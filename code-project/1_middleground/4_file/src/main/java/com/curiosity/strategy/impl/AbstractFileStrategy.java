package com.curiosity.strategy.impl;

import com.curiosity.domain.FileDeleteDO;
import com.curiosity.entity.File;
import com.curiosity.enumeration.IconType;
import com.curiosity.strategy.FileStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname AbstractFileStrategy
 * @Description
 * @Date 2021/7/7 17:33
 * @Created by curiosity
 */
public abstract class AbstractFileStrategy implements FileStrategy {
    @Override
    public File upload(MultipartFile file) {
        final String originalFilename = file.getOriginalFilename();
        final LocalDateTime now = LocalDateTime.now();
        final File fileEntity = File.builder()
                .submittedFileName(StringUtils.getFilename(originalFilename))
                .isDelete(Boolean.FALSE)
                .size(file.getSize())
                .contextType(file.getContentType())
                .createTime(now)
                .ext(StringUtils.getFilenameExtension(originalFilename)).build();
        fileEntity.setIcon(IconType.getIcon(fileEntity.getExt()).getIcon());

        return null;
    }


    @Override
    public File delete(List<FileDeleteDO> list) {
        return null;
    }
}
