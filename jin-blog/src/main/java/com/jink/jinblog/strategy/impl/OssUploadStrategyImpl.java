package com.jink.jinblog.strategy.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.jink.jinblog.config.OssConfigProperties;
import com.jink.jinblog.exception.BizException;
import com.jink.jinblog.strategy.UploadStrategy;
import com.jink.jinblog.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author JINK
 * @version 1.0
 * @description 阿里云存储服务
 * @date 2023/3/5 19:02:26
 */
@Service
public class OssUploadStrategyImpl implements UploadStrategy {

    @Autowired
    private OssConfigProperties ossConfigProperties;


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
        return "oss";
    }

    public Boolean exists(String filePath) {
        return getOssClient().doesObjectExist(ossConfigProperties.getBucketName(), filePath);
    }


    public void upload(String path, String fileName, InputStream inputStream) {
        getOssClient().putObject(ossConfigProperties.getBucketName(), path + fileName, inputStream);
    }


    public String getFileAccessUrl(String filePath) {
        return ossConfigProperties.getUrl() + filePath;
    }

    /**
     * 获取ossClient
     *
     * @return {@link OSS} ossClient
     */
    private OSS getOssClient() {
        return new OSSClientBuilder().build(ossConfigProperties.getEndpoint(), ossConfigProperties.getAccessKeyId(), ossConfigProperties.getAccessKeySecret());
    }
}
