package org.umeframework.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.umeframework.dora.appconfig.AutoConfigurationBasic;
import org.umeframework.dora.appconfig.AutoConfigurationDao;

/**
 * Gateway server for umeframework
 * 
 * @author UME team
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration.class, org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration.class, org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class })
@Import({ AutoConfigurationBasic.class, AutoConfigurationDao.class })
@ComponentScan(basePackages = "org.umeframework.cloud")
public class Application {

    /**
     * main
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Throwable e) {
            if (!e.getClass().getName().startsWith("org.springframework.boot.devtools")) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create RestTemplate instance.<br>
     * 
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
