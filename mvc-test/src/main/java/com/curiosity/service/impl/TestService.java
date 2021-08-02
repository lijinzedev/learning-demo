package com.curiosity.service.impl;

import com.curiosity.dao.User1Mapper;
import com.curiosity.dao.User2Mapper;
import com.curiosity.model.model.Father;
import com.curiosity.model.model.Son;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname TestService
 * @Description
 * @Date 2021/8/2 14:58
 * @Created by lijinze
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
    @Resource(name = "fatherServiceImpl")
    private FatherServiceImpl fatherServiceImpl;
    @Resource(name = "sonServiceImpl")
    private SonServiceImpl sonServiceImpl;


    public void testUser1NotTransactional(Father father, Son son) {

        fatherServiceImpl.add(father);
        sonServiceImpl.add(son);

    }
}
