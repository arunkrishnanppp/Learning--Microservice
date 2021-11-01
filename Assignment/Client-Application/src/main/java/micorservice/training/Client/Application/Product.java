package micorservice.training.Client.Application;



public class Product {
	
	
	private int id;
	private String product_name;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product(int id, String product_name, double price) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.price = price;
	}
	public Product() {
		super();
	}
	
	
	

}
