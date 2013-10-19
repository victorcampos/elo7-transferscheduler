package br.com.victorcampos.elo7.transferscheduler.helpers;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountFormatValidatorTest {

    @Test
    public void testIsValidFormatWithCorrectAccountNumber() {
	assertTrue(AccountFormatValidator.isValidFormat("12345-6"));
    }
    
    @Test
    public void testIsValidFormatWithInvalidAccountNumber() {
	assertFalse(AccountFormatValidator.isValidFormat("2345-6"));
    }
    
    @Test
    public void testIsValidFormatWithAccountNumberContainingLetters() {
	assertFalse(AccountFormatValidator.isValidFormat("ABCDE-F"));
    }

}
