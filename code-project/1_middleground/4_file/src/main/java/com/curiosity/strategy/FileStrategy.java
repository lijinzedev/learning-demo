package com.curiosity.strategy;

import com.curiosity.domain.FileDeleteDO;
import com.curiosity.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Classname FileStrategy
 * @Description
 * @Date 2021/7/7 16:15
 * @Created by curiosity
 */
public interface FileStrategy {
    /**
     * 文件上传接口
     * @param file
     * @return
     */
    public File upload(MultipartFile file);

    /**
     * 删除
     * @param list
     * @return
     */
    public File delete(List<FileDeleteDO> list);

}
