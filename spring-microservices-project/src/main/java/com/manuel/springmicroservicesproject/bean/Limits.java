package com.manuel.springmicroservicesproject.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Limits {
    private String minimum;
    private String maximum;
}
