package com.tianhe.currentnetty.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianhe.currentnetty.entity.CustInfo;
import com.tianhe.currentnetty.mapper.CustInfoMapper;
import com.tianhe.currentnetty.service.ICustInfoService;
import org.springframework.stereotype.Service;



@Service
public class ICustInfoServiceImpl extends ServiceImpl<CustInfoMapper, CustInfo> implements ICustInfoService {

}
