package com.waiyanhtet.jpa.specification.dto;

import com.waiyanhtet.jpa.specification.entity.State.Type;

public interface StateDTO {

	int getId();
	String getName();
	Type getType();
	String getRegion();
	int getPopulation();
	String getCapital();
}
