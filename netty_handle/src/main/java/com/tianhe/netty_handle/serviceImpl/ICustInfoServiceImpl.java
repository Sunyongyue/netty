package com.tianhe.netty_handle.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.tianhe.netty_handle.entity.CustInfo;
import com.tianhe.netty_handle.mapper.CustInfoMapper;
import com.tianhe.netty_handle.service.ICustInfoService;
import org.springframework.stereotype.Service;


@Service
public class ICustInfoServiceImpl extends ServiceImpl<CustInfoMapper, CustInfo> implements ICustInfoService {

}
