package br.com.victorcampos.elo7.transferscheduler.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TypeAFeeCalculatorTest {

    @Test
    public void testCalculateFee() {
	int expectedFee = 500;
	// set transfer amount to $100.00
	FeeCalculable typeAFeeCalculator = new TypeAFeeCalculator(10000);

	int calculatedFee = typeAFeeCalculator.calculateFee();
	assertEquals(expectedFee, calculatedFee);
    }

    @Test
    public void testCalculateFeeForALargerAmount() {
	int expectedFee = 300200;
	// set transfer amount to $100000.00
	FeeCalculable typeAFeeCalculator = new TypeAFeeCalculator(10000000);

	int calculatedFee = typeAFeeCalculator.calculateFee();
	assertEquals(expectedFee, calculatedFee);
    }

    @Test
    public void testCalculateFeeForASmallAmount() {
	int expectedFee = 200;
	// set transfer amount to $0.01
	FeeCalculable typeAFeeCalculator = new TypeAFeeCalculator(10);

	int calculatedFee = typeAFeeCalculator.calculateFee();
	assertEquals(expectedFee, calculatedFee);
    }

}
