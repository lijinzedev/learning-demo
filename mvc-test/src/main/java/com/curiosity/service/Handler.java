package com.curiosity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.curiosity.base.BaseService;

/**
 * @Classname UserServiceImpl
 * @Description
 * @Date 2021/8/2 14:51
 * @Created by lijinze
 */
public interface Handler<T>  {

    public void add(T user);

    public void addAndThrowException(T user);

    public void addRequiredNew(T user);

    public void addRequiredNewAndThrowException(T user);
    public void addNested(T user);
    public void addNestedException(T user);
}
