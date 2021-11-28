package com.crosscomerce.orderarray.services;

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
	
	public Numbers getNumbers(Integer numPage) {
		
		Mono<Numbers> monoNumbers = this.webClient
			.method(HttpMethod.GET)
			.uri("/numbers?page={numpage}", numPage)
			.retrieve()
			.bodyToMono(Numbers.class);
		
		Numbers number = monoNumbers.block();
		
		return number;
	}

}
