package com.jink.jinblog.strategy.impl;

import com.jink.jinblog.config.CosConfigProperties;
import com.jink.jinblog.exception.BizException;
import com.jink.jinblog.strategy.UploadStrategy;
import com.jink.jinblog.util.FileUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author JINK
 * @version 1.0
 * @description 腾讯云存储
 * @date 2023/3/5 19:15:11
 */
@Slf4j
@Service
public class CosUploadStrategyImpl implements UploadStrategy {

    @Autowired
    private CosConfigProperties cosConfigProperties;


    @Override
    public String uploadFile(MultipartFile file, String path) {
        try {
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtName(file.getOriginalFilename());
            // 重新生成文件名
            String fileName = md5 + extName;
            // 判断文件是否已存在
            if (!exists(path + fileName)) {
                // 不存在则继续上传
                upload(path, fileName, file.getInputStream());
            }
            // 返回文件访问路径
            return getFileAccessUrl(path + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("文件上传失败");
        }
    }

    @Override
    public String uploadFile(String fileName, InputStream inputStream, String path) {
        try {
            // 上传文件
            upload(path, fileName, inputStream);
            // 返回文件访问路径
            return getFileAccessUrl(path + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("文件上传失败");
        }
    }

    @Override
    public String getMode() {
        return "cos";
    }


    public Boolean exists(String filePath) {
        return getCosClient().doesObjectExist(cosConfigProperties.getBucketName(), filePath);
    }


    public void upload(String path, String fileName, InputStream inputStream) {
        // 生成cos客户端
        COSClient cosclient = getCosClient();
        try {
            // 从输入流上传(需提前告知输入流的长度, 否则可能导致 oom)
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 设置输入流长度
            objectMetadata.setContentLength(inputStream.available());

            cosclient.putObject(cosConfigProperties.getBucketName(), path + fileName, inputStream, objectMetadata);

        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            // 关闭客户端
            cosclient.shutdown();
        }
    }


    public String getFileAccessUrl(String filePath) {
        return cosConfigProperties.getUrl() + filePath;
    }

    public COSClient getCosClient() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(cosConfigProperties.getSecretId(), cosConfigProperties.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(cosConfigProperties.getRegion()));
        // 3 生成cos客户端
        return new COSClient(cred, clientConfig);
    }
}
