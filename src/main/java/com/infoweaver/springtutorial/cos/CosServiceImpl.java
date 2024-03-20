package com.infoweaver.springtutorial.cos;

import com.infoweaver.springtutorial.util.DateTimeUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Ruobing Shang 2024-03-19 20:36
 */
@Service
public class CosServiceImpl implements ICosService {
    @Value("${cos.secretId}")
    private String secretId;
    @Value("${cos.secretKey}")
    private String secretKey;
    @Value("${cos.bucketName}")
    private String bucketName;
    @Value("${cos.region}")
    private String region;

    @Override
    public Map<String, String> uploadFile(MultipartFile file) {
        if (file.getSize() <= 0) {
            throw new RuntimeException("上传的文件大小不能为空");
        }
        if (file.getSize() > 20 * 1024 * 1024) {
            throw new RuntimeException("上传的文件大小不能超过20MB");
        }
        COSCredentials credentials = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        COSClient cosClient = new COSClient(credentials, clientConfig);
        try {
            String fileName = DateTimeUtils.getNowDateTimeStampStr() + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            InputStream inputStream = file.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
            cosClient.putObject(putObjectRequest);
            inputStream.close();
            return Map.of("url", "https://" + bucketName + ".cos." + region + ".myqcloud.com/" + fileName);
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败");
        } finally {
            cosClient.shutdown();
        }
    }
}


