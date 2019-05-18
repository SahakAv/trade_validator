package com.meritkapital.trade_validator.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValidationResponse {

    @JsonProperty("validationResult")
   private List<TradeValidationResponse> validationResult;


    public ValidationResponse() {
    }

    public ValidationResponse(List<TradeValidationResponse> validationResult) {
        this.validationResult = validationResult;
    }

    public List<TradeValidationResponse> getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(List<TradeValidationResponse> validationResult) {
        this.validationResult = validationResult;
    }


    @Override
    public String toString() {
        return "ValidationResponse{" +
                "validationResult=" + validationResult +
                '}';
    }
}


