package online.salon.booking.system.dao;

import java.util.List;

import online.salon.booking.system.dto.AppointmentDTO;
import online.salon.booking.system.exception.DuplicateRecordException;

public interface AppointmentDAOInt {

	public long add(AppointmentDTO dto) throws DuplicateRecordException;

	public void Update(AppointmentDTO dto) throws DuplicateRecordException;

	public void Delete(long id);

	public List<AppointmentDTO> list();

	public List<AppointmentDTO> appointmentlist(long userid);
	
	public List<AppointmentDTO> appointmentemplist(long empId);
	
	
	
	public List<AppointmentDTO> empappointmentlist(String empName);

	public AppointmentDTO FindByPk(long pk);

	public AppointmentDTO FindByEmp(long userid);
	
	public AppointmentDTO FindByTime(String time);

	public List<AppointmentDTO> search(AppointmentDTO dto);

	public void Cancle(long id, String status);

	public void Confirm(long id, String status);

	public void Paid(long id, String status);

}
