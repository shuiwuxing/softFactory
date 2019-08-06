package cc.mrbird.febs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author MrBird
 */
@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@MapperScan("cc.mrbird.febs.*.mapper")
//public class FebsApplication {
public class FebsApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FebsApplication.class);
    }
    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsApplication.class)
                .run(args);
    }

}
