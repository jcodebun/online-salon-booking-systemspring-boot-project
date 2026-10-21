package online.salon.booking.system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.salon.booking.system.dao.OrderDAOInt;
import online.salon.booking.system.dto.OrderDTO;
import online.salon.booking.system.exception.DuplicateRecordException;


@Service
public class OrderServiceImpl implements OrderServiceInt{
	@Autowired
	private OrderDAOInt dao;


	@Override
	public long add(OrderDTO dto) throws DuplicateRecordException {
//		OrderDTO exisBean = dao.FindByOrderId(dto.getUserid());
//		if (exisBean != null){
//			long pk = dao.add(dto);
//		}
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	public void Update(OrderDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void Delete(long id) {
		dao.Delete(id);		
	}

	@Override
	public List<OrderDTO> list() {
		List<OrderDTO> list =  dao.list();
		  return list;
	}

	@Override
	public List<OrderDTO> userorderlist(long userid) {
		 List<OrderDTO> list =  dao.userorderlist(userid);
			return list;
	}

	@Override
	public OrderDTO FindByPk(long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO FindByOrderId(long userID) {
		OrderDTO cartDto = dao.FindByOrderId(userID);
		return cartDto;
	}

	@Override
	public List<OrderDTO> search(OrderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Cancle(long id, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Confirm(long id, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Paid(long id, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void delete(OrderDTO dto) {
		dao.delete(dto);
		
	}
}
