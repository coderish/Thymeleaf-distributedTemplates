package com.coderish.tutorial.thymeleaf.configurations;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
public class AppConfig {

	@Value("${application.templating.isCacheable}")
	private boolean isCacheable;

	@Bean
	@Description("Thymeleaf View Resolver")
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templatesEngine());
		viewResolver.setOrder(1);
		return viewResolver;
	}

	@Bean
	@Description("Thymeleaf Template Engine")
	public SpringTemplateEngine templatesEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(urlTemplateResolver());
//	    templateEngine.setTemplateEngineMessageSource(messageSource());
		return templateEngine;
	}

	public ITemplateResolver urlTemplateResolver() {
		final UrlTemplateResolver urlResolver = new UrlTemplateResolver();
		urlResolver.setCacheable(this.isCacheable);
		urlResolver.setCheckExistence(true);
		urlResolver.setCharacterEncoding(Charset.defaultCharset().name());
		return urlResolver;
	}

	/*
	 * @Bean
	 * 
	 * @Description("Spring Message Resolver") public ResourceBundleMessageSource
	 * messageSource() { ResourceBundleMessageSource messageSource = new
	 * ResourceBundleMessageSource(); messageSource.setBasename("messages"); return
	 * messageSource; }
	 */
}
