package online.salon.booking.system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.salon.booking.system.dao.EmployeeDAOInt;
import online.salon.booking.system.dto.EmployeeDTO;
import online.salon.booking.system.exception.DuplicateRecordException;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInt{
	
	@Autowired
	public EmployeeDAOInt dao;

	@Override
	public long add(EmployeeDTO dto) throws DuplicateRecordException {
		EmployeeDTO exisBean = dao.FindByEmail(dto.getEmail());
		if (exisBean != null){
			throw new DuplicateRecordException("Email Id is Already Exist");
		}
		
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional
	public void Update(EmployeeDTO dto) throws DuplicateRecordException {
		dao.Update(dto);
		
	}

	@Override
	@Transactional
	public void Delete(long id) {
		dao.Delete(id);
		
	}

	@Override
	public List<EmployeeDTO> list() {
		 List<EmployeeDTO> list =  dao.list();
		  return list;
	}

	@Override
	public EmployeeDTO FindByPk(long pk) {
		EmployeeDTO dto = dao.FindByPk(pk);
		return dto;
	}

	@Override
	public EmployeeDTO FindByEmail(String email) {
		EmployeeDTO dto = dao.FindByEmail(email);
		return dto;
	}

	@Override
	public EmployeeDTO Authentication(EmployeeDTO dto) {
		dto = dao.Authentication(dto);
		return dto;
	}

	@Override
	public List<EmployeeDTO> search(EmployeeDTO dto) {
		 List<EmployeeDTO> list =  dao.search(dto);
		 return list;
	}

}
