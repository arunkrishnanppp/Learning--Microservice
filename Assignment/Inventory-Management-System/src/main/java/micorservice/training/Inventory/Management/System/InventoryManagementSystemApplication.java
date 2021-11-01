package micorservice.training.Inventory.Management.System;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@EnableConfigurationProperties(value=Myconfig.class)
@Configuration
@EnableEurekaClient
public class InventoryManagementSystemApplication {
	

	@Autowired
	private Myconfig config;
	
	@GetMapping("/ims/products/config")
	public Myconfig returnConfig() {
		
		System.out.println(config.getDatabaseUrl());
		return config;
	}
	
//	@Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.h2.Driver");
//        dataSourceBuilder.url(config.getDatabaseUrl()+"arun");
//      // dataSourceBuilder.url("jdbc:h2:mem:test");
//  dataSourceBuilder.username("SA");
//     dataSourceBuilder.password("gl");
//        return dataSourceBuilder.build();
//    }

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
	}

}
