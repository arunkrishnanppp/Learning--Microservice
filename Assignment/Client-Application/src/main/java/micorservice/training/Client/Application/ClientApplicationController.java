package micorservice.training.Client.Application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;





@RestController
public class ClientApplicationController {

//	@Autowired
//	private DiscoveryClient discoveryClient;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ClientApplication.class);
	
//	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	KafkaTemplate<String, Product> kafkaTemplateProduct;
	
//	List<ServiceInstance> instances=((DiscoveryClient) discoveryClient).getInstances("Inventory-Management-System");
//	ServiceInstance serviceInstance=instances.get(0);
//	
//	String IMS_URL=serviceInstance.getUri().toString();
	
	
	private static final String TOPIC="PRODUCT_TOPIC";
	private String IMS_URL="http://Inventory-Management-System/ims/products";
	
	
	@GetMapping("/client/products")
	public List<Product> getAllProductFromIMS() {
		LOGGER.info("IN CLEINT CALLING IMS");
		//String url="http://Inventory-Management-System/ims/products";
		
		//List<Product> response= (List<Product>) this.restTemplate.getForObject(url, Product.class);
	//	System.out.println(this.restTemplate.getForObject(url, List.class));
		List<Product> response=this.restTemplate.getForObject(IMS_URL, List.class);
		
		//String port=env.getProperty("local.server.port");
		//return "PORT:"+port+" Server one response:"+responseString;
				return response;
	}
	@GetMapping("/client/products/{id}")
	public Product getOneProductFromIMS(@PathVariable int id) {
		//String url="http://Inventory-Management-System/ims/products/"+id;
		//List<Product> response= (List<Product>) this.restTemplate.getForObject(url, Product.class);
		//System.out.println(this.restTemplate.getForObject(url, Product.class));
		String getURL=IMS_URL+"/"+id;
		Product response=this.restTemplate.getForObject(getURL, Product.class);
		System.out.println(response);
		
		//String port=env.getProperty("local.server.port");
		//return "PORT:"+port+" Server one response:"+responseString;
				return response;
				
				
	}
	@PostMapping("/client/products")
	public Product saveOneProductToIMS(@RequestBody Product prod) {
		
		//String url="http://Inventory-Management-System/ims/products";
		//String postUrl=IMS_URL;
		
		//System.out.println(this.restTemplate.postForObject(url, prod, Product.class));
		Product response=this.restTemplate.postForObject(IMS_URL, prod, Product.class);
		System.out.println(response);
				return response;
				
				
	}
	@PutMapping("/client/products/{id}")
	public String updateOneProductinIMS(@RequestBody Product prod,@PathVariable int id) {
		String putURL=IMS_URL+"/"+id;
		//String url="http://Inventory-Management-System/ims/products/"+id;
		this.restTemplate.put(putURL,prod);
		
		return "UPDATED";
	}
	
	@DeleteMapping("/client/products/{id}")
	public String deleteOneProductFromIMS(@PathVariable int id) {
		String deleteURL=IMS_URL+"/"+id;
		//String url="http://Inventory-Management-System/ims/products/"+id; 
		this.restTemplate.delete(deleteURL);
		return "Deleted";
	}
	
	@PostMapping("/client/products/publish")
	public String kafkaProducer(@RequestBody Product prod) {
		
		Product product=prod;
		kafkaTemplateProduct.send(TOPIC, product);
		
		return "Product Published Successfully";
				
	}
	@GetMapping("/client/products/getport")
	public String getMapping() {
		System.out.println("IN GET PORT");
		String getPortUrl=IMS_URL+"/getport";
		return this.restTemplate.getForObject(getPortUrl, String.class);
	}
	@GetMapping("/client/check")
	public String getMappingCheck() {
	return "OK";
	}
	
}
