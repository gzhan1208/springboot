package com.example.spring_boot_alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.example.spring_boot_alipay.model.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @author gz
 * @Description alipay-trade-sdk对alipay-trade-sdk进行了封装，两个都可以使用
 *  建议使用trade
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfiguration {
    @Autowired
    private AlipayProperties properties;

    /*
     * alipay-trade-sdk
     * @return
     * */
    @Bean
    public AlipayTradeService alipayTradeService() {
        return new AlipayTradeServiceImpl.ClientBuilder()
                .setGatewayUrl(properties.getGatewayUrl())
                .setAppid(properties.getAppid())
                .setPrivateKey(properties.getAppPrivateKey())
                .setAlipayPublicKey(properties.getAlipayPublicKey())
                .setSignType(properties.getSignType())
                .build();
    }

    /**
     * alipay-sdk-java
     * @return
     */
    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(properties.getGatewayUrl(),
                properties.getAppid(),
                properties.getAppPrivateKey(),
                properties.getFormate(),
                properties.getCharset(),
                properties.getAlipayPublicKey(),
                properties.getSignType());
    }
}
