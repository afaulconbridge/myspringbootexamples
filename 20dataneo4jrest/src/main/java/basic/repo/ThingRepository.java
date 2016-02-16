package basic.repo;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import basic.model.Thing;

@Repository
public interface ThingRepository extends GraphRepository<Thing> {
	
}
