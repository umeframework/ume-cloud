package org.umeframework.cloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka server for doraframework cloud
 * 
 * @author Yue MA
 */
@SpringBootApplication
@EnableEurekaServer
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

}