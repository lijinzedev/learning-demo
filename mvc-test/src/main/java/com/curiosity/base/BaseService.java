package com.curiosity.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;


public interface  BaseService<T> extends IService<T> {

    int delete(Wrapper<T> queryWrapper);

    // 批量插入
    @Override
    boolean saveBatch(Collection<T> entityList);

    // 批量修改插入
    @Override
    boolean saveOrUpdateBatch(Collection<T> entityList);


}
