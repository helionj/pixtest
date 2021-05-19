package com.helion.pixtest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helion.pixtest.services.PixCreateImmediateChargeService;

@RestController
@RequestMapping(value ="/createpix")
public class PixCreateImmediateChargeResource {

	@Autowired
	private PixCreateImmediateChargeService service;
	
	
	@GetMapping()
	public ResponseEntity<String> testPixImmediate(){
		
		String response = service.pixCreateImediateCharge();
		return ResponseEntity.ok(response);
	}
}
