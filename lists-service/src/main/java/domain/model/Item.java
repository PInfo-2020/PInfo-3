package domain.model;


public class Item {
	
	private Long id;
	private double quantity;
	
	public Item(Long id, double quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	public Item() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	

}
