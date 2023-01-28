package com.waiyanhtet.jpa.specification;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.waiyanhtet.jpa.specification.repo.DistrictRepo;
import com.waiyanhtet.jpa.specification.service.DistrictSpecificationDyanmicQueryService;

@SpringBootTest
public class PaganationTest {

	@Autowired
	private DistrictRepo repo;
	
	@Autowired
	private DistrictSpecificationDyanmicQueryService service;
	
	@Disabled
	@Test
	void order_by_test() {
		var result = repo.findByRegion("Lower");
		result.forEach(System.out::println);
	}
	
	@Disabled
	@Test
	void service_order_by_test() {
		var result = service.findByRegionOrderByName("Lower");
		result.forEach(System.out::println);
	}
	
	@Disabled
	@Test
	void repo_sort_by_test() {
		var result = repo.findByRegionWithSort("Lower", Sort.by("name").descending());
		result.forEach(System.out::println);
	}
	
	@Disabled
	@Test
	void service_sort_by_test() {
		var result = service.findByRegionWithSort("Lower", Sort.by("name").descending());
		result.forEach(System.out::println);
	}
	
	@Disabled
	@Test
	void repo_pageable_test() {
		var pageable = PageRequest.of(3, 3, Sort.by("name"));
		var result = repo.findByRegion("Lower", pageable);
		System.out.println("Elements : " + result.getNumberOfElements());
		System.out.println("Total Elements : " + result.getTotalElements());
		System.out.println("Total Pages : " + result.getTotalPages());
		System.out.println("Page Number : " + result.getNumber());
		result.getContent().forEach(System.out::println);
	}
	
	@Test
	void service_pageable_test() {
		var pageable = PageRequest.of(0, 3, Sort.by("name").descending());
		var result = repo.findByRegion("Lower", pageable);
		System.out.println("Elements : " + result.getNumberOfElements());
		System.out.println("Total Elements : " + result.getTotalElements());
		System.out.println("Total Pages : " + result.getTotalPages());
		System.out.println("Page Number : " + result.getNumber());
		result.getContent().forEach(System.out::println);
	}
}
