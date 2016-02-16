package basic;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Thing {
    
    @GraphId
    private Long id;
	
    @Relationship(type = "DERIVED_FROM", direction = Relationship.OUTGOING)
    private Thing derivedFrom;

    //Uniqueness has to be enforced within Neo4J not via spring as of 4.0.0
    private String foo;

    public Long getId() {
    	return id;
    }

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public Thing getDerivedFrom() {
		return derivedFrom;
	}

	public void setDerivedFrom(Thing derivedFrom) {
		this.derivedFrom = derivedFrom;
	}
	
	
}
