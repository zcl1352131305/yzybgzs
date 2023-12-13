package cn.gov.spb.cq.yzglfzgj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="audit.upload")
@Data
public class UploadConfig {

    /**文件上传根目录*/
    private String rootPath;

    /**文件资源访问目录**/
    private String webPath;


}
