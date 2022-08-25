package com.practice.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.practice.feign.clients.TaskRestClient;
import com.practice.feign.model.ScheduledTask;

@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "com.practice.feign.clients")
@SpringBootApplication
public class FeignPractice1ClientSideApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignPractice1ClientSideApplication.class, args);
	}

	@Autowired
	private TaskRestClient taskRestClient;

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			
			System.out.println("-----------------------------------------------------------");
			// ScheduledTask task = taskRestClient.findScheduledTaskById(71);
			// System.out.println("task found: " + task);
			// taskRestClient.findNormalTasksByFilter(new TaskFilter("TASK-76")).stream()
			// taskRestClient.findNormalTasks().stream()
			// 	.forEach(System.out::println);
			System.out.println("-----------------------------------------------------------");
		};
	}
}
