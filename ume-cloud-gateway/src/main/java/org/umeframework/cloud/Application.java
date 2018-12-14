package org.umeframework.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.umeframework.dora.message.MessageProperties;

/**
 * Gateway server for umeframework
 * 
 * @author UME team
 */
@SpringBootApplication
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
    
//    @Bean
//    public RouteLocator customizeRouter(RouteLocatorBuilder builder) {
//        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
//        config.setParts(1);
//        return builder.routes()
//                .route("host_route", r -> r.path("/a/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8081"))
//                .route("host_route", r -> r.path("/b/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8082"))
//                .build();
//    }

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
