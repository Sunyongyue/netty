package com.tianhe.currentnetty.IotServer;


import com.alibaba.fastjson.JSONObject;
import com.tianhe.currentnetty.entity.CustInfo;
import com.tianhe.currentnetty.producer.MsgProducer;
import com.tianhe.currentnetty.service.ICustInfoService;
import com.tianhe.currentnetty.utils.RedisUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 我们自定义的handler需要继承netty中已定义好的HandlerAdapter，才能成为一个handler
 **/
@Slf4j
@Component
public class IotServerHandler extends ChannelInboundHandlerAdapter {
    private ICustInfoService custInfoService;
    private RedisUtils redisUtils;

    private MsgProducer msgProducer;

//    public IotServerHandler(RedisUtils redisUtils, MsgProducer msgProducer) {
//        this.redisUtils = redisUtils;
//        this.msgProducer = msgProducer;
//    }

    public IotServerHandler(ICustInfoService custInfoService, RedisUtils redisUtils, MsgProducer msgProducer) {
        this.custInfoService = custInfoService;
        this.redisUtils = redisUtils;
        this.msgProducer = msgProducer;
    }

    //通道建立
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {



    }

    //数据读取
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        //创建字节数组,buffer.readableBytes可读字节长度
        byte[] infoBytes = new byte[buffer.readableBytes()];
        //复制内容到字节数组
        buffer.readBytes(infoBytes);
        //字节数组转字符串
        String s = bytesToHexString(infoBytes);
        List<CustInfo> list = custInfoService.list();
        System.out.println(list);
        System.out.println(s);
        redisUtils.lSet("1",s);
        msgProducer.sendMsg(s);
        //过滤掉不合格字符串
        ctx.close();


    }

    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // cause.printStackTrace();
        //输出错误日志
        //log.info("网关处理接收数据发生异常",cause);
        //关闭通道
        ctx.close();
    }
    public  String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


}
