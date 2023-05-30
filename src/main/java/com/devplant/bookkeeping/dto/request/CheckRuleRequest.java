package com.devplant.bookkeeping.dto.request;

import lombok.Data;

@Data
public class CheckRuleRequest {

    private String receiverAccount;
    private String senderAccount;
}
