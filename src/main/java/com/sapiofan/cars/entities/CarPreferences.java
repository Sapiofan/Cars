package com.sapiofan.cars.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "preferences")
@Entity
public class CarPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String preference;

    @ManyToMany(mappedBy = "preferences", fetch = FetchType.EAGER)
    Set<Contract> contracts = new HashSet<>();

    public CarPreferences() {
    }

    public CarPreferences(String preference) {
        this.preference = preference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
}
