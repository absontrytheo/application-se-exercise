package com.dlizarra.starter.purchase;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@EqualsAndHashCode(of = {"buyerName", "productName", "price", "buyDate"})
@ToString(of = {"id", "buyerName", "productName", "price", "buyDate"})
@Setter
@Getter
@Entity
@Table(name = "purchases")
public class Purchase {

    static final int MAX_LENGTH_BUYER_NAMES = 30;
    static final int MAX_LENGTH_PRODUCT_NAMES = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = false, length = MAX_LENGTH_BUYER_NAMES)
    private String buyerName;
    @Column(nullable = false, unique = false, length = MAX_LENGTH_PRODUCT_NAMES)
    private String productName;
    @Column(nullable = false, unique = false)
    private Integer priceInCents;

    @Column(nullable = false, unique = false)
    private String buyDate; // FIXME: I got stuck on trying to map the string from the request mapped to a LocalDate, hence I changed this to a string.


    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;


    public Purchase() {
    }


    @PrePersist
    public void prePersist() {
        creationTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        modificationTime = LocalDateTime.now();
    }

}
