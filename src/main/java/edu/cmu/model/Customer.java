package edu.cmu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    //@NonNull
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //@NonNull
    @NotNull
    @JsonProperty("userId")
    @Email
    private String userId;

    //@NonNull
    @NotNull
    @JsonProperty("name")
    private String name;
    //@NonNull
    @NotNull
    @JsonProperty("phone")
    private String phone;


    //@NonNull
    @NotNull
    @JsonProperty("address")
    private String address;


    @JsonProperty("address2")
    private String address2;

    //@NonNull
    @NotNull
    @JsonProperty("city")
    private String city;

    //@NonNull
    @NotNull
    @JsonProperty("state")
    private String state;

    //@NonNull
    @NotNull
    @JsonProperty("zipcode")
    private String zipcode;


}
