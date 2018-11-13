package com.example.spring_boot_alipay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author gz
 * @Date 2018/11/13 21:26
 */
@Configuration
public class MVCConfig {
    @Configuration
    public class WebMvcConfiguration extends WebMvcConfigurationSupport {
        @Override
        protected void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/gotoPay").setViewName("payPage");
            super.addViewControllers(registry);
        }
    }
}
