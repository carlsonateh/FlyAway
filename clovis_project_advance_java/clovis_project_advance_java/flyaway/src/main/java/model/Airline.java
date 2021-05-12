package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name= "airline")   
public class Airline {
	
	@Id   
	private Integer id;
    @Column(name="name")
	private String name;

    public Airline() {
    }

    public Airline(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Airline setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Airline setName(String name) {
        this.name = name;
        return this;
    }
}
