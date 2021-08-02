package com.curiosity.service.impl;

import cn.hutool.core.util.StrUtil;
import com.curiosity.constant.StrPool;
import com.curiosity.domain.FileDeleteDO;
import com.curiosity.entity.File;
import com.curiosity.properties.FileServerProperties;
import com.curiosity.strategy.impl.AbstractFileStrategy;
import com.curiosity.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@EnableConfigurationProperties(FileServerProperties.class)
@ConditionalOnProperty(name = "file.file.type", havingValue = "LOCAL")
public class LocalServiceImpl extends AbstractFileStrategy {
    private void buildClient() {
        properties = fileProperties.getLocal();
    }

    @Override
    public void uploadFile(File file, MultipartFile multipartFile) throws Exception {
        buildClient();
        String endpoint = properties.getEndpoint();
        String bucketName = properties.getBucketName();
        String uriPrefix = properties.getUriPrefix();
        //使用UUID为文件生成新文件名
        String fileName = UUID.randomUUID().toString() + StrPool.DOT + file.getExt();
        //  D:\\uploadFiles\\oss-file-service\\2020\\05\\xxx.doc
        //日期目录
        String relativePath = Paths.get(LocalDate.now().format(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_MONTH_FORMAT_SLASH))).toString();
        //上传文件存储的绝对目录 例如：D:\\uploadFiles\\oss-file-service\\2020\\05
        Path absolutePath = Paths.get(endpoint, bucketName, relativePath);
        Files.copy(multipartFile.getInputStream(), absolutePath, StandardCopyOption.REPLACE_EXISTING);
        //文件上传完成后需要设置File对象的属性(url，filename，relativePath），用于保存到数据库
        String url = new StringBuilder(uriPrefix)
                .append(StrPool.SLASH)
                .append(properties.getBucketName())
                .append(StrPool.SLASH)
                .append(relativePath)
                .append(StrPool.SLASH)
                .append(fileName)
                .toString();
        //替换掉windows环境的\路径
        url = StrUtil.replace(url, "\\\\", StrPool.SLASH);
        url = StrUtil.replace(url, "\\", StrPool.SLASH);
        file.setUrl(url);//  http://ip:port/oss-file-service/2020/05/xxx.doc
        file.setFilename(fileName);
        file.setRelativePath(relativePath);
    }

    @Override
    public void delete(FileDeleteDO fileDeleteDO) {
        final Path path = Paths.get(properties.getEndpoint(), properties.getBucketName(), fileDeleteDO.getRelativePath(), fileDeleteDO.getFileName());
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件删除失败{}", path);
        }
    }
}
