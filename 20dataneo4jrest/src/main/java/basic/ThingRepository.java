package basic;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingRepository extends GraphRepository<Thing> {
	
}
