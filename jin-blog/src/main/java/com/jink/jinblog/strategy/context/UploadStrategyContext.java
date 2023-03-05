package com.jink.jinblog.strategy.context;

import com.jink.jinblog.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author JINK
 * @version 1.0
 * @project webFlux_blog
 * @description 上传策略上下文
 * @date 2023/3/4 14:46:06
 */
@Service
public class UploadStrategyContext {

    /**
     * 上传模式
     */
    @Value("${upload.mode}")
    private String uploadMode;

    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;

   public UploadStrategyContext(List<UploadStrategy> uploadStrategyList){
       uploadStrategyMap = uploadStrategyList.stream().collect(Collectors.toMap(UploadStrategy::getMode, Function.identity()));
   }


    /**
     * 执行上传策略
     *
     * @param file 文件
     * @param path 路径
     * @return {@link String} 文件地址
     */
    public String executeUploadStrategy(MultipartFile file, String path) {
        return uploadStrategyMap.get(uploadMode).uploadFile(file, path);
    }


    /**
     * 执行上传策略
     *
     * @param fileName    文件名称
     * @param inputStream 输入流
     * @param path        路径
     * @return {@link String} 文件地址
     */
    public String executeUploadStrategy(String fileName, InputStream inputStream, String path) {
        return uploadStrategyMap.get(uploadMode).uploadFile(fileName, inputStream, path);
    }
}
