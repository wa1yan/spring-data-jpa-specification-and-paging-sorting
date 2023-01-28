package com.waiyanhtet.jpa.specification.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.waiyanhtet.jpa.specification.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StateCriteriaService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<State> findByRegion(String region) {
		
		var criteriBuilder = entityManager.getCriteriaBuilder();
		var criteriaQuery = criteriBuilder.createQuery(State.class);
		var root = criteriaQuery.from(State.class);		
		
		var predicate = criteriBuilder.like(root.get("region"), region);
		criteriaQuery.where(predicate);
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
