package com.tianhe.currentnetty.controller;

import com.tianhe.currentnetty.entity.CustInfo;
import com.tianhe.currentnetty.service.ICustInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    ICustInfoService custInfoService;
    @RequestMapping("test")
    @ResponseBody
    public void test(){
        List<CustInfo> list = custInfoService.list();
        System.out.println(list);
    }
}
