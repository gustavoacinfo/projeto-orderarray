package com.crosscomerce.orderarray.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.crosscomerce.orderarray.models.Numbers;

import reactor.core.publisher.Mono;

@Service
public class NumbersService {
	
	@Autowired
	private WebClient webClient;
	
	public ArrayList<Double> orderArray() { 
		
		ArrayList<Double> orderArray = new ArrayList<>(this.getNumbers(1));
		
		
		for (int i = 0; i < orderArray.size(); i++) {
	        for (int j = i + 1; j < orderArray.size(); j++) {
	            Double x = 0.0;
	            if (orderArray.get(i) > orderArray.get(j)) {
	                x = orderArray.get(i);
	                orderArray.set(i, orderArray.get(j)); 
	                orderArray.set(j, x);
	            }
	        }
	    }
		
		return orderArray;
	
	}
	
	
	public ArrayList<Double> getNumbers(Integer numPage) {
		
		Mono<Numbers> monoNumbers = this.webClient
			.method(HttpMethod.GET)
			.uri("/numbers?page={numpage}", numPage)
			.retrieve()
			.bodyToMono(Numbers.class);
			
		Numbers numbers = monoNumbers.block();
		
		ArrayList<Double> allNumbers = new ArrayList<>(numbers.getNumbers());
		
		if (numbers.getNumbers().size() > 0 && numPage <= 20) { // && numPage <= 2
			allNumbers.addAll(this.getNumbers(++numPage));
		}
		
		return allNumbers;
	}

}
