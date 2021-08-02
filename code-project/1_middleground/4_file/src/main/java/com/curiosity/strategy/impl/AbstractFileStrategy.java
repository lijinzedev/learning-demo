package com.curiosity.strategy.impl;

import com.curiosity.domain.FileDeleteDO;
import com.curiosity.entity.File;
import com.curiosity.enumeration.IconType;
import com.curiosity.properties.FileServerProperties;
import com.curiosity.strategy.FileStrategy;
import com.curiosity.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public abstract class AbstractFileStrategy implements FileStrategy {
    private static final String FILE_SPLIT = ".";

    @Autowired
    protected FileServerProperties fileProperties;

    protected FileServerProperties.Properties properties;
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
        //设置文件创建时间
        fileEntity.setCreateMonth(DateUtils.formatAsYearMonthEn(now));
        fileEntity.setCreateWeek(DateUtils.formatAsYearWeekEn(now));
        fileEntity.setCreateDay(DateUtils.formatAsDateEn(now));
        try {
            uploadFile(fileEntity,file);
        } catch (Exception e) {
            log.error("e = {}",e);
        }
        return fileEntity;
    }

    /**
     * 文件上传抽象方法，需要由当前类的子类来实现
     * @param file
     * @param multipartFile
     * @return
     */
    public abstract void uploadFile(File file,MultipartFile multipartFile) throws Exception;

    /**
     * 文件删除
     * @param list
     * @return
     */
    @Override
    public boolean delete(List<FileDeleteDO> list) {
        if(list == null || list.isEmpty()){
            return true;
        }

        boolean flag = false;//删除操作是否成功的标识位
        for (FileDeleteDO fileDeleteDO : list) {
            try {
                delete(fileDeleteDO);
                flag = true;
            }catch (Exception e){
                log.error("e = {}",e);
            }
        }

        return flag;
    }

    /**
     * 文件删除抽象方法，需要当前类的子类来实现
     * @param fileDeleteDO
     */
    public abstract void delete(FileDeleteDO fileDeleteDO);


}
