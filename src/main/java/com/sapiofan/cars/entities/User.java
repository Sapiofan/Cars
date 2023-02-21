package com.sapiofan.cars.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Double forfeit = 0.0;

    @Column(nullable = false)
    private Integer rent_number = 0;

    @Column(nullable = false)
    private Integer discount = 0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private List<Contract> contracts = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getForfeit() {
        return forfeit;
    }

    public void setForfeit(Double forfeit) {
        this.forfeit = forfeit;
    }

    public Integer getRent_number() {
        return rent_number;
    }

    public void setRent_number(Integer rent_number) {
        if (rent_number == 0) {
            discount = 15;
        } else if (rent_number >= 10 && rent_number < 20) {
            discount = 5;
        } else if (rent_number >= 20 && rent_number < 40) {
            discount = 10;
        } else if (rent_number >= 40) {
            discount = 15;
        } else {
            discount = 0;
        }
        this.rent_number = rent_number;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(first_name, user.first_name)
                && Objects.equals(surname, user.surname) && Objects.equals(address, user.address)
                && Objects.equals(phone, user.phone) && Objects.equals(password, user.password)
                && Objects.equals(forfeit, user.forfeit) && Objects.equals(rent_number, user.rent_number)
                && Objects.equals(discount, user.discount) && role == user.role && Objects.equals(contracts, user.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, surname, address, phone, password,
                forfeit, rent_number, discount, role, contracts);
    }
}
