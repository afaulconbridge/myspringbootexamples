package basic.model;

import java.util.Objects;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="DERIVED_FROM")
public class DerivedFrom {
	
    @GraphId   
    private Long id;
    
    private String foo; 
    
    @StartNode 
    private Thing parent;
    
    @EndNode   
    private Thing child;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public Thing getChild() {
		return child;
	}

	public Thing getParent() {
		return parent;
	}
	
	public DerivedFrom() {
		
	}
	
	public DerivedFrom(Thing parent, Thing child, String foo) {
		this.parent = parent;
		this.child = child;
		this.foo = foo;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DerivedFrom)) {
            return false;
        }

        DerivedFrom other = (DerivedFrom) o;
		return Objects.equals(parent, other.parent)
				&& Objects.equals(child, other.child)
				&& Objects.equals(foo, other.foo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo, child, parent);
    }
	
}
