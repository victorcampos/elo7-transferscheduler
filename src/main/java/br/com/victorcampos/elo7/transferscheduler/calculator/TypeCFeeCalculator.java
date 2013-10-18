package br.com.victorcampos.elo7.transferscheduler.calculator;

import org.joda.time.DateTime;

public class TypeCFeeCalculator implements FeeCalculable {

    private final int transferAmount;
    private final DateTime createdDate;
    private final DateTime scheduledDate;

    public TypeCFeeCalculator(int transferAmount, DateTime createdDate,
	    DateTime scheduledDate) {
	this.transferAmount = transferAmount;
	this.createdDate = createdDate;
	this.scheduledDate = scheduledDate;
    }

    public int calculateFee() {
	double feeRatio = 0;

	if (getScheduledDate().isAfter(getCreatedDate().plusDays(30)))
	    feeRatio = 0.012;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(25)))
	    feeRatio = 0.021;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(20)))
	    feeRatio = 0.043;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(15)))
	    feeRatio = 0.054;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(10)))
	    feeRatio = 0.067;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(5)))
	    feeRatio = 0.074;
	else
	    feeRatio = 0.083;

	return (int) (getTransferAmount() * feeRatio);
    }

    public int getTransferAmount() {
	return transferAmount;
    }

    public DateTime getCreatedDate() {
	return createdDate;
    }

    public DateTime getScheduledDate() {
	return scheduledDate;
    }

}
