package com.devplant.bookkeeping.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Rule {

    private UUID id;
    @NotNull
    private String receiverAccount;
    @NotNull
    private String senderAccount;
    @NotNull
    private String name;
}
