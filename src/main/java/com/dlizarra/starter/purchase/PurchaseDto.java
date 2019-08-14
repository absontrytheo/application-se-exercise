package com.dlizarra.starter.purchase;

import javax.validation.constraints.Size;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(of = {"buyerName", "productName", "price", "buyDate"})
@ToString(of = {"id", "buyerName", "productName", "price", "buyDate"})
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PurchaseDto {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Integer id;

    @Size(max = Purchase.MAX_LENGTH_BUYER_NAMES)
    private String buyerName;
    @Size(max = Purchase.MAX_LENGTH_PRODUCT_NAMES)
    private String productName;

    private Integer priceInCents;
    private String buyDate;


    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;
}
