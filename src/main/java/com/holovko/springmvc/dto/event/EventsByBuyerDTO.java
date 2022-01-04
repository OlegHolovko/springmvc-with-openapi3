package com.holovko.springmvc.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventsByBuyerDTO {
    private String name;
    private List<EventForBuyerDTO> events;
}
