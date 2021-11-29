package com.crosscomerce.orderarray.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crosscomerce.orderarray.services.NumbersService;

@RestController
@RequestMapping("numbers")
public class NumbersController {

	@Autowired
	private NumbersService numberService;
	
	@GetMapping("ordered")
	public ResponseEntity<ArrayList<Double>> getNumbersOrdered(){ 
		
		return ResponseEntity.ok(this.numberService.orderArray());
		
	}
	
	@GetMapping("unordered")
	public ResponseEntity<ArrayList<Double>> getNumbersUnordered(){ 
		
		return ResponseEntity.ok(this.numberService.getNumbers(1));
		
	}
}
