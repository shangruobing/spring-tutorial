package com.infoweaver.springtutorial.tms;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tms.v20201229.TmsClient;
import com.tencentcloudapi.tms.v20201229.models.TextModerationRequest;
import com.tencentcloudapi.tms.v20201229.models.TextModerationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @author Ruobing Shang 2024-07-02 16:33
 */
@Slf4j
@Service
public class TextModerationServiceImpl {
    @Value("${tms.secretId}")
    private String secretId;
    @Value("${tms.secretKey}")
    private String secretKey;
    @Value("${tms.bizType}")
    private String bizType;

    public boolean validate(String content) {
        try {
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            TmsClient client = new TmsClient(cred, "ap-guangzhou", clientProfile);
            TextModerationRequest request = new TextModerationRequest();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedString = encoder.encodeToString(content.getBytes());
            request.setContent(encodedString);
            request.setBizType(bizType);
            TextModerationResponse response = client.TextModeration(request);
            log.info("敏感词：{} 类型：{} 处理结果：{}",content, response.getLabel(), response.getSuggestion());
            return "PASS".equalsIgnoreCase(response.getSuggestion());
        } catch (TencentCloudSDKException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}