package com.meritkapital.trade_validator.service;

import com.meritkapital.trade_validator.model.request.TradeRequest;
import com.meritkapital.trade_validator.model.response.TradeValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TradeValidationServiceImpl implements TradeValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeValidationService.class);


    @Autowired
    private Validator validator;

    @Override
    public List<TradeValidationResponse> validateTrades(final List<TradeRequest> trades) {

        return trades.stream().map(tradeRequest -> {
            final Set<String> errors = validator.validate(tradeRequest)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            LOGGER.info("Trade '{}' validated with result '{}'", tradeRequest, errors.isEmpty() ? " Success" : errors);
            return errors.isEmpty() ? new TradeValidationResponse(tradeRequest.getCustomer()) : new TradeValidationResponse(tradeRequest.getCustomer(), errors);

        }).collect(Collectors.toList());

    }


}
