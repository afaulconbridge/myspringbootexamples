package basic;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Runner implements ApplicationRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void run(ApplicationArguments args) throws Exception {

		Person person = new Person();
		person.setFirstName("Alice");
		person.setLastName("Foo");
		

		RestTemplate rest = new RestTemplate();
		
		//need to create a new message converter to handle hal+json
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter.setObjectMapper(mapper);

		//add the new converters to the restTemplate
		//but make sure it is BEFORE the exististing converters
		List<HttpMessageConverter<?>> converters = rest.getMessageConverters();
		converters.add(0,converter);
		rest.setMessageConverters(converters);
		

		//use a POST to put it into the database
		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Person> httpEntity = new HttpEntity<>(person, postHeaders);
		rest.exchange(
				"http://localhost:8080/people", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Resource<Person>>() {
				});
		
		//now use a GET to get it back
		ResponseEntity<Resource<Person>> getResult = rest.exchange(
				"http://localhost:8080/people/1", HttpMethod.GET, null,
				new ParameterizedTypeReference<Resource<Person>>() {
				});
		
		log.info(""+getResult.getHeaders());
		
		//check the links on the response
		log.info("getResult "+getResult);
		log.info("getResult.getBody"+getResult.getBody());
		//uh oh, no links...
		log.info("getResult.getLink(\"self\")"+getResult.getBody().getLink("self"));
		log.info("getResult.getLink(\"self\").getHref()"+getResult.getBody().getLink("self").getHref());

	}

}
