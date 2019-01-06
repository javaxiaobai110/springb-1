package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.U_RMapper;
import com.baizhi.springb1.entity.U_R;
import com.baizhi.springb1.service.U_RService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class U_RServiceImpl implements U_RService {

    @Autowired
    private U_RMapper u_rMapper;

    @Override
    public List<U_R> queryByU_R(U_R u_r) {
        List<U_R> select = u_rMapper.select(u_r);
        return select;
    }
}
