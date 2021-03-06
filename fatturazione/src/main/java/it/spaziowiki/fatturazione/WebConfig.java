package it.spaziowiki.fatturazione;

import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import it.spaziowiki.fatturazione.view.ListaBozzeExcelView;
import it.spaziowiki.fatturazione.view.ListaFattureExcelView;

@Configuration
@EnableWebMvc
@PropertySource({ "classpath:parametri.properties" })
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");

		return resolver;
	}
	
	

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");

		registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");

		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");

		registry.addResourceHandler("/scss/**").addResourceLocations("/resources/scss/");

		registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
		
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ITALIAN);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	@Bean
	public ListaFattureExcelView listaFattureExcelView() {
		ListaFattureExcelView listaFattureExcelView = new ListaFattureExcelView();
		return listaFattureExcelView;
	}

	@Bean
	public ListaBozzeExcelView listaBozzeExcelView() {
		ListaBozzeExcelView listaBozzeExcelView = new ListaBozzeExcelView();
		return listaBozzeExcelView;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	 @ConfigurationProperties(prefix = "spring.datasource")
	 public DataSource dataSource() {
	  return DataSourceBuilder.create().build();
	 }
}
