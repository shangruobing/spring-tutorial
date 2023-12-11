package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.util.DateTimeUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * @author Ruobing Shang 2023-10-20 22:33
 */
public class FileManager {
    /**
     * 上传文件钩子
     *
     * @param file             文件
     * @param fileCategoryName 文件分类名称
     * @return 完整文件名称
     */
    public static String uploadFile(MultipartFile file, String fileCategoryName) {
        /**
         * 程序运行路径
         * 开发环境为 /Users/Ruobing/Desktop/Red Packet Distribution/backend
         * 测试环境为 /
         */
        String rootDirectory = System.getProperty("user.dir");
        rootDirectory = "/".equals(rootDirectory) ? rootDirectory : rootDirectory + "/";
        /**
         * 上传文件夹命名
         */
        String uploadDirectoryName = "upload";
        /**
         * 根据月份进行划分文件
         */
        LocalDate currentDate = LocalDate.now();
        String dateName = currentDate.getYear() + "-" + currentDate.getMonth().getValue();
        /**
         * 完整文件前缀路径: 运行环境路径/上传文件夹命名/文件类别名/日期
         * 例如 运行环境路径/upload/XXX-attachment/2023-10/
         */
        String prefixPath = rootDirectory + uploadDirectoryName + "/" + fileCategoryName + "/" + dateName + "/";
        /**
         * 为文件名添加时间戳
         * jar包运行加入时区避免时差 java -jar -Duser.timezone=GMT+08
         */
        String originalFilename = file.getOriginalFilename();
        String filename = prefixPath + DateTimeUtils.getNowDateTimeStampStr() + "_" + originalFilename;
        /**
         * 保存到磁盘
         */
        try {
            Files.createDirectories(Paths.get(prefixPath));
            file.transferTo(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return filename;
    }

    /**
     * 根据文件路径下载文件
     *
     * @param filepath 文件下载
     * @return 文件流对象
     * @throws IOException IO异常
     */
    public static ResponseEntity<?> downloadFile(String filepath) throws IOException {
        File downloadFile = new File(filepath);
        if (downloadFile.exists()) {
            byte[] bytes = Files.readAllBytes(downloadFile.toPath());
            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadFile.getName(), StandardCharsets.UTF_8))
                    .header("Access-Control-Expose-Headers", "Content-Disposition")
                    .body(byteArrayResource);
        }
        return new ResponseEntity<>(downloadFile.getName() + " 不存在，下载失败!", HttpStatus.NOT_FOUND);
    }
}
