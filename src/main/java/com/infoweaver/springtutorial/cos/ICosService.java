package com.infoweaver.springtutorial.cos;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Ruobing Shang 2024-03-19 20:36
 */
public interface ICosService {
    /**
     * Tencent Cloud Object Storage
     *
     * @param file file
     * @return url
     */
    Map<String, String> uploadFile(MultipartFile file);
}

