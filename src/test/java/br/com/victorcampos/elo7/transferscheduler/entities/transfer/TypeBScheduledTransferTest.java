package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeBScheduledTransferTest {

    @Test
    public void testGetType() throws InvalidArgumentException {
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(1);
	ScheduledTransfer typeBScheduledTransfer = new TypeBScheduledTransfer(
		"12345-6", "12345-6", 0, scheduledDate, scheduledDate);

	assertEquals("B", typeBScheduledTransfer.getType());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateBefore30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 1000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(10);

	ScheduledTransfer typeBScheduledTransfer = new TypeBScheduledTransfer(
		"12345-6", "12345-6", 10000, now, scheduledDate);

	assertEquals(expectedFee, typeBScheduledTransfer.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 800;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	ScheduledTransfer typeBScheduledTransfer = new TypeBScheduledTransfer(
		"12345-6", "12345-6", 10000, now, scheduledDate);

	assertEquals(expectedFee, typeBScheduledTransfer.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateIs30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 1000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(30);

	ScheduledTransfer typeBScheduledTransfer = new TypeBScheduledTransfer(
		"12345-6", "12345-6", 10000, now, scheduledDate);

	assertEquals(expectedFee, typeBScheduledTransfer.calculateFee());
    }

}
