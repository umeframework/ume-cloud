package org.umeframework.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.umeframework.dora.message.MessageProperties;

/**
 * Zuul server for umeframework
 * 
 * @author UME team
 */
@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration.class, org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration.class, org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class })
@Import({ org.umeframework.dora.appconfig.AutoConfigurationBasic.class, })
@ComponentScan(basePackages = "org.umeframework.cloud")
public class Application {

    /**
     * main
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
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

    /**
     * messageProperties
     * 
     * @return
     * @throws Exception
     */
    @Bean(name = "appMapping")
    public MessageProperties appMapping() throws Exception {
        return new org.umeframework.dora.message.impl.MessagePropertiesImpl("appMapping");
    }

}
