package com.curiosity.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {


    @Autowired
    private M mapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int delete(Wrapper<T> queryWrapper) {
        return mapper.delete(queryWrapper);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean saveBatch(Collection<T> entityList) {
        return super.saveBatch(entityList, entityList.size());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return super.saveOrUpdateBatch(entityList, entityList.size());
    }
}
