package com.tianhe.currentnetty.IotServer;


import com.tianhe.currentnetty.config.NettyConfig;
import com.tianhe.currentnetty.producer.MsgProducer;
import com.tianhe.currentnetty.service.ICustInfoService;
import com.tianhe.currentnetty.utils.RedisUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class IotServer implements CommandLineRunner {
    @Resource
    private NettyConfig nettyConfig;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private MsgProducer msgProducer;
    @Resource
    private ICustInfoService custInfoService;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap bootstrap;
    private ChannelFuture futureMeter;
    private ChannelFuture futureApp;

    public IotServer(){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        //配置bootstrap参数
        bootstrap.group(bossGroup,workerGroup) //设置两个线程组
                .channel(NioServerSocketChannel.class) //设置使用NioServerSocketChannel作为服务器的通道实现
                .option(ChannelOption.SO_BACKLOG,128) //设置线程队列等待连接数
                .childOption(ChannelOption.SO_KEEPALIVE,true) //设置保持活动链接状态
                .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象(匿名对象)
                    //给pipeline设置处理器
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //lengthAdjustment = 数据包总长度 - lengthFieldOffset - lengthFieldLength - 长度域的值
                        //sc.pipeline().addLast(new LengthFieldBasedFrameDecoder(2000,3,1,-4,0));
                        sc.pipeline().addLast(new IotServerHandler(custInfoService,redisUtils,msgProducer));
                    }
                }); //设置workerGroup的EventLoop对应的管道设置处理器

    }

    public void start(){
         bootstrap.bind(nettyConfig.getPort());
        System.out.println("--------------------------在--"+nettyConfig.getPort()+"--端口监听-----------------------------------");
    }

    @Override
    public void run(String... args) throws Exception {
        start();
    }
}
