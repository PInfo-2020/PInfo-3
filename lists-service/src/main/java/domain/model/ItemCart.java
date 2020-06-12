package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@IdClass(ItemCartID.class)
@Table(name="ItemCart")
public class ItemCart implements Serializable{
	
	public ItemCart() {}
	
	public ItemCart(@NotNull String userID,@NotNull int ingredientID,@NotNull double quantity) {
		super();
		this.userID = userID;
		this.ingredientID = ingredientID;
		this.quantity = quantity;
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="userID", nullable=false)
	private String userID;
	
	@Id
	@Column(name="ingredientID", nullable=false)
	private int ingredientID;
	
	@Column(name="quantity", nullable=false)
	private double quantity;
	
}
