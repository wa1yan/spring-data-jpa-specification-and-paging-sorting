package com.waiyanhtet.jpa.specification.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.waiyanhtet.jpa.specification.entity.State;

import jakarta.transaction.Transactional;

@Repository
public interface StateRepo extends JpaRepositoryImplementation<State, Integer> {

	@Transactional
	void deleteByRegion(String region);
}
