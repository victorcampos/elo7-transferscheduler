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
    public void testIsValidCreatedDateWhenScheduledDateIsNull() {
	DateTime createdDate = new DateTime();

	assertTrue(ScheduledTransferValidator.isValidCreatedDate(createdDate,
		null));
    }

    @Test
    public void testIsValidCreatedDateWhenCreatedDateIsBeforeScheduledDate() {
	DateTime createdDate = new DateTime();
	DateTime scheduledDate = createdDate.plusSeconds(1);

	assertTrue(ScheduledTransferValidator.isValidCreatedDate(createdDate,
		scheduledDate));
    }

    @Test
    public void testIsInvalidCreatedDateWhenCreatedDateIsAfterScheduledDate() {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.plusSeconds(1);

	assertFalse(ScheduledTransferValidator.isValidCreatedDate(createdDate,
		scheduledDate));
    }

    @Test
    public void testIsInvalidCreatedDateWhenNull() {
	DateTime scheduledDate = new DateTime();

	assertFalse(ScheduledTransferValidator.isValidCreatedDate(null,
		scheduledDate));
    }

    @Test
    public void testIsValidScheduledDateWhenCreatedDateIsNull() {
	DateTime scheduledDate = new DateTime();

	assertTrue(ScheduledTransferValidator.isValidScheduledDate(null,
		scheduledDate));
    }

    @Test
    public void testIsValidScheduledDateWhenScheduledDateIsAfterCreatedDate() {
	DateTime createdDate = new DateTime();
	DateTime scheduledDate = createdDate.plusSeconds(1);

	assertTrue(ScheduledTransferValidator.isValidScheduledDate(createdDate,
		scheduledDate));
    }

    @Test
    public void testIsInvalidScheduledDateWhenScheduledDateIsBeforeCreatedDate() {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.plusSeconds(1);

	assertFalse(ScheduledTransferValidator.isValidScheduledDate(
		createdDate, scheduledDate));
    }

    @Test
    public void testIsInvalidScheduledDateWhenNull() {
	DateTime createdDate = new DateTime();

	assertFalse(ScheduledTransferValidator.isValidScheduledDate(
		createdDate, null));
    }

    @Test
    public void isValidTransferAmountWhenGreaterThanZero() {
	assertTrue(ScheduledTransferValidator.isValidTransferAmount(1));
    }
    
    @Test
    public void isInvalidTransferAmountWhenEqualToZero() {
	assertFalse(ScheduledTransferValidator.isValidTransferAmount(0));
    }
    
    @Test
    public void isInvalidTransferAmountWhenLessThanZero() {
	assertFalse(ScheduledTransferValidator.isValidTransferAmount(-1));
    }

}
