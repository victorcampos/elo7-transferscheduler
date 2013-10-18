package br.com.victorcampos.elo7.transferscheduler.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TypeAFeeCalculatorTest {

    @Test
    public void testCalculateFee() {
	int expectedFee = 5000;
	// set transfer amount to $100.00
	FeeCalculable typeAFeeCalculator = new TypeAFeeCalculator(100000);

	int calculatedFee = typeAFeeCalculator.calculateFee();
	assertEquals(expectedFee, calculatedFee);
    }

    @Test
    public void testCalculateFeeForALargerAmount() {
	int expectedFee = 3002000;
	// set transfer amount to $100000.00
	FeeCalculable typeAFeeCalculator = new TypeAFeeCalculator(100000000);

	int calculatedFee = typeAFeeCalculator.calculateFee();
	assertEquals(expectedFee, calculatedFee);
    }

    @Test
    public void testCalculateFeeForASmallAmount() {
	int expectedFee = 2000;
	// set transfer amount to $0.01
	FeeCalculable typeAFeeCalculator = new TypeAFeeCalculator(10);

	int calculatedFee = typeAFeeCalculator.calculateFee();
	assertEquals(expectedFee, calculatedFee);
    }

}
