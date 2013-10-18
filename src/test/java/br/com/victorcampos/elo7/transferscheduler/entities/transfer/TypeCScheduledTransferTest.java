package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TypeCScheduledTransferTest {

    @Test
    public void testGetType() {
	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();

	assertEquals("C", typeCScheduledTransfer.getType());
    }

}
