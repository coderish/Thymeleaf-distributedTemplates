package com.coderish.tutorial.thymeleaf.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTemplateByteController {

	@Value("${template.welcome.path}")
	private String welcomeTemplatePath;
	@Value("${template.customer.path}")
	private String customerTemplatePath;

	@GetMapping("/welcome")
	public ResponseEntity<?> getWelcomeTemplate() throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(welcomeTemplatePath);
		return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
	}

	@GetMapping("/customer")
	public ResponseEntity<?> getCustomerTemplate() throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(customerTemplatePath);
		return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
	}
}
