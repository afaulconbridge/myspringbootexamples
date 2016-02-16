package basic.repo;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import basic.model.DerivedFrom;

@Repository
public interface DerivedFromRepository extends GraphRepository<DerivedFrom> {
	
}
