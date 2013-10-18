package br.com.victorcampos.elo7.transferscheduler.calculator;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeBFeeCalculatorTest {

    @Test
    public void testCalculateFeeWhenScheduledDateBefore30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 10000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(10);

	FeeCalculable typeBFeeCalculator = new TypeBFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeBFeeCalculator.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 8000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	FeeCalculable typeBFeeCalculator = new TypeBFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeBFeeCalculator.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateIs30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 10000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(30);

	FeeCalculable typeBFeeCalculator = new TypeBFeeCalculator(100000, now,
		scheduledDate);

	assertEquals(expectedFee, typeBFeeCalculator.calculateFee());
    }

}
