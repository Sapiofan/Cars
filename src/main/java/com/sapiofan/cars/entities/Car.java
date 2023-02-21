package com.sapiofan.cars.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer speed;

    @Column(nullable = false)
    private Integer engine_speed;

    @Column(nullable = false)
    private String gearbox;

    @Column(nullable = false)
    private Byte seats;

    @Column(nullable = false)
    private Double fuel_consumption;

    @Column(nullable = false)
    private String fuel_type;

    @Column(nullable = false)
    private Integer pledge;

    @Column(nullable = false)
    private String image;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    @JsonIgnore
    private List<Contract> contracts = new ArrayList<>();

    public Car() {
    }

    public Car(String name, String brand, String type,
               Integer year, Integer price, Integer speed,
               Integer engine_speed, String gearbox, Byte seats,
               Double fuel_consumption, String fuel_type,
               Integer pledge, String image) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.year = year;
        this.price = price;
        this.speed = speed;
        this.engine_speed = engine_speed;
        this.gearbox = gearbox;
        this.seats = seats;
        this.fuel_consumption = fuel_consumption;
        this.fuel_type = fuel_type;
        this.pledge = pledge;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEngine_speed() {
        return engine_speed;
    }

    public void setEngine_speed(Integer engine_speed) {
        this.engine_speed = engine_speed;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public Byte getSeats() {
        return seats;
    }

    public void setSeats(Byte seats) {
        this.seats = seats;
    }

    public Double getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(Double fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Integer getPledge() {
        return pledge;
    }

    public void setPledge(Integer pledge) {
        this.pledge = pledge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(name, car.name)
                && Objects.equals(brand, car.brand) && Objects.equals(type, car.type) && Objects.equals(year, car.year)
                && Objects.equals(price, car.price) && Objects.equals(speed, car.speed)
                && Objects.equals(engine_speed, car.engine_speed) && Objects.equals(gearbox, car.gearbox)
                && Objects.equals(seats, car.seats) && Objects.equals(fuel_consumption, car.fuel_consumption)
                && Objects.equals(fuel_type, car.fuel_type) && Objects.equals(pledge, car.pledge)
                && Objects.equals(image, car.image) && Objects.equals(contracts, car.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, type, year, price, speed, engine_speed,
                gearbox, seats, fuel_consumption, fuel_type, pledge, image, contracts);
    }
}
