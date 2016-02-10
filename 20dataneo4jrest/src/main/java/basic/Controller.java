package basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}