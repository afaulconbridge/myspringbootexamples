package basic.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Thing {
    
    @GraphId
	private Long id;

	public Long getId() {
		return id;
	}

    @Property 
    //Uniqueness has to be enforced within Neo4J not via spring as of 4.0.0
    private String foo;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

    @Relationship(type = "DERIVED_FROM") //links to DerivedFrom class
	private Thing derivedFrom;

	public Thing getDerivedFrom() {
		return derivedFrom;
	}

	public void setDerivedFrom(Thing derivedFrom) {
		this.derivedFrom = derivedFrom;
	}
}
