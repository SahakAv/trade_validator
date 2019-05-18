package com.meritkapital.trade_validator.validator;

import com.meritkapital.trade_validator.model.request.TradeRequest;
import com.meritkapital.trade_validator.util.Utils;
import com.meritkapital.trade_validator.validation.validator.LocalDateValidator;
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
import java.util.function.BiPredicate;

@RunWith(DataProviderRunner.class)
public class LocalDateValidatorTest {

    private static final String BASE_FIELD  =  "valueDate";

    private static final String FIELD_TO_COMPARE = "tradeDate";

    @InjectMocks
    private LocalDateValidator validator;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        Utils.setFieldValue(validator, "baseField", BASE_FIELD);
        Utils.setFieldValue(validator, "fieldToCompare", FIELD_TO_COMPARE);
    }

    @Test()
    @UseDataProvider("localDateValidationDataProvider")
    public void testValidation(LocalDate base, LocalDate toCompare, BiPredicate<LocalDate, LocalDate> validator, boolean expectedResult) throws Exception {
        TradeRequest tradeRequest = new TradeRequest();
        Utils.setFieldValue(tradeRequest, BASE_FIELD, base);
        Utils.setFieldValue(tradeRequest, FIELD_TO_COMPARE, toCompare);
        Utils.setFieldValue(this.validator, "validator", validator);
        boolean actualResult = this.validator.isValid(tradeRequest, null);
        Assert.assertEquals(expectedResult, actualResult);
    }


    @DataProvider
    public static Object[][] localDateValidationDataProvider() {
        return new Object[][]{
                {LocalDate.now(), LocalDate.now().minusMonths(1), (BiPredicate<LocalDate, LocalDate>) LocalDate::isAfter, true},
                {LocalDate.now(), LocalDate.now().plusMonths(1), (BiPredicate<LocalDate, LocalDate>) LocalDate::isAfter, false},
                {LocalDate.now().plusYears(1), LocalDate.now(), (BiPredicate<LocalDate, LocalDate>) LocalDate::isAfter, true},};
    }
}
