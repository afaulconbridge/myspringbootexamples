package basic.repo;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import basic.model.Thing;

/**
 * Spring will automagically create instances of repository interfaces
 * as long as they are annotated.
 * 
 * No point creating edge repository, since edges are persisted according 
 * to the nodes they are attached to.
 * 
 * @author Adam
 *
 */
@Repository
public interface ThingRepository extends GraphRepository<Thing> {
	
}
