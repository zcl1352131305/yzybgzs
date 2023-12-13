package cn.gov.spb.cq.yzglfzgj;

import cn.gov.spb.cq.yzglfzgj.utils.SVNUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Order(value = 10) // 指定其执行顺序,值越小优先级越高
public class Initializer implements ApplicationRunner {
    @Resource
    private Environment env;

    /**
     * 项目启动后，若开启了swagger，输出swagger地址
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String svnUrl = env.getProperty("svn.repository.url");
        String password = env.getProperty("svn.repository.password");
        String username = env.getProperty("svn.repository.username");
        SVNUtil.init(svnUrl,username,password);

    }
}
