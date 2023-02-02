package com.manuel.springmicroservicesproject.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CurrencyExchange {
    @Id
    private long id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiples;
    private String environment;
}
