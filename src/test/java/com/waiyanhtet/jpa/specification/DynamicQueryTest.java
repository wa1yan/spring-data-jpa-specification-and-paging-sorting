package com.waiyanhtet.jpa.specification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.waiyanhtet.jpa.specification.service.DistrictSpecificationDyanmicQueryService;

@SpringBootTest
public class DynamicQueryTest {
	
	@Autowired
	private DistrictSpecificationDyanmicQueryService service;
	
	@Test
	void test() {
		var result = service.search(null, 0, "Upper");
		result.forEach(System.out::println);
		
	}

}
