package basic.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NodeEntity
public class Thing {
    
    @GraphId
	private Long id;

	public Long getId() {
		return id;
	}

    //Uniqueness has to be enforced within Neo4J not via spring as of 4.0.0
    private String foo;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

    @Relationship(type = "DERIVED_FROM", direction=Relationship.OUTGOING)
    @JsonIgnore //to prevent infinite recursion on JSON serialization
	private DerivedFrom derivedFrom;

	public DerivedFrom getDerivedFrom() {
		return derivedFrom;
	}

	public void setDerivedFrom(DerivedFrom derivedFrom) {
		this.derivedFrom = derivedFrom;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thing)) {
            return false;
        }

        Thing other = (Thing) o;

        if (id != null && other.id != null) {
        	return id.equals(other.id);
        } else {
        	return true;
        }
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
