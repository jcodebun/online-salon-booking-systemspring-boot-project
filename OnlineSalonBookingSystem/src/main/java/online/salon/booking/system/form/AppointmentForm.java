package online.salon.booking.system.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import online.salon.booking.system.dto.AppointmentDTO;
import online.salon.booking.system.dto.BaseDTO;
import online.salon.booking.system.util.DataUtility;


@Setter
@Getter
public class AppointmentForm extends BaseForm{
	
	private String employeeName;
	
	private String userName;
	private String fromBookingDate;
	private String fromTime;
	private String toBookingDate;
	private String toTime;
	private String status;
	private long userid;	
    private long productid;	
	private long serviceid;	
	private long empid;
	private long price;

	@Override
	public BaseDTO getDTO() {
		AppointmentDTO bean = new AppointmentDTO();
		bean.setEmployeeName(employeeName);
		bean.setUserName(userName);
		bean.setFromBookingDate(DataUtility.getDate1(fromBookingDate));
		bean.setToBookingDate(DataUtility.getDate1(toBookingDate));
		
		bean.setToTime(toTime);
		bean.setFromTime(fromTime);
		bean.setStatus(status);
	    bean.setUserid(userid);
	    bean.setProductid(productid);
	    bean.setServiceid(serviceid);
	    bean.setEmpid(empid);
	    bean.setPrice(price);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		AppointmentDTO dto = (AppointmentDTO)getDTO();
		employeeName = dto.getEmployeeName();
		userName = dto.getUserName();
		fromBookingDate = DataUtility.getStringData(dto.getFromBookingDate());
		toBookingDate = DataUtility.getStringData(dto.getToBookingDate());
		fromTime = dto.getFromTime();
		toTime = dto.getToTime();
		status = dto.getStatus();
		userid = dto.getUserid();
		productid = dto.getProductid();
		serviceid = dto.getServiceid();
		empid = dto.getEmpid();
		price = dto.getPrice();
	}

}
