package com.infoweaver.springtutorial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Ruobing Shang 2023-10-24 08:30
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final UserDetailsServiceImpl userDetailsService;
    private final CredentialAuthenticationFilter credentialAuthenticationFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                                 CredentialAuthenticationFilter credentialAuthenticationFilter,
                                 JwtAuthenticationFilter jwtAuthenticationFilter,
                                 AuthenticationEntryPointImpl authenticationEntryPoint,
                                 AccessDeniedHandlerImpl accessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.credentialAuthenticationFilter = credentialAuthenticationFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                /**
                 * 禁用默认登录页面
                 */
                .formLogin(AbstractHttpConfigurer::disable)
                /**
                 * 禁用默认注销页面
                 */
                .logout(AbstractHttpConfigurer::disable)
                /**
                 * 自定义密码加密策略
                 */
                .authenticationProvider(authenticationProvider())
                /**
                 * 禁用CSRF
                 */
                .csrf(AbstractHttpConfigurer::disable)
                /**
                 * 禁用session
                 */
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /**
                 * 禁用httpBasic
                 */
                .httpBasic(AbstractHttpConfigurer::disable)
                /**
                 * 允许跨域
                 */
                .cors(withDefaults())
                /**
                 * JWT认证
                 */
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                /**
                 * 其他后端Credential证书认证
                 */
                .addFilterAfter(credentialAuthenticationFilter, JwtAuthenticationFilter.class)
                /**
                 * 401异常捕获
                 */
                .exceptionHandling(handler -> handler.authenticationEntryPoint(authenticationEntryPoint))
                /**
                 * 403异常捕获
                 */
                .exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedHandler))
                /**
                 * 路径匹配
                 */
                .authorizeHttpRequests((request) -> request
                        /**
                         * 登录接口
                         */
                        .requestMatchers("/user/login").permitAll()
                        /**
                         * 微信授权接口使用queryParam来完成校验
                         */
                        .requestMatchers("/wechat-authorize").permitAll()
                        /**
                         * Swagger接口
                         */
                        .requestMatchers(
                                "/doc",
                                "/swagger-ui/**",
                                "/v3/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new CustomPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
