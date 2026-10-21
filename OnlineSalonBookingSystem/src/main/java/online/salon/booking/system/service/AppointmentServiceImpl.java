package online.salon.booking.system.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.salon.booking.system.dao.AppointmentDAOInt;
import online.salon.booking.system.dto.AppointmentDTO;
import online.salon.booking.system.exception.DuplicateRecordException;
import online.salon.booking.system.util.DataUtility;


@Service
public class AppointmentServiceImpl implements AppointmentServiceInt{
	
	@Autowired
	private AppointmentDAOInt dao;
	
	
	@Override
	public long add(AppointmentDTO dto) throws DuplicateRecordException {
	     System.out.println("AppointmentDTO: "+dto.getToBookingDate()+ " : "+ dto.getToBookingDate());
	
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional
	public void Update(AppointmentDTO dto) throws DuplicateRecordException {
		dao.Update(dto);;
		
	}

	@Override
	@Transactional
	public void Delete(long id) {
		dao.Delete(id);		
	}

	@Override
	public List<AppointmentDTO> list() {
		List<AppointmentDTO> list =  dao.list();
		  return list;
	}

	@Override
	public List<AppointmentDTO> appointmentlist(long userid) {
		 List<AppointmentDTO> list =  dao.appointmentlist(userid);
			return list;
	}
	
	@Override
	public List<AppointmentDTO> empappointmentlist(long empid) {
		List<AppointmentDTO> list =  dao.appointmentemplist(empid);
		return list;
	}



	@Override
	public AppointmentDTO FindByPk(long pk) {
		AppointmentDTO dto = dao.FindByPk(pk);
		return dto;
	}

	@Override
	public AppointmentDTO FindByEmp(long userid) {
		AppointmentDTO order = dao.FindByEmp(userid);
		return order;
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto) {
		List<AppointmentDTO> list =  dao.search(dto);
		 return list;
	}

	@Override
	@Transactional
	public void Cancle(long id, String status) {
		dao.Cancle(id,status);		
		
	}

	@Override
	@Transactional
	public void Confirm(long id, String status) {
		dao.Confirm(id, status);
		
	}

	@Override
	@Transactional
	public void Paid(long id, String status) {
		dao.Paid(id, status);
		
	}

	@Override
	public List<AppointmentDTO> empappointmentlist(String empName) {
		 List<AppointmentDTO> list =  dao.empappointmentlist(empName);
			return list;
	}

	@Override
	public AppointmentDTO FindByTime(String time) {
		AppointmentDTO order = dao.FindByTime(time);
		return order;
	}
	
	
	@Scheduled(fixedDelay = 10000)
	@Transactional
	 public void scheduleFixedDelayTask() {
	     
		List<AppointmentDTO> appointmentList = dao.list();     
 
	     for (AppointmentDTO appointmentDTO : appointmentList) {
	    	 
	      String toBookingDate = DataUtility.getDateString1(appointmentDTO.getToBookingDate());
	      
	      String toTime = appointmentDTO.getToTime();
	      String finalBookingTime = toBookingDate+" "+toTime+":00";
	      System.out.println("To Booking Date and time: "+finalBookingTime);
	      System.out.println("Current Time: "+DataUtility.getCurrentTimestamp());
	      
	      int b3 = DataUtility.getCurrentTimestamp().compareTo(DataUtility.getDateAndTime(finalBookingTime));  
	      System.out.println("Compare Status: "+b3);
	      
	       if(b3>=0){   
	    	  try {
	    		  
	    		appointmentDTO.setStatus("Available");
	    		System.out.println("b3: "+b3);
	    		System.out.println("appointmentDTO: "+appointmentDTO);
				dao.Update(appointmentDTO);
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	     }
	     
		
	}

	
}
