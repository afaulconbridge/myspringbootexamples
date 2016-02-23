package basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Runner implements ApplicationRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void run(ApplicationArguments args) throws Exception {

		Person person = new Person();
		person.setFirstName("Alice");
		person.setLastName("Foo");
		
		
		RestTemplate rest = new RestTemplate();

		//use a POST to put it into the database
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Person> httpEntity = new HttpEntity<>(person, headers);
		rest.exchange(
				"http://localhost:8080/people", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Resource<Person>>() {
				});
		
		//now use a GET to get it back
		ResponseEntity<Resource<Person>> getResult = rest.exchange(
				"http://localhost:8080/people/1", HttpMethod.GET, null,
				new ParameterizedTypeReference<Resource<Person>>() {
				});
		
		//check the links on the response
		log.info("getResult "+getResult);
		log.info("getResult.getBody"+getResult.getBody());
		//uh oh, no links...
		log.info("getResult.getLink(\"self\")"+getResult.getBody().getLink("self"));
		log.info("getResult.getLink(\"self\").getHref()"+getResult.getBody().getLink("self").getHref());

	}

}
