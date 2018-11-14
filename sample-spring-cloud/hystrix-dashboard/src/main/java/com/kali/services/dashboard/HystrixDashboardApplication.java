package com.kali.services.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * This application's main class annoted with @EnableHystrixDashboard
 * & @EnableTurbine. After launching it, the Hystrix dashboard is available
 * under the /hystrix context path.
 * 
 * TODO : It would be nice to improve the turbine treatment with a message queue
 * (Kafka or AMQP).
 * 
 * @author kali
 *
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}

}
