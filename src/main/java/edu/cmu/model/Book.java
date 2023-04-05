package edu.cmu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @NotNull
    @JsonProperty("ISBN")
    private String ISBN;

    @NotNull
    @JsonProperty
    private String title;

    @NotNull
    @JsonProperty("Author")
    private String author;

    @NotNull
    @JsonProperty
    private String description;

    @NotNull
    @JsonProperty
    private String genre;

    @NotNull
    @JsonProperty
    private double price;

    @NotNull
    @JsonProperty
    private int quantity;

    @JsonIgnore
    public boolean isPriceInvalid() {
        return !String.valueOf(price).equals(String.format("%.2f", price));
    }
}
