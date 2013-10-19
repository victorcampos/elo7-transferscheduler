package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class ScheduledTransferTest {

    @Test(expected = InvalidArgumentException.class)
    public void testSetCreatedDateAfterScheduledDateShouldFail()
	    throws Exception {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.plusSeconds(1);

	new ScheduledTransferMock("12345-6", "12345-6", 0, createdDate,
		scheduledDate);
    }

    @Test(expected = InvalidArgumentException.class)
    public void testSetScheduledDateBeforeCreatedDateShouldFail()
	    throws Exception {
	DateTime now = new DateTime();
	DateTime scheduledDate = now.minusSeconds(1);

	new ScheduledTransferMock("12345-6", "12345-6", 0, now,
		scheduledDate);
    }

}
