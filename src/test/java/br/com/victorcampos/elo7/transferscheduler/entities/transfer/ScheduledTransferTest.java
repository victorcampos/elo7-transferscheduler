package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

import static org.junit.Assert.*;

public class ScheduledTransferTest {

    @Test(expected = InvalidArgumentException.class)
    public void testSetCreatedDateAfterScheduledDateShouldFail()
	    throws Exception {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.plusSeconds(1);

	new ScheduledTransferMock("12345-6", "12345-6", 0, createdDate,
		scheduledDate);
    }

    @Test
    public void testSetScheduledDateBeforeCreatedDateShouldFailAndGiveMessage()
	    throws Exception {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.plusSeconds(1);

	try {
	    new ScheduledTransferMock("12345-6", "12345-6", 0, createdDate,
		    scheduledDate);
	} catch (InvalidArgumentException e) {
	    assertEquals("Scheduled date should be after created date",
		    e.getMessage());
	}
    }

    @Test(expected = InvalidArgumentException.class)
    public void testSetScheduledDateBeforeCreatedDateShouldFail()
	    throws Exception {
	DateTime now = new DateTime();
	DateTime scheduledDate = now.minusSeconds(1);

	new ScheduledTransferMock("12345-6", "12345-6", 0, now, scheduledDate);
    }

    @Test
    public void testSetCreatedDateAfterScheduledDateShouldFailAndGiveMessage()
	    throws Exception {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.minusSeconds(1);

	ScheduledTransfer scheduledTransfer = new ScheduledTransferMock(
		"12345-6", "12345-6", 0, createdDate, scheduledDate);

	try {
	    scheduledTransfer.setCreatedDate(scheduledDate.plusSeconds(1));
	} catch (InvalidArgumentException e) {
	    assertEquals("Created date should be before scheduled date",
		    e.getMessage());
	}
    }

    @Test
    public void testInvalidOriginAccountShouldFailAndGiveMessage()
	    throws Exception {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.minusSeconds(1);

	try {
	    new ScheduledTransferMock("1234-6", "12345-6", 0, createdDate,
		    scheduledDate);
	} catch (InvalidArgumentException e) {
	    assertEquals("Invalid origin account format, expected XXXXX-X",
		    e.getMessage());
	}
    }

    @Test
    public void testInvalidDestinationAccountShouldFailAndGiveMessage()
	    throws Exception {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.minusSeconds(1);

	try {
	    new ScheduledTransferMock("12345-6", "1234-6", 0, createdDate,
		    scheduledDate);
	} catch (InvalidArgumentException e) {
	    assertEquals(
		    "Invalid destination account format, expected XXXXX-X",
		    e.getMessage());
	}
    }

}
