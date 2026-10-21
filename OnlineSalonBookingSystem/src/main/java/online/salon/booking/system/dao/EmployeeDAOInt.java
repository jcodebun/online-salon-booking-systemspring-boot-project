package online.salon.booking.system.dao;

import java.util.List;

import online.salon.booking.system.dto.EmployeeDTO;
import online.salon.booking.system.exception.DuplicateRecordException;

public interface EmployeeDAOInt {
	
public long add(EmployeeDTO dto) throws DuplicateRecordException;
	
	public void Update(EmployeeDTO dto) throws DuplicateRecordException;
	
	public void Delete(long id);

	public List<EmployeeDTO> list();
	
	public EmployeeDTO FindByPk(long pk);
	
	public EmployeeDTO FindByEmail(String email);
	
	public EmployeeDTO Authentication(EmployeeDTO dto);
	
	public List<EmployeeDTO> search(EmployeeDTO dto);

}
