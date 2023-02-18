package com.sapiofan.cars.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date start_rent;

    @Column(nullable = false)
    private Date end_rent;

    @Column(nullable = false)
    private Double end_price;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "preference_list",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "preference_id")
    )
    private Set<CarPreferences> preferences = new HashSet<>();

    public Contract() {
    }

    public Contract(Date start_rent, Date end_rent, Double end_price, User user, Car car, Set<CarPreferences> preferences) {
        this.start_rent = start_rent;
        this.end_rent = end_rent;
        this.end_price = end_price;
        this.user = user;
        this.car = car;
        this.preferences = preferences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_rent() {
        return start_rent;
    }

    public void setStart_rent(Date start_rent) {
        this.start_rent = start_rent;
    }

    public Date getEnd_rent() {
        return end_rent;
    }

    public void setEnd_rent(Date end_rent) {
        this.end_rent = end_rent;
    }

    public Double getEnd_price() {
        return end_price;
    }

    public void setEnd_price(Double end_price) {
        this.end_price = end_price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<CarPreferences> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<CarPreferences> preferences) {
        this.preferences = preferences;
    }
}
