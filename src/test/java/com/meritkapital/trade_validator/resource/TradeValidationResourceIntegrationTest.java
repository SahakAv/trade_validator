package com.meritkapital.trade_validator.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meritkapital.trade_validator.model.ValidationResult;
import com.meritkapital.trade_validator.model.request.TradeValidateRequest;
import com.meritkapital.trade_validator.model.response.TradeValidationResponse;
import com.meritkapital.trade_validator.model.response.ValidationResponse;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradeValidationResourceIntegrationTest {

    private static final String RESOURCE_URL = "/trade/validate";
    private static final ImmutableTriple<String, ValidationResult, Integer> REQUEST_1 = ImmutableTriple.of("request1.json", ValidationResult.ERROR, 9);
    private static final ImmutableTriple<String, ValidationResult, Integer> REQUEST_2 = ImmutableTriple.of("request2.json", ValidationResult.SUCCESS, 0);

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();
    }


    @Test
    public void testWithErrors() throws IOException {
        final String requestFileName = REQUEST_1.left;
        final ValidationResult expectedValidatorResult = REQUEST_1.middle;
        final int expectedErrorsCount = REQUEST_1.right;
        TradeValidateRequest request = getRequestFromFile(requestFileName);
        ValidationResponse tradeValidationResponse = executeRequestAndMapResponse(request);
        TradeValidationResponse result = tradeValidationResponse.getValidationResult().get(0);
        assertEquals(expectedErrorsCount, result.getValidationErrors().size());
        assertEquals(result.getValidationResult(), expectedValidatorResult);

    }

    @Test
    public void testWithoutErrors() throws IOException {
        final String requestFileName = REQUEST_2.left;
        final ValidationResult expectedValidatorResult = REQUEST_2.middle;
        TradeValidateRequest request = getRequestFromFile(requestFileName);
        ValidationResponse tradeValidationResponse = executeRequestAndMapResponse(request);
        TradeValidationResponse result = tradeValidationResponse.getValidationResult().get(0);
        assertEquals(result.getValidationResult(), expectedValidatorResult);
    }


    private TradeValidateRequest getRequestFromFile(String filePath) throws IOException {
        String file = getClass().getClassLoader().getResource(filePath).getFile();
        return objectMapper.readValue(new File(file), TradeValidateRequest.class);
    }

    private ValidationResponse executeRequestAndMapResponse(TradeValidateRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String response = testRestTemplate.exchange(getResourceUrl(), HttpMethod.POST, new HttpEntity<>(request, headers),
                String.class).getBody();
        return objectMapper.readValue(response, ValidationResponse.class);
    }


    private String getResourceUrl() {
        return "http://localhost:" + port + RESOURCE_URL;
    }
}
