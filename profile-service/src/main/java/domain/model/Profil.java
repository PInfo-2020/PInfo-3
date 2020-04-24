package domain.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="profil")
public class Profil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="usernameID", unique=true)
	private String usernameID;
	
	//@Column(name="name", nullable=false)
//	private String name;
	
//	@Column(name="lastname", nullable=false)
	//private String lastname;
	
//	@Column(name="mark", nullable=true)
	//private float mark;

/*	@Id
	private String lei;
	@NotNull
	private String name;
	@Embedded	
	private Address legalAddress;
	@Embedded	
	private Registration registration;
	@NotNull
	private STATUS status;
*/
	

}
