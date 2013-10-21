package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeDScheduledTransferTest {

    @Test
    public void testGetType() throws InvalidArgumentException {
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(1);
	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer(
		"12345-6", "12345-6", 0, now, scheduledDate);

	assertEquals("D", typeDScheduledTransfer.getType());
    }

    @Test
    public void testTypeAFeeCase() throws InvalidArgumentException {
	int expectedFee = 500;

	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(1);
	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer(
		"12345-6", "12345-6", 10000, now, scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }

    @Test
    public void testTypeBFeeCaseWhenScheduledDateIsUnder30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 1000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(15);
	
	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer(
		"12345-6", "12345-6", 2500001, now, scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }

    @Test
    public void testTypeBFeeCaseWhenScheduledDateIsAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 800;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer(
		"12345-6", "12345-6", 2500001, now, scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testTypeCFeeCase()
	    throws InvalidArgumentException {
	int expectedFee = 516004;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(21);
	
	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer(
		"12345-6", "12345-6", 12000100, now, scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testTypeCFeeSecondCase()
	    throws InvalidArgumentException {
	int expectedFee = 804007;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(15);

	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer(
		"12345-6", "12345-6", 12000100, now, scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }

}
