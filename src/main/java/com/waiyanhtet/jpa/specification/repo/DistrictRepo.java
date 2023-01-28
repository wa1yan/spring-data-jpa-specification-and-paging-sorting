package com.waiyanhtet.jpa.specification.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.waiyanhtet.jpa.specification.entity.District;

public interface DistrictRepo extends JpaRepositoryImplementation<District, Integer> {
	
	@Query(value = "select d from District d where d.state.region = ?1 order by name desc")
	List<District> findByRegion(String region);
	
	List<District> findByStateRegionOrderByName(String region);
	
	@Query(value = "select d from District d where d.state.region = ?1")
	List<District> findByRegionWithSort(String region, Sort sort);
	
	@Query(value = "select d from District d where d.state.region = ?1")
	Page<District> findByRegion(String region, Pageable pageable);

}
