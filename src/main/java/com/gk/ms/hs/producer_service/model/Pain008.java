package com.gk.ms.hs.producer_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pain008 implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("messageId")
    private String messageId; // Unique identifier for the message

    @JsonProperty("creationDateTime")
    private String creationDateTime; // Timestamp of message creation (ISO 8601 format)

    @JsonProperty("initiatingParty")
    private String initiatingParty; // Details of the party initiating the message

    @JsonProperty("debtorAgent")
    private String debtorAgent; // Financial institution servicing the debtor (BIC/SWIFT code)

    // Payment Information Block
    @JsonProperty("paymentInformationId")
    private String paymentInformationId; // Identifier for the payment information block

    @JsonProperty("paymentMethod")
    private String paymentMethod; // Payment method, e.g., "DD" for direct debit

    @JsonProperty("batchBooking")
    private String batchBooking; // Batch booking flag (true/false)

    @JsonProperty("requestedCollectionDate")
    private String requestedCollectionDate; // Date funds will be collected (YYYY-MM-DD)

    @JsonProperty("creditor")
    private String creditor; // Creditor's name

    @JsonProperty("creditorAccount")
    private String creditorAccount; // Creditor's IBAN or account number

    @JsonProperty("creditorAgent")
    private String creditorAgent; // Financial institution servicing the creditor (BIC/SWIFT code)

    @JsonProperty("chargeBearer")
    private String chargeBearer;         // Party bearing the transaction charges

}
