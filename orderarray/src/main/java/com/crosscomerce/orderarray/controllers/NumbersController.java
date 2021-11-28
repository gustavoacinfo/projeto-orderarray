package com.crosscomerce.orderarray.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crosscomerce.orderarray.models.Numbers;
import com.crosscomerce.orderarray.services.NumbersService;

@RestController
@RequestMapping("numbers")
public class NumbersController {

	@Autowired
	private NumbersService numberService;
	
	@GetMapping("/{numpage}")
	public ResponseEntity<Numbers> getNumbers(@PathVariable Integer numpage){
		
		Numbers number = this.numberService.getNumbers(numpage);
		
		return ResponseEntity.ok(number);
		
	}
	
	
}
