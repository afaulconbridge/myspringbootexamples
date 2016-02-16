package basic.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class Thing {
    
    @GraphId
    private Long id;

    @Property 
    //Uniqueness has to be enforced within Neo4J not via spring as of 4.0.0
    private String foo;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}
	
}
