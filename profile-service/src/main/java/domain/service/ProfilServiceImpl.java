package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


import domain.model.Profil;
import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class ProfilServiceImpl implements ProfilService {

    @PersistenceContext(unitName = "ProfilPU")
    private EntityManager em;

	@Override
	public List<Profil> getDataUser() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Profil> criteria = builder.createQuery( Profil.class );
		criteria.from(Profil.class);
		return (List<Profil>)em.createQuery( criteria ).getResultList();
	}
    
/*
	@Override
	public Profil get(String lei) {
		return em.find(Profil.class, lei);
	}

	@Override
	public String name() {
		//log.info("Count the number of profiles");	
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = qb.createQuery(String.class);
		cq.select(qb.name(cq.from(Profil.class)));
		return em.createQuery(cq).getSingleResult();
	}*/
}
