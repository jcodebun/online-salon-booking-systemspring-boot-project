package online.salon.booking.system.service;

import java.util.List;

import online.salon.booking.system.dto.AppointmentDTO;
import online.salon.booking.system.dto.UserDTO;
import online.salon.booking.system.exception.DuplicateRecordException;



public interface UserServiceInt {
	
	public long add(UserDTO dto) throws DuplicateRecordException;
	
	public void Update(UserDTO dto) throws DuplicateRecordException;
	
	public void Delete(long id);

	public List<UserDTO> list();
	
	public List<UserDTO> employeelist(long empid);
	
	public List<UserDTO> userlist(long userid);
	
	public UserDTO FindByPk(long pk);
	
	public UserDTO FindByEmail(String email);
	
	public UserDTO FindByAccountnumber(String accountnumber);
	
	public UserDTO Authentication(UserDTO dto);
	
	public List<UserDTO> search(UserDTO dto);
	
	public boolean changePassword(Long id, String oldPassword, String newPassword);

	List<UserDTO> employeelist();

	
}
