package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeCScheduledTransferTest {

    @Test
    public void testGetType() {
	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();

	assertEquals("C", typeCScheduledTransfer.getType());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 1200;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }

    @Test
    public void testCalculateFeeForADifferentAmountWhenScheduledDateAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 2400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(200000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateBetween30and25DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 2100;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(30);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateBetween30and25DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 2100;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(26);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween25and20DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 4300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(25);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween25and20DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 4300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(21);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween20and15DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 5400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(20);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween20and15DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 5400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(16);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween15and10DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 6700;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(15);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween15and10DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 6700;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(11);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween10and5DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 7400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(10);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween10and5DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 7400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(6);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateIsUnder5DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 8300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(5);

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateIsUnder5DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 8300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now;

	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer();
	typeCScheduledTransfer.setCreatedDate(now);
	typeCScheduledTransfer.setScheduledDate(scheduledDate);
	typeCScheduledTransfer.setTransferAmount(100000);

	assertEquals(expectedFee, typeCScheduledTransfer.calculateFee());
    }

}
