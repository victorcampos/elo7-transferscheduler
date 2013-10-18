package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.*;

import org.junit.Test;

public class TypeAScheduledTransferTest {

    @Test
    public void testGetType() {
	ScheduledTransfer typeAScheduledTransfer = new TypeAScheduledTransfer();
	
	assertEquals("A", typeAScheduledTransfer.getType());
    }

    @Test
    public void testCalculateFee() {
	int expectedFee = 500;
	ScheduledTransfer typeAScheduledTransfer = new TypeAScheduledTransfer();
	
	// set transfer amount to $100.00
	typeAScheduledTransfer.setTransferAmount(10000);
	int calculatedFee = typeAScheduledTransfer.calculateFee();
	
	assertEquals(expectedFee, calculatedFee);
    }

}
