package com.bdqn.mall.config;

import com.bdqn.mall.utils.SystemConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * 加载静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        //设置回显路径
        registry.addResourceHandler(SystemConstants.IMAGE_SHOW_PATH).
                //设置文件上传路径
                        addResourceLocations("file:/"+SystemConstants.IMAGE_UPLOAD_PATH);
    }
}
