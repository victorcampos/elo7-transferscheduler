package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.*;

import org.junit.Test;

public class TypeAScheduledTransferTest {

    @Test
    public void testGetType() {
	ScheduledTransfer typeAScheduledTransfer = new TypeAScheduledTransfer();
	
	assertEquals("A", typeAScheduledTransfer.getType());
    }
   
}
