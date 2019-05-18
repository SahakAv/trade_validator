package com.meritkapital.trade_validator.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TradeValidateRequest {

    @JsonProperty("test")
    private List<TradeRequest> trades;


    public List<TradeRequest> getTrades() {
        return trades;
    }

    public void setTrades(List<TradeRequest> trades) {
        this.trades = trades;
    }


    @Override
    public String toString() {
        return "TradeValidateRequest{" +
                "trades=" + trades +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeValidateRequest that = (TradeValidateRequest) o;
        return Objects.equals(trades, that.trades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trades);
    }
}
