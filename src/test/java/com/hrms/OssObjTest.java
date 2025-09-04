package com.hrms;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.VoidResult;
import com.hrms.config.AliOSSConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@SpringBootTest
public class OssObjTest {
    private static final Logger log = LoggerFactory.getLogger(OssObjTest.class);
    @Autowired
    private AliOSSConfig properties;
    @Test
    public void deleteFile() {
        String fileName = "bb196b38-c5f3-492a-82ca-de8691fcb1ba.jpg";

        String endpoint = properties.getEndpoint();
        String accessKeyId = properties.getAccessKeyId();
        String accessKeySecret = properties.getAccessKeySecret();
        String bucketName = properties.getBucketName();


        String objectName = fileName;

//        boolean flag = false;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 删除文件。
            VoidResult voidResult = ossClient.deleteObject(bucketName, objectName);
            if (voidResult.getResponse().getStatusCode() == 204){
//                flag = true;
            }
            // 关闭OSSClient。
            ossClient.shutdown();

        } catch (OSSException e){
            e.printStackTrace();
        }
//        return flag;
    }

    @Test
    public void uploadFile() throws IOException {
        File file = new File("C:\\Users\\morea\\Desktop\\微信图片_20240806113942.jpg");
        FileInputStream inputStreamFile = new FileInputStream(file);

        MultipartFile multipartFile = new MockMultipartFile(
                file.getName(),             // 文件名
                file.getName(),             // 原始文件名
                "image/jpeg",               // 文件类型（根据需要可以动态获取）
                inputStreamFile                 // 文件输入流
        );
        String endpoint = properties.getEndpoint();
        String accessKeyId = properties.getAccessKeyId();
        String accessKeySecret = properties.getAccessKeySecret();
        String bucketName = properties.getBucketName();
        // 获取上传的文件的输入流
        InputStream inputStream = multipartFile.getInputStream();

        // 避免文件覆盖
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        log.info(url);
    }

}
