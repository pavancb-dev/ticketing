package com.pcb.bookingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "customer")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Customer {

    @Id
    private Long id;
    private String name;
    private String email;
    private String address;

}
