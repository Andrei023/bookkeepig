package com.devplant.bookkeeping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Account {
    private String id;
    private String name;
}
