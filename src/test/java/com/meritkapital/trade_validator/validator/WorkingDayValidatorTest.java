package com.meritkapital.trade_validator.validator;

import com.meritkapital.trade_validator.model.request.TradeRequest;
import com.meritkapital.trade_validator.util.Utils;
import com.meritkapital.trade_validator.validation.validator.WorkingDayValidator;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

@RunWith(DataProviderRunner.class)
public class WorkingDayValidatorTest {

    private static final String VALIDATING_FIELD_VALUE  = "valueDate";

    @InjectMocks
    private WorkingDayValidator validator;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        Utils.setFieldValue(validator, "fieldName", VALIDATING_FIELD_VALUE);
    }

    @Test
    @UseDataProvider("workingDayValidationDataProvider")
    public void testWorkingDayValidation(LocalDate localDate, boolean expectedValidationResult) throws Exception {
        TradeRequest tradeRequest = new TradeRequest();
        Utils.setFieldValue(tradeRequest, VALIDATING_FIELD_VALUE, localDate);
        boolean actualResult = validator.isValid(tradeRequest, null);
        Assert.assertEquals(actualResult, expectedValidationResult);


    }


    @DataProvider
    public static Object[][] workingDayValidationDataProvider() {
        return new Object[][]{
                {LocalDate.of(2019, 1, 6), false,},
                {LocalDate.of(2019, 3, 11), false},
                {LocalDate.of(2019, 5, 17), true,},
                {LocalDate.of(2019, 5, 18), false,},
                {LocalDate.of(2019, 5, 18), false,},
                {LocalDate.of(2019, 5, 21), true,},
                {LocalDate.of(2019, 5, 22), true,},};
    }
}
