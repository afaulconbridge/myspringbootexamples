package basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import basic.model.Thing;
import basic.repo.ThingRepository;

@RestController
@RequestMapping("/")
public class Controller {

	@Autowired 
	private ThingRepository thingRepository;
	
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/all")
    public Iterable<Thing> all() {
        return thingRepository.findAll();
    }
    
    @RequestMapping("/populate")
    public void populate() {
    	Thing t1 = new Thing();
    	t1.setFoo("T1");
    	Thing t2 = new Thing();
    	t2.setFoo("T2");
    	List<Thing> things = new ArrayList<Thing>();
    	things.add(t1);
    	things.add(t2);
    	thingRepository.save(things, 1);
    	
    	
    }
    
}