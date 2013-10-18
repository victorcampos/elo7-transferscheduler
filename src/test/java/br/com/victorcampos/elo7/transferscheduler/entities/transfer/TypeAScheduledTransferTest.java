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
	int expectedFee = 5000;
	ScheduledTransfer typeAScheduledTransfer = new TypeAScheduledTransfer();
	
	// set transfer amount to $100.00
	typeAScheduledTransfer.setTransferAmount(100000);
	int calculatedFee = typeAScheduledTransfer.calculateFee();
	
	assertEquals(expectedFee, calculatedFee);
    }
    
    @Test
    public void testCalculateFeeForALargerAmount() {
	int expectedFee = 3002000;
	ScheduledTransfer typeAScheduledTransfer = new TypeAScheduledTransfer();
	
	// set transfer amount to $100000.00
	typeAScheduledTransfer.setTransferAmount(100000000);
	int calculatedFee = typeAScheduledTransfer.calculateFee();
	
	assertEquals(expectedFee, calculatedFee);
    }
    
    @Test
    public void testCalculateFeeForASmallAmount() {
	int expectedFee = 2000;
	ScheduledTransfer typeAScheduledTransfer = new TypeAScheduledTransfer();
	
	// set transfer amount to $0.01
	typeAScheduledTransfer.setTransferAmount(10);
	int calculatedFee = typeAScheduledTransfer.calculateFee();
	
	assertEquals(expectedFee, calculatedFee);
    }

}
