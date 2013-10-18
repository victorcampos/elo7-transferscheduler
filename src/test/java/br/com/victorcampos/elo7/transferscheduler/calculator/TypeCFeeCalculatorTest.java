package br.com.victorcampos.elo7.transferscheduler.calculator;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeCFeeCalculatorTest {
    
    @Test
    public void testCalculateFeeWhenScheduledDateAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 1200;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }

    @Test
    public void testCalculateFeeForADifferentAmountWhenScheduledDateAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 2400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);
	
	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(200000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateBetween30and25DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 2100;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(30);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateBetween30and25DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 2100;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(26);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween25and20DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 4300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(25);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween25and20DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 4300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(21);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween20and15DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 5400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(20);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween20and15DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 5400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(16);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween15and10DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 6700;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(15);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween15and10DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 6700;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(11);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween10and5DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 7400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(10);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateBetween10and5DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 7400;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(6);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateIsUnder5DaysUpperBound()
	    throws InvalidArgumentException {
	int expectedFee = 8300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(5);

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }
    
    @Test
    public void testCalculateFeeWhenScheduledDateIsUnder5DaysLowerBound()
	    throws InvalidArgumentException {
	int expectedFee = 8300;
	DateTime now = new DateTime();
	DateTime scheduledDate = now;

	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeCFeeCalculator.calculateFee());
    }

}
