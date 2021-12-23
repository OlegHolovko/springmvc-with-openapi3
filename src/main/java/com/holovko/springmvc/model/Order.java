package com.holovko.springmvc.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter

public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="buyer_name")
    private String buyerName;

    @Column(name="amount")
    private Integer amount;

    @Column(name="total_price")
    private Integer totalPrice;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "event_id", nullable = true)
    @JsonBackReference
    private Event event;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name="event_id")
    private Long eventId;

}
