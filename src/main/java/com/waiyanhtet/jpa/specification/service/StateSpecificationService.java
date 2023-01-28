package com.waiyanhtet.jpa.specification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.waiyanhtet.jpa.specification.dto.StateDTO;
import com.waiyanhtet.jpa.specification.entity.State;
import com.waiyanhtet.jpa.specification.repo.StateRepo;

import jakarta.persistence.criteria.JoinType;
import jakarta.transaction.Transactional;

@Service
public class StateSpecificationService {

	@Autowired
	private StateRepo stateRepo;
	
	public List<State> findByRegion(String region) {
		return stateRepo.findAll(byRegion(region));
	}
	
	public Long findCountByRegion(String region) {
		return stateRepo.count(byRegion(region));
	}
	
	public List<StateDTO> findDTOByRegion(String region) {
		return stateRepo.findBy(byRegion(region), 
					query -> query.project("id","name","region")
								  .as(StateDTO.class).all());
	}
	
	public List<State> findByDistrictNameLike(String name) {
		Specification<State> specification = (root, query, cb) -> {
			var join = root.join("districts", JoinType.INNER);
			return cb.like(cb.lower(join.get("name")), name.toLowerCase().concat("%"));
		};
		return stateRepo.findAll(specification);
	}
	
	@Transactional
	public void deleteByRegion(String region) {
		stateRepo.delete(byRegion(region));
	}
	
	private Specification<State> byRegion(String region){
		return (root, query, cb) -> cb.equal(root.get("region"), region);
	}
}
