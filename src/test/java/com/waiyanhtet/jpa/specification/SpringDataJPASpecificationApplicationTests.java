package com.waiyanhtet.jpa.specification;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.waiyanhtet.jpa.specification.dto.StateDTO;
import com.waiyanhtet.jpa.specification.entity.State;
import com.waiyanhtet.jpa.specification.repo.StateRepo;
import com.waiyanhtet.jpa.specification.service.StateCriteriaService;
import com.waiyanhtet.jpa.specification.service.StateSpecificationService;

@SpringBootTest
class SpringDataJPASpecificationApplicationTests {
	
	@Autowired
	private StateCriteriaService stateCriteriaService;

	@Autowired
	private StateSpecificationService stateSpecificationService;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Disabled
	@Test
	void test_criteria() {
		var result = stateCriteriaService.findByRegion("Lower");
		for(State s : result) {
			System.out.println(s.getName() + " " + s.getPopulation());
		}
	}
	
	@Disabled
	@Test
	void test_spec_find_by_region() {
		var result = stateSpecificationService.findByRegion("Lower");
		for(State s : result) {
			System.out.println(s.getName() + " " + s.getPopulation());
		}
	}

	@Disabled
	@Test
	void test_spec_count_by_region() {
		var count = stateSpecificationService.findCountByRegion("Lower");
		assertThat(count, is(3L));
	}
	
	@Disabled
	@Test
	void test_spec_find_dto_by_region() {
		var result = stateSpecificationService.findDTOByRegion("Lower");
		for(StateDTO s : result) {
			System.out.println("%d %s %s".formatted(s.getId(), s.getName() ,s.getRegion()));
		}
	}
	
	@Disabled
	@Test
	void test_spec_delete_by_region() {
		stateRepo.deleteByRegion("Lower");
	}
	
	@Test
	void test_spec_find_by_district_name_like() {
		var result = stateSpecificationService.findByDistrictNameLike("Kanbe");
		System.out.println(result.toString());
	}
}
