package online.salon.booking.system.dao;

import java.util.List;

import online.salon.booking.system.dto.OrderDTO;
import online.salon.booking.system.exception.DuplicateRecordException;

public interface OrderDAOInt {
	
	public long add(OrderDTO dto) throws DuplicateRecordException;

	public void Update(OrderDTO dto) throws DuplicateRecordException;

	public void Delete(long id);
	
	public void delete(OrderDTO dto);

	public List<OrderDTO> list();
	
	public List<OrderDTO> userorderlist(long userid);

	public OrderDTO FindByPk(long pk);

	public OrderDTO FindByOrderId(long userID);

	public List<OrderDTO> search(OrderDTO dto);
	
	public void Cancle(long id,String status);
	
	public void Confirm(long id,String status);
	
	public void Paid(long id,String status);

}
