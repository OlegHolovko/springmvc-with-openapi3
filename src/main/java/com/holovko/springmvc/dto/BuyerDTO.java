package com.holovko.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BuyerDTO {
    private String name;
    private List<EventForBuyerDTO> events;
}
