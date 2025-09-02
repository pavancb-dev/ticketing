package com.pcb.inventoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "venue")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Venue {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "total_capacity")
    private Long totalCapacity;
}
