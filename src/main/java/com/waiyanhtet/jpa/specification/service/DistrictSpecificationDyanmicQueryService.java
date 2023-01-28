package com.waiyanhtet.jpa.specification.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.waiyanhtet.jpa.specification.entity.District;
import com.waiyanhtet.jpa.specification.repo.DistrictRepo;

@Service
public class DistrictSpecificationDyanmicQueryService {

	@Autowired
	private DistrictRepo repo;
	
	public List<District> search(String stateName, Integer stateId, String region){
		List<Specification<District>> list = new ArrayList<>();
		
		if(StringUtils.hasLength(stateName)) {
			list.add((root, query, cb) -> cb.like(cb.lower(root.get("state").get("name")), stateName.toLowerCase().concat("%")));
		}
		
		if(null != stateId && stateId > 0) {
			list.add((root, query, cb) -> cb.equal(root.get("state").get("id"), stateId));	
		}
		
		if(StringUtils.hasLength(region)) {
			list.add((root, query, cb) -> cb.equal(cb.lower(root.get("state").get("region")), region.toLowerCase()));
		}
		
		return repo.findAll(Specification.allOf(list));
	}
	
	public List<District> findByRegionOrderByName(String region) {
		Specification<District> spec = (root, query, cb) -> {
			query.orderBy(cb.asc(root.get("name")));
			return cb.equal(root.get("state").get("region"), region);
		};
		return repo.findAll(spec);
	}
	
	public List<District> findByRegionWithSort(String region, Sort sort) {
		Specification<District> spec = (root, query, cb) -> {
			return cb.equal(root.get("state").get("region"), region);
		};
		return repo.findBy(spec, query -> query.sortBy(sort).all());
	}
	
	public Page<District> findByRegionWithSort(String region, Pageable page) {
		Specification<District> spec = (root, query, cb) -> {
			return cb.equal(root.get("state").get("region"), region);
		};
		return repo.findBy(spec, query -> query.page(page));
	}
}
