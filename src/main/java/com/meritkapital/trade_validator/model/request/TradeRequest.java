package com.meritkapital.trade_validator.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meritkapital.trade_validator.model.TradeStyle;
import com.meritkapital.trade_validator.util.BigDecimalJsonSerializer;
import com.meritkapital.trade_validator.util.DateComparisionType;
import com.meritkapital.trade_validator.validation.CurrencyPairValidation;
import com.meritkapital.trade_validator.validation.LocalDateValidation;
import com.meritkapital.trade_validator.validation.WorkingDayValidation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@LocalDateValidation(message = "Value date should be after trade date", baseField = "valueDate", fieldToCompare = "tradeDate",
        dateComparisionType = DateComparisionType.DATE_IS_AFTER)
@LocalDateValidation(message = "Expiry date should be after delivery date", baseField = "expiryDate", fieldToCompare = "deliveryDate",
        dateComparisionType = DateComparisionType.DATE_IS_AFTER)
@LocalDateValidation(message = "Premium date should be after delivery date", baseField = "premiumDate", fieldToCompare = "deliveryDate",
        dateComparisionType = DateComparisionType.DATE_IS_AFTER)
@LocalDateValidation(message = "Exercise start date should be after trade date", baseField = "excerciseStartDate", fieldToCompare = "tradeDate",
        dateComparisionType = DateComparisionType.DATE_IS_AFTER,  tradeStyle = TradeStyle.AMERICAN)
@LocalDateValidation(message = "Exercise start date should be after expired date", baseField = "excerciseStartDate", fieldToCompare = "expiredDate",
        dateComparisionType = DateComparisionType.DATE_IS_BEFORE,  tradeStyle = TradeStyle.AMERICAN)
@WorkingDayValidation(message = "Value date cannot be on non-working day", field = "valueDate")
public class TradeRequest {

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("valueDate")
    private LocalDate valueDate;

    @JsonProperty("type")
    private String type;

    @JsonProperty("ccyPair")
    @CurrencyPairValidation(message = "Ccypair should be valid pair of currencies by a ISO 4217")
    private String ccyPair;

    @JsonProperty("style")
    private TradeStyle style;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("strategy")
    private String strategy;

    @JsonProperty("tradeDate")
    private LocalDate tradeDate;

    @JsonProperty("amount1")
    @JsonSerialize(using = BigDecimalJsonSerializer.class)
    private BigDecimal amount1;

    @JsonProperty("amount2")
    @JsonSerialize(using = BigDecimalJsonSerializer.class)
    private BigDecimal amount2;

    @JsonProperty("rate")
    @JsonSerialize(using = BigDecimalJsonSerializer.class)
    private BigDecimal rate;

    @JsonProperty("deliveryDate")
    private LocalDate deliveryDate;

    @JsonProperty("expiryDate")
    private LocalDate expiryDate;

    @JsonProperty("payCcy")
    private String payCcy;

    @JsonProperty("excerciseStartDate")
    private LocalDate excerciseStartDate;

    @JsonProperty("premiumType")
    private String premiumType;

    @JsonProperty("premiumDate")
    private LocalDate premiumDate;

    @JsonProperty("legalEntity")
    private String legalEntity;

    @JsonProperty("trader")
    private String trader;

    @JsonProperty("premium")
    @JsonSerialize(using = BigDecimalJsonSerializer.class)
    private BigDecimal premium;

    @JsonProperty("premiumCcy")
    private String premiumCcy;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }

    public TradeStyle getStyle() {
        return style;
    }

    public void setStyle(TradeStyle style) {
        this.style = style;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getAmount1() {
        return amount1;
    }

    public void setAmount1(BigDecimal amount1) {
        this.amount1 = amount1;
    }

    public BigDecimal getAmount2() {
        return amount2;
    }

    public void setAmount2(BigDecimal amount2) {
        this.amount2 = amount2;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPayCcy() {
        return payCcy;
    }

    public void setPayCcy(String payCcy) {
        this.payCcy = payCcy;
    }

    public LocalDate getExcerciseStartDate() {
        return excerciseStartDate;
    }

    public void setExcerciseStartDate(LocalDate excerciseStartDate) {
        this.excerciseStartDate = excerciseStartDate;
    }

    public String getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(String premiumType) {
        this.premiumType = premiumType;
    }

    public LocalDate getPremiumDate() {
        return premiumDate;
    }

    public void setPremiumDate(LocalDate premiumDate) {
        this.premiumDate = premiumDate;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeRequest that = (TradeRequest) o;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(valueDate, that.valueDate) &&
                Objects.equals(type, that.type) &&
                Objects.equals(ccyPair, that.ccyPair) &&
                style == that.style &&
                Objects.equals(direction, that.direction) &&
                Objects.equals(strategy, that.strategy) &&
                Objects.equals(tradeDate, that.tradeDate) &&
                Objects.equals(amount1, that.amount1) &&
                Objects.equals(amount2, that.amount2) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(deliveryDate, that.deliveryDate) &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(payCcy, that.payCcy) &&
                Objects.equals(excerciseStartDate, that.excerciseStartDate) &&
                Objects.equals(premiumType, that.premiumType) &&
                Objects.equals(premiumDate, that.premiumDate) &&
                Objects.equals(legalEntity, that.legalEntity) &&
                Objects.equals(trader, that.trader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, valueDate, type, ccyPair, style, direction, strategy, tradeDate, amount1, amount2, rate, deliveryDate, expiryDate, payCcy, excerciseStartDate, premiumType, premiumDate, legalEntity, trader);
    }

}
