package model;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name= "flight")   
public class Flight {
	@Id   
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private Integer price;
    @Column(name="source_id")
    private Integer sourceId;
    @Column(name="destination_id")
    private Integer destinationId;
    @Column(name="airline_id")
    private Integer airlineId;
    @Column(name="travel_date")
    private Date travelDate;

    public Flight() {
    }

    public Flight(Integer id, String name, Integer price, Integer sourceId, Integer destinationId,
                  Integer airlineId, Date travelDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.airlineId = airlineId;
        this.travelDate = travelDate;
    }

    public Integer getId() {
        return id;
    }

    public Flight setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Flight setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Flight setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public Flight setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public Flight setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
        return this;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public Flight setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
        return this;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public Flight setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
        return this;
    }

}
