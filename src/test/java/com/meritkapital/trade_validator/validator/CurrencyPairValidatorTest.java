package com.meritkapital.trade_validator.validator;

import com.meritkapital.trade_validator.validation.validator.CurrencyPairValidator;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import javax.validation.ConstraintValidatorContext;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(DataProviderRunner.class)
public class CurrencyPairValidatorTest {

    @InjectMocks
    private CurrencyPairValidator validator;

    @Mock
    private ConstraintValidatorContext constraintValidatorContextMock;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    @UseDataProvider("currencyValidationDataProvider")
    public void testValidation(String currencyPair, boolean expectedValidationResult) {
        when(constraintValidatorContextMock.buildConstraintViolationWithTemplate(any())).thenReturn(constraintViolationBuilder);
        when(constraintViolationBuilder.addConstraintViolation()).thenReturn(constraintValidatorContextMock);
        boolean actualResult = validator.isValid(currencyPair,constraintValidatorContextMock);
        Assert.assertEquals(actualResult, expectedValidationResult);
    }


    @DataProvider
    public static Object[][] currencyValidationDataProvider() {
        return new Object[][]{
                {"USDEUR", true,},
                {"AMDEUR", true,},
                {"AMEUR", false,},
                {"AMER", false,},
                {"DZFBI", false,},
                {"AFNALL", true,},
        };
    }
}
