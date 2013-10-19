package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeAScheduledTransferTest {

    @Test
    public void testGetType() throws InvalidArgumentException {
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(1);
	ScheduledTransfer typeAScheduledTransfer = new TypeAScheduledTransfer(
		"12345-6", "12345-6", 0, now, scheduledDate);

	assertEquals("A", typeAScheduledTransfer.getType());
    }

}
