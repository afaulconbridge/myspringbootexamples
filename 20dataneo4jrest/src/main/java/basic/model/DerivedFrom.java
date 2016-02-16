package basic.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="DERIVED_FROM")
public class DerivedFrom {
	
    @GraphId   
    private Long id;
    
    @Property  
    private String title; 
    
    @StartNode 
    private Thing child;
    
    @EndNode   
    private Thing parent;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Thing getChild() {
		return child;
	}

	public void setChild(Thing child) {
		this.child = child;
	}

	public Thing getParent() {
		return parent;
	}

	public void setParent(Thing parent) {
		this.parent = parent;
	}
	
}
