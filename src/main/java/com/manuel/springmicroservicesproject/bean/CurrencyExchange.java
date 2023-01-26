package com.manuel.springmicroservicesproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {
    private String id;
    private String from;
    private String to;
    private BigDecimal conversionMultiples;
    private String environment;
}
