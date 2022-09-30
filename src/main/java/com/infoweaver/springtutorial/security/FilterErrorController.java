//package com.infoweaver.springtutorial.security;
//
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// * @author Ruobing Shang 2022-09-29 23:18
// */
//@RestController
//public class FilterErrorController extends BasicErrorController {
//    public FilterErrorController(ServerProperties serverProperties) {
//        super(new DefaultErrorAttributes(), serverProperties.getError());
//    }
//
//    @Override
//    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity error(HttpServletRequest request) {
//        System.out.println("安全的异常捕获");
//        Map<String, Object> body = getErrorAttributes(request, ErrorAttributeOptions.defaults());
//        System.out.println(body);
//        return new ResponseEntity<>(String.valueOf(body.get("error")), HttpStatus.valueOf(200));
//    }
//}
