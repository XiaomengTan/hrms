package com.hrms.config;

import com.hrms.netty.NettyServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NettyServerConfig {
    @Resource
    private NettyServer nettyServer;

    private Thread nettyThread;

    @PostConstruct
    public void startNettyServer() {
        //使用另一个线程来启动netty服务器，避免因线程安全问题使主线程被阻塞导致http请求无法正常访问
        nettyThread = new Thread(() -> {
            try {
                nettyServer.start();
            } catch (Exception e) {
                log.info("netty server start fail: {}", e.getMessage());
            }
        });
        nettyThread.start();
    }

    @PreDestroy
    public void stopNettyServer() throws InterruptedException {
        //项目关闭前，首先关闭netty服务器
        if (nettyThread != null && nettyThread.isAlive()) {
            nettyServer.shutdown();
            try {
                nettyThread.join();
                log.info("netty thread stopped success");
            } catch (InterruptedException e) {
                log.info("netty thread stopped fail: {}", e.getMessage());
            }
        }
    }
}