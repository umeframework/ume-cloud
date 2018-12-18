package org.umeframework.cloud;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gateway server for umeframework
 * 
 * @author UME team
 */
@Configuration
public class RouterConfiguration {

    /**
     * routeLocators
     * 
     * @param builder
     * @return
     * @throws Exception
     */
    @Bean
    public RouteLocator routeLocators(RouteLocatorBuilder builder) throws Exception {
        RouteLocatorBuilder.Builder rb = builder.routes();
        
        /// Default routing rules is 'http://{gateway-address}:{gatewary-port}/{SERVICE_REG_NAME}/**'
        /// You could add customize routing rules such as:
        // rb.route("defaultRoute", p -> p.path("/ume-quickstart-cloud/**").filters(f -> f.stripPrefix(1)).uri("lb://UME-QUICKSTART-CLOUD"));
        // rb.route("defaultRoute", p -> p.path("/lb/ume-quickstart-cloud/**").filters(f -> f.stripPrefix(2)).uri("lb://UME-QUICKSTART-CLOUD"));
        // rb.route("defaultRoute", p -> p.path("/hp/ume-quickstart-cloud/**").filters(f -> f.stripPrefix(2)).uri("http://localhost:8081"));

        return rb.build();
    }

}
