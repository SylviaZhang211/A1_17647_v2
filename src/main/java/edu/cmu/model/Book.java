package edu.cmu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    //@NonNull
    @NotNull
    @JsonProperty("ISBN")
    private String ISBN;

    //@NonNull
    @NotNull
    @JsonProperty("title")
    private String title;

    //@NonNull
    @NotNull
    @JsonProperty("Author")
    private String Author;
    //@NonNull
    @NotNull
    @JsonProperty("description")
    private String description;

    //@NonNull
    @NotNull
    @JsonProperty("genre")
    private String genre;

    //@NonNull
    @NotNull
    @JsonProperty("price")
    private double price;

    //@NonNull
    @NotNull
    @JsonProperty("quantity")
    private int quantity;



}
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//@Entity
//@Data
//public class Book {
//    @Id
//    @NotNull
//    @JsonProperty("ISBN")
//    private String ISBN;
//
//    @NotNull
//    @JsonProperty
//    private String title;
//
//    @NotNull
//    @JsonProperty("Author")
//    private String author;
//
//    @NotNull
//    @JsonProperty
//    private String description;
//
//    @NotNull
//    @JsonProperty
//    private String genre;
//
//    @NotNull
//    @JsonProperty
//    private double price;
//
//    @NotNull
//    @JsonProperty
//    private int quantity;
//
//    @JsonIgnore
//    public boolean isPriceInvalid() {
//        return !String.valueOf(price).equals(String.format("%.2f", price));
//    }
//}
