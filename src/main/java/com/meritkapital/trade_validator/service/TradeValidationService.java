package com.meritkapital.trade_validator.service;

import com.meritkapital.trade_validator.model.request.TradeRequest;
import com.meritkapital.trade_validator.model.response.TradeValidationResponse;

import java.util.List;

public interface TradeValidationService {


    List<TradeValidationResponse> validateTrades(final List<TradeRequest> trades);
}
