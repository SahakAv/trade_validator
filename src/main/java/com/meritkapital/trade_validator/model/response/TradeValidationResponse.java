package com.meritkapital.trade_validator.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.meritkapital.trade_validator.model.ValidationResult;

import java.util.Objects;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TradeValidationResponse {

    @JsonProperty("tradeName")
    private final String tradeName;

    @JsonProperty("tradeValidationErrors")
    private Set<String> validationErrors;

    @JsonProperty("validationResult")
    private ValidationResult validationResult;

    public TradeValidationResponse(String tradeName, Set<String> validationErrors) {
        this.tradeName = tradeName;
        this.validationErrors = validationErrors;
        this.validationResult = ValidationResult.ERROR;

    }

    public TradeValidationResponse(String tradeName) {
        this.tradeName = tradeName;
        this.validationResult = ValidationResult.SUCCESS;
    }

    public String getTradeName() {
        return tradeName;
    }

    public Set<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeValidationResponse that = (TradeValidationResponse) o;
        return Objects.equals(tradeName, that.tradeName) &&
                Objects.equals(validationErrors, that.validationErrors) &&
                validationResult == that.validationResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeName, validationErrors, validationResult);
    }

    @Override
    public String toString() {
        return "TradeValidationResponse{" +
                "tradeName='" + tradeName + '\'' +
                ", validationErrors=" + validationErrors +
                ", validationResult=" + validationResult +
                '}';
    }
}
