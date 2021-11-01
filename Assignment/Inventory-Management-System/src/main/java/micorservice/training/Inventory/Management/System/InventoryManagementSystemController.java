package micorservice.training.Inventory.Management.System;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class InventoryManagementSystemController {
	
//	@Autowired
//	private Myconfig config;
//	
//	@GetMapping("/ims/products/config")
//	public Myconfig returnConfig() {
//		
//		System.out.println(config.getDatabaseUrl());
//		return config;
//	}
	@Autowired
	Environment env;
//	
	
	
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	
//	@Bean 
//	public DataSource getDataSource() { 
//	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(); 
//	   
//	    dataSourceBuilder.url("jdbc:h2:mem:test");
//	    return dataSourceBuilder.build(); 
//	}
	
	
	 
	@GetMapping("/ims/products")
	public List<Product> returnAllProduct() {
		List<Product> prod=productRepository.findAll();
		prod.get(0).setPort(env.getProperty("local.server.port"));
		return prod;
	}
	
	@GetMapping("/ims/products/{id}")
	public Product getProductById(@PathVariable int id) {
		Optional<Product> prod=productRepository.findById(id);
		return prod.get();
	}
	@PostMapping("/ims/products")
	public Product saveProduct(@RequestBody Product product) {
		Product prod=productRepository.save(product);
		return prod;
		
	}
	
	@DeleteMapping("/ims/products/{id}")
	public String deleteProduct(@PathVariable int id) {
		
		   productRepository.deleteById(id);
		   
		   return "Deleted";
	}
	
	
	@PutMapping("/ims/products/{id}")
	public Product updateOneProductById(@PathVariable int id,@RequestBody Product  prod)
	{
		return  productRepository.save(prod);
		
	}

	

		
		
		@GetMapping("/ims/products/getport")
		public String getMapping() {
			System.out.println("IN GET PORT");
			String port=env.getProperty("local.server.port");
			return  ("From  IMS  PORT:"+port);
		}
	
	
	
	
}


