package com.ahmed.invoicemanagment.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;


@Data
@SuperBuilder
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class Role {

    private long id;
    private String name;
    private String permissions;

}

