package br.com.victorcampos.elo7.transferscheduler.calculator;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class TypeBFeeCalculatorTest {

    @Test
    public void testCalculateFeeWhenScheduledDateBefore30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 1000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(10);

	FeeCalculable typeBFeeCalculator = new TypeBFeeCalculator(now,
		scheduledDate);

	assertEquals(expectedFee, typeBFeeCalculator.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateAfter30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 800;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(31);

	FeeCalculable typeBFeeCalculator = new TypeBFeeCalculator(now,
		scheduledDate);

	assertEquals(expectedFee, typeBFeeCalculator.calculateFee());
    }

    @Test
    public void testCalculateFeeWhenScheduledDateIs30DaysFromCreation()
	    throws InvalidArgumentException {
	int expectedFee = 1000;
	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(30);

	FeeCalculable typeBFeeCalculator = new TypeBFeeCalculator(now,
		scheduledDate);

	assertEquals(expectedFee, typeBFeeCalculator.calculateFee());
    }

}
