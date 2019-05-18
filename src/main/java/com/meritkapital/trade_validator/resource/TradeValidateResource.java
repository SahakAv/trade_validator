package com.meritkapital.trade_validator.resource;

import com.meritkapital.trade_validator.model.request.TradeValidateRequest;
import com.meritkapital.trade_validator.model.response.TradeValidationResponse;
import com.meritkapital.trade_validator.model.response.ValidationResponse;
import com.meritkapital.trade_validator.service.TradeValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/trade")
public class TradeValidateResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeValidateResource.class);

    @Autowired
    private TradeValidationService tradeValidationService;


    @PostMapping(value = "/validate", consumes = "application/json", produces = "application/json")
    public ValidationResponse validateTrade(@RequestBody TradeValidateRequest tradeRequest){
        LOGGER.info("Request to validate trades " + tradeRequest);
        final List<TradeValidationResponse> responses = tradeValidationService.validateTrades(tradeRequest.getTrades());
        LOGGER.info("Validated trades with result " + responses);
        return new ValidationResponse(responses);

    }
}
