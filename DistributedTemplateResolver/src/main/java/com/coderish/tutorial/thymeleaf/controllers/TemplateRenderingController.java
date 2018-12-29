package com.coderish.tutorial.thymeleaf.controllers;

import static com.coderish.tutorial.thymeleaf.constants.Constant.MESSAGE;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.coderish.tutorial.thymeleaf.model.Address;
import com.coderish.tutorial.thymeleaf.model.Customer;

@RestController
public class TemplateRenderingController {

	@Autowired
	private SpringTemplateEngine templatesEngine;

	@Value("${template.welcome.message}")
	private String message;

	@Value("${template.welcome.url}")
	private String welcomeTemplateUrl;
	@Value("${template.customer.url}")
	private String customerTemplateUrl;

	@GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<?> getWelcome() throws IOException {
		Context context = new Context();
		context.setVariable(MESSAGE, this.message);
		String resultHtml;

		try (Writer writer = new StringWriter();) {
			templatesEngine.process(welcomeTemplateUrl, context, writer);
			resultHtml = writer.toString();
		}
		return new ResponseEntity<String>(resultHtml, HttpStatus.OK);
	}

	@GetMapping("/customer")
	public ResponseEntity<?> getCustomer() throws IOException {
		List<Customer> lstCustomers = new ArrayList<Customer>();

		lstCustomers.add(new Customer(new Long(1), "Jack", 25, new Address("NANTERRE CT", "77471")));
		lstCustomers.add(new Customer(new Long(2), "Mary", 37, new Address("W NORMA ST", "77009")));
		lstCustomers.add(new Customer(new Long(3), "Peter", 18, new Address("S NUGENT AVE", "77571")));
		lstCustomers.add(new Customer(new Long(4), "Amos", 23, new Address("E NAVAHO TRL", "77449")));
		lstCustomers.add(new Customer(new Long(5), "Craig", 45, new Address("AVE N", "77587")));
		lstCustomers.add(new Customer(new Long(6), "Aries", 19, new Address("Broadway/Reade St, New York", "10007")));
		lstCustomers.add(new Customer(new Long(7), "Brice", 39, new Address("Columbus, OH 43215, USA", "43215")));
		lstCustomers.add(new Customer(new Long(8), "Cage", 24, new Address("Plano, TX 75074", "75074")));
		lstCustomers.add(new Customer(new Long(9), "Ellen", 41, new Address("Modesto, CA 95354", "95354")));
		lstCustomers.add(new Customer(new Long(10), "Brice", 32, new Address("Atlanta, GA 30334", "30334")));
		lstCustomers.add(new Customer(new Long(11), "Jack", 25, new Address("NANTERRE CT", "77471")));
		lstCustomers.add(new Customer(new Long(12), "Mary", 37, new Address("W NORMA ST", "77009")));
		lstCustomers.add(new Customer(new Long(13), "Peter", 18, new Address("S NUGENT AVE", "77571")));
		lstCustomers.add(new Customer(new Long(14), "Amos", 23, new Address("E NAVAHO TRL", "77449")));
		lstCustomers.add(new Customer(new Long(15), "Craig", 45, new Address("AVE N", "77587")));
		lstCustomers.add(new Customer(new Long(16), "Aries", 19, new Address("Broadway/Reade St, New York", "10007")));
		lstCustomers.add(new Customer(new Long(17), "Brice", 39, new Address("Columbus, OH 43215, USA", "43215")));
		lstCustomers.add(new Customer(new Long(18), "Cage", 24, new Address("Plano, TX 75074", "75074")));
		lstCustomers.add(new Customer(new Long(19), "Ellen", 41, new Address("Modesto, CA 95354", "95354")));
		lstCustomers.add(new Customer(new Long(20), "Brice", 32, new Address("Atlanta, GA 30334", "30334")));

		Context context = new Context();
		context.setVariable("customers", lstCustomers);
		String resultHtml;

		try (Writer writer = new StringWriter();) {
			templatesEngine.process(customerTemplateUrl, context, writer);
			resultHtml = writer.toString();
		}
		return new ResponseEntity<String>(resultHtml, HttpStatus.OK);
	}
}
