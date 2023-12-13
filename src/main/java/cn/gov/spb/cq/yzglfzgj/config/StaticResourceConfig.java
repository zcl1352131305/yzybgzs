package cn.gov.spb.cq.yzglfzgj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 配置静态资源路径映射
 */
@Configuration
public class StaticResourceConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private UploadConfig uploadConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadConfig.getWebPath().concat("/**"))
                .addResourceLocations("file:".concat(uploadConfig.getRootPath()).concat("/"));
    }

}
