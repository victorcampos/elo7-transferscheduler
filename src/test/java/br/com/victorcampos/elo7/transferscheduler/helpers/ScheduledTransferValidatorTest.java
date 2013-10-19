package br.com.victorcampos.elo7.transferscheduler.helpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Test;

public class ScheduledTransferValidatorTest {

    @Test
    public void testIsValidFormatWithCorrectAccountNumber() {
	assertTrue(ScheduledTransferValidator.isValidFormat("12345-6"));
    }

    @Test
    public void testIsValidFormatWithInvalidAccountNumber() {
	assertFalse(ScheduledTransferValidator.isValidFormat("2345-6"));
    }

    @Test
    public void testIsValidFormatWithAccountNumberContainingLetters() {
	assertFalse(ScheduledTransferValidator.isValidFormat("ABCDE-F"));
    }

    @Test
    public void testCreatedDateAfterScheduledDateIsInvalid() {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.plusSeconds(1);

	assertFalse(ScheduledTransferValidator
		.isValidPeriodBetweenCreatedAndScheduledDates(createdDate,
			scheduledDate));
    }

    @Test
    public void testScheduledDateBeforeCreatedDateIsInvalid() {
	DateTime now = new DateTime();
	DateTime scheduledDate = now.minusSeconds(1);

	assertFalse(ScheduledTransferValidator
		.isValidPeriodBetweenCreatedAndScheduledDates(now,
			scheduledDate));
    }

}
