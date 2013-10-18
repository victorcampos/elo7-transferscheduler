package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeDScheduledTransferTest {

    @Test
    public void testGetType() {
	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer();

	assertEquals("D", typeDScheduledTransfer.getType());
    }

    @Test
    public void testTypeAFeeCase() {
	int expectedFee = 5000;

	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer();
	typeDScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }

    @Test
    public void testTypeBFeeCaseWhenScheduledDateIsUnder30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 10000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(15);

	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer();
	typeDScheduledTransfer.setTransferAmount(25000010);
	typeDScheduledTransfer.setCreatedDate(now);
	typeDScheduledTransfer.setScheduledDate(scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }

    @Test
    public void testTypeBFeeCaseWhenScheduledDateIsAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 8000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer();
	typeDScheduledTransfer.setTransferAmount(25000010);
	typeDScheduledTransfer.setCreatedDate(now);
	typeDScheduledTransfer.setScheduledDate(scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testTypeCFeeCase()
	    throws InvalidArgumentException {
	int expectedFee = 5160043;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(21);

	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer();
	typeDScheduledTransfer.setTransferAmount(120001000);
	typeDScheduledTransfer.setCreatedDate(now);
	typeDScheduledTransfer.setScheduledDate(scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testTypeCFeeSecondCase()
	    throws InvalidArgumentException {
	int expectedFee = 8040067;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(15);

	ScheduledTransfer typeDScheduledTransfer = new TypeDScheduledTransfer();
	typeDScheduledTransfer.setTransferAmount(120001000);
	typeDScheduledTransfer.setCreatedDate(now);
	typeDScheduledTransfer.setScheduledDate(scheduledDate);

	assertEquals(expectedFee, typeDScheduledTransfer.calculateFee());
    }

}
