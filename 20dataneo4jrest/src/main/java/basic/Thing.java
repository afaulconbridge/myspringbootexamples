package basic;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Thing {
    
    @GraphId
    private Long id;

    private String foo;


	public String getFoo() {
		return foo;
	}


	public void setFoo(String foo) {
		this.foo = foo;
	}
	
}
