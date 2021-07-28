package com.yuzh.webflux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.TcpClient;
import reactor.netty.tcp.TcpServer;

@SpringBootApplication
public class WebFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @Bean
    CommandLineRunner serverRunner(TcpDecoderHanlder tcpDecoderHanlder) {
        return strings -> {
            createTcpServer(tcpDecoderHanlder);
        };
    }

    /**
     * 创建TCP Server
     *
     * @param tcpDecoderHanlder： 解析TCP Client上报数据的handler
     */
    private void createTcpServer(TcpDecoderHanlder tcpDecoderHanlder) {
        TcpServer.create()
                .handle((in, out) -> {
                    in.receive()
                            .asByteArray()
                            .subscribe();
                    return Flux.never();

                })
                .doOnConnection(conn ->
                        conn.addHandler(tcpDecoderHanlder)) //实例只写了如何添加handler,可添加delimiter，tcp生命周期，decoder，encoder等handler
                .port(9999)
                .bindNow();
    }

}
