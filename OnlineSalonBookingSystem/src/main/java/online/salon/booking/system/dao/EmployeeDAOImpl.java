package online.salon.booking.system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import online.salon.booking.system.dto.EmployeeDTO;
import online.salon.booking.system.exception.DuplicateRecordException;


@Repository
public class EmployeeDAOImpl implements EmployeeDAOInt{
	
	  @Autowired
	  public EntityManager entitymanger;

	@Override
	public long add(EmployeeDTO dto) throws DuplicateRecordException {
		Session session = entitymanger.unwrap(Session.class);
		long pk = (long) session.save(dto);
		return pk;
	}

	@Override
	public void Update(EmployeeDTO dto) throws DuplicateRecordException {
		Session session = entitymanger.unwrap(Session.class);
		session.merge(dto);
		
	}

	@Override
	public void Delete(long id) {
		Session session = (Session) entitymanger.unwrap(Session.class);
		Query<EmployeeDTO> query = session.createQuery("delete from EmployeeDTO where id= "+id+"");
	    query.executeUpdate();
		
	}

	@Override
	public List<EmployeeDTO> list() {
		Session session = entitymanger.unwrap(Session.class);
		Query<EmployeeDTO> query = session.createQuery("from EmployeeDTO", EmployeeDTO.class);
		List<EmployeeDTO> list = query.getResultList();
		return list;
	}

	@Override
	public EmployeeDTO FindByPk(long pk) {
		Session session = entitymanger.unwrap(Session.class);
		EmployeeDTO dto = session.get(EmployeeDTO.class, pk);
		return dto;
	}

	@Override
	public EmployeeDTO FindByEmail(String email) {
		Session session = entitymanger.unwrap(Session.class);
		Criteria criteria = session.createCriteria(EmployeeDTO.class);
		criteria.add(Restrictions.eq("email", email));
		EmployeeDTO dto =  (EmployeeDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public EmployeeDTO Authentication(EmployeeDTO dto) {
		Session session = entitymanger.unwrap(Session.class);
		Query<EmployeeDTO> query = session.createQuery("from EmployeeDTO where email=:email and password=:password", EmployeeDTO.class);
		query.setParameter("email",dto.getEmail());
		query.setParameter("password",dto.getPassword());
		dto = null;
		try {
			dto = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return dto;
	}

	@Override
	public List<EmployeeDTO> search(EmployeeDTO dto) {
		Session session = entitymanger.unwrap(Session.class);
		Criteria criteria = session.createCriteria(EmployeeDTO.class);
		if (dto != null) {
			if (dto.getId()>0) {
              criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getEmail() != null && dto.getEmail().length()>0) {
				criteria.add(Restrictions.like("email", dto.getEmail() + "%"));
			}
			
		}
		return criteria.list();
	}

}
