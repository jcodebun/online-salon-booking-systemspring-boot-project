package online.salon.booking.system.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import online.salon.booking.system.dto.OrderDTO;
import online.salon.booking.system.exception.DuplicateRecordException;


@Repository
public class OrderDAOImpl implements OrderDAOInt{
	
	@Autowired
    public EntityManager entitymanger;

	@Override
	public long add(OrderDTO dto) throws DuplicateRecordException {
		Session session = entitymanger.unwrap(Session.class);
		long pk = (long) session.save(dto);
		return pk;
	}

	@Override
	public void Update(OrderDTO dto) throws DuplicateRecordException {
	}

	@Override
	@Transactional
	public void Delete(long id) {
		Session session = (Session) entitymanger.unwrap(Session.class);
		Query<OrderDTO> query = session.createQuery("delete from OrderDTO where id= "+id+"");
	    query.executeUpdate();	
		
	}

	@Override
	public List<OrderDTO> list() {
		Session session = entitymanger.unwrap(Session.class);
		Query<OrderDTO> query = session.createQuery("from OrderDTO", OrderDTO.class);
		List<OrderDTO> list = query.getResultList();
		return list;
	}

	@Override
	public List<OrderDTO> userorderlist(long userid) {
		System.out.println("in DAO");
		Session session = entitymanger.unwrap(Session.class);
		Query<OrderDTO> query = session.createQuery("from OrderDTO as b where b.userid = ?1 ", OrderDTO.class);
		query.setParameter(1, userid);
		List<OrderDTO> Rlist = query.getResultList();
		return Rlist;
	}

	@Override
	public OrderDTO FindByPk(long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO FindByOrderId(long userID) {
		Session session = entitymanger.unwrap(Session.class);
		Criteria criteria = session.createCriteria(OrderDTO.class);
		criteria.add(Restrictions.eq("userid",userID));
		OrderDTO dto =  (OrderDTO) criteria.uniqueResult();
		return dto;
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
	public void delete(OrderDTO dto) {
		entitymanger.remove(entitymanger.contains(dto) ? dto : entitymanger.merge(dto));
		
	}


}
