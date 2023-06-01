package com.devplant.bookkeeping.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rule implements Cloneable {

    private UUID id;
    @NotNull
    private String receiverAccount;
    @NotNull
    private String senderAccount;
    @NotNull
    private String name;

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return (Rule) super.clone();
        } catch (CloneNotSupportedException e) {
            return Rule.builder()
                    .id(this.getId())
                    .receiverAccount(this.getReceiverAccount())
                    .senderAccount(this.getSenderAccount())
                    .name(this.getName())
                    .build();
        }
    }
}
