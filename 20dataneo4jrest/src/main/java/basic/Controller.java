package basic;

import java.util.HashMap;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import basic.model.DerivedFrom;
import basic.model.Thing;
import basic.repo.ThingRepository;

@RestController
@RequestMapping("/")
public class Controller {

	@Autowired 
	private ThingRepository thingRepository;
	
	@Autowired Neo4jOperations neo4jTemplate;
	
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/all")
    public Iterable<Thing> all() {
        return thingRepository.findAll();
    }

    @PostConstruct
    public void populateOnStart() {
    	//clean up any nodes or edges from previous sessions
    	//woulnd't want to do this in production...
    	neo4jTemplate.query("MATCH (n) DETACH DELETE n", new HashMap<>());
    	
        // populates upon initialization...
    	Thing t1 = new Thing();
    	t1.setFoo("T1");
    	thingRepository.save(t1);
    	
    	Thing t2 = new Thing();
    	t2.setFoo("T2");
    	thingRepository.save(t2);
    	
    	DerivedFrom d = new DerivedFrom(t2, t1, "Monty Python");
    	t1.setDerivedFrom(d);
    	thingRepository.save(t1);
    }
    
}