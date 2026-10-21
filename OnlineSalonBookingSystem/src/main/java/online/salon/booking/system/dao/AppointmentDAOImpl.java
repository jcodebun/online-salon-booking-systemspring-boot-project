package online.salon.booking.system.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import online.salon.booking.system.dto.AppointmentDTO;
import online.salon.booking.system.exception.DuplicateRecordException;


@Repository
public class AppointmentDAOImpl implements AppointmentDAOInt{
	
	@Autowired
    public EntityManager entitymanger;

	@Override
	public long add(AppointmentDTO dto) throws DuplicateRecordException {
		Session session = entitymanger.unwrap(Session.class);
		long pk = (long) session.save(dto);
		return pk;
	}

	@Override
	public void Update(AppointmentDTO dto) throws DuplicateRecordException {
		Session session = entitymanger.unwrap(Session.class);
		session.merge(dto);		
		
	}

	@Override
	public void Delete(long id) {
		Session session = (Session) entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("delete from AppointmentDTO where id= "+id+"");
	    query.executeUpdate();	
		
	}

	@Override
	public List<AppointmentDTO> list() {
		Session session = entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO", AppointmentDTO.class);
		List<AppointmentDTO> list = query.getResultList();
		return list;
	}
	


	@Override
	public List<AppointmentDTO> appointmentlist(long userid) {
		Session session = entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO as b where b.userid = ?1 ", AppointmentDTO.class);
		query.setParameter(1, userid);
		List<AppointmentDTO> Rlist = query.getResultList();
		return Rlist;
	}
	
	@Override
	public List<AppointmentDTO> appointmentemplist(long empid) {
		Session session = entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO as b where b.empid = ?1 ", AppointmentDTO.class);
		query.setParameter(1, empid);
		List<AppointmentDTO> Rlist = query.getResultList();
		return Rlist;
	}

	
	
	@Override
	public AppointmentDTO FindByPk(long pk) {
		Session session = entitymanger.unwrap(Session.class);
		AppointmentDTO dto = session.get(AppointmentDTO.class, pk);
		return dto;
	}

	@Override
	public AppointmentDTO FindByEmp(long userid) {
		Session session = entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO as u where u.userid=?1",AppointmentDTO.class);
	//	query.setParameter(1, empName);
		query.setParameter(1, userid);
		//System.out.println("empName:" + empName);
		System.out.println(" userid:" + userid);
		Optional<AppointmentDTO> op = query.getResultStream().findFirst();
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto) {
		Session session = entitymanger.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AppointmentDTO.class);
		if (dto != null) {
			if (dto.getId()>0) {
              criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getEmployeeName() != null && dto.getEmployeeName().length()>0) {
				criteria.add(Restrictions.like("employeeName", dto.getEmployeeName() + "%"));
			}
		}
		return criteria.list();
	}

	@Override
	@Transactional
	public void Cancle(long id, String status) {
		Session session = (Session) entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("update AppointmentDTO set status = 'cancle'  where id= "+id+"");
	    query.executeUpdate();	
	}

	@Override
	@Transactional
	public void Confirm(long id, String status) {
		Session session = (Session) entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("update AppointmentDTO set status = 'confirm'  where id= "+id+"");
	    query.executeUpdate();	
		
	}

	@Override
	@Transactional
	public void Paid(long id, String status) {
		Session session = (Session) entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("update AppointmentDTO set status = 'paid'  where id= "+id+"");
	    query.executeUpdate();	
		
	}

	@Override
	public List<AppointmentDTO> empappointmentlist(String empName) {
		System.out.println("in DAO");
		Session session = entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO as b where b.employeeName = ?1 ", AppointmentDTO.class);
		query.setParameter(1, empName);
		List<AppointmentDTO> Rlist = query.getResultList();
		return Rlist;
	}

	@Override
	public AppointmentDTO FindByTime(String time) {
		Session session = entitymanger.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO as u where u.time=?1",AppointmentDTO.class);
		query.setParameter(1, time);
		Optional<AppointmentDTO> op = query.getResultStream().findFirst();
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

}
