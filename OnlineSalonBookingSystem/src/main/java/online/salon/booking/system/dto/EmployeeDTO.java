package online.salon.booking.system.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "employee")
public class EmployeeDTO extends BaseDTO{

	@Column(name = "USERNAME",length = 255)
	private String userName;
	
	@Column(name = "EMAIL",length = 255)
	private String email;
	
	@Column(name = "PASSWORD",length = 255)
	private String password;
	
	@Column(name = "GENDER",length = 255)
	private String gender;
	
	@Column(name = "PHONENO",length = 255)
	private String phoneNo;
	
	@Column(name = "address",length = 3000)
	private String address;
	
	private long roleid;
	
	@Column(name = "ROLE_NAME",length = 255)
	private String rolename;
	
	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return userName;
	}
}
