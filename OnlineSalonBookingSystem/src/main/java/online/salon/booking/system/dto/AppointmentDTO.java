package online.salon.booking.system.dto;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "appointment")
@Entity
public class AppointmentDTO extends BaseDTO{
	
	@Column(name = "employeeName",length = 200)
	private String employeeName;
	
	@Column(name = "userName",length = 200)
	private String userName;
	
	private long userid;
	
	private long productid;
	
	private long serviceid;
	
	private long empid;
	
	private long price;
	
	private Date fromBookingDate;
	private String fromTime;
	private Date toBookingDate;
	private String toTime;
	
	@Column(name = "status",length = 200)
	private String status;
	

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [employeeName=" + employeeName + ", userName=" + userName + ", userid=" + userid
				+ ", productid=" + productid + ", serviceid=" + serviceid + ", empid=" + empid + ", fromBookingDate="
				+ fromBookingDate + ", fromTime=" + fromTime + ", toBookingDate=" + toBookingDate + ", toTime=" + toTime
				+ ", status=" + status + "]";
	}
	
	

}
