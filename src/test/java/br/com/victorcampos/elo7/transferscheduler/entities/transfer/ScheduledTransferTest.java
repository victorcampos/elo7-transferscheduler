package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class ScheduledTransferTest {
    
    @Test(expected=InvalidArgumentException.class)
    public void testSetCreatedDateAfterScheduledDateShouldFail() throws Exception {
	DateTime scheduledDate = new DateTime();
	DateTime createdDate = scheduledDate.plusSeconds(1);
	
	ScheduledTransfer scheduledTransfer = new ScheduledTransferMock();
	scheduledTransfer.setScheduledDate(scheduledDate);
	scheduledTransfer.setCreatedDate(createdDate);
    }
    
    @Test(expected=InvalidArgumentException.class)
    public void testSetScheduledDateBeforeCreatedDateShouldFail() throws Exception {
	DateTime now = new DateTime();
	DateTime scheduledDate = now.minusSeconds(1);
	
	ScheduledTransfer scheduledTransfer = new ScheduledTransferMock();
	scheduledTransfer.setCreatedDate(now);
	scheduledTransfer.setScheduledDate(scheduledDate);
    }

}
