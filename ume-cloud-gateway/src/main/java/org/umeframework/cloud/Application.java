package org.umeframework.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.umeframework.dora.appconfig.AutoConfigurationBasic;
import org.umeframework.dora.appconfig.AutoConfigurationDao;
import org.umeframework.dora.message.MessageProperties;

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
     * myRoutes
     * 
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().build();
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
     * routeMapping
     * 
     * @return
     * @throws Exception
     */
    @Bean(name = "routeMapping")
    public MessageProperties routeMapping() throws Exception {
        return new org.umeframework.dora.message.impl.MessagePropertiesImpl("routeMapping");
    }

}
