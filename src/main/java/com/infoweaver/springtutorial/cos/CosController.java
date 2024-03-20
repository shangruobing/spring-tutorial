package com.infoweaver.springtutorial.cos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Ruobing Shang 2024-03-19 20:36
 */
@RestController
@Slf4j
public class CosController {
    private final ICosService cosService;

    @Autowired
    public CosController(ICosService cosService) {
        this.cosService = cosService;
    }

    @PostMapping("/cos/upload")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
        return cosService.uploadFile(file);
    }
}
