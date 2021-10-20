package com.test.myproject.restwebservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.myproject.restwebservice.model.MyFilter;

@RestController
public class FilterController {

	@GetMapping("/filter")
	public MyFilter getFilter() {
		return new MyFilter("field1", "field2", "field3");
	}

	@GetMapping("/filter-list")
	public List<MyFilter> getFilters() {
		return Arrays.asList(new MyFilter("field1", "field2", "field3"), new MyFilter("field11", "field21", "field31"));
	}
}
