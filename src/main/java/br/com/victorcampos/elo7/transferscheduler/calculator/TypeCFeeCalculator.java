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
	float feeRatio = 0;

	if (getScheduledDate().isAfter(getCreatedDate().plusDays(30)))
	    feeRatio = 0.012f;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(25)))
	    feeRatio = 0.021f;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(20)))
	    feeRatio = 0.043f;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(15)))
	    feeRatio = 0.054f;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(10)))
	    feeRatio = 0.067f;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(5)))
	    feeRatio = 0.074f;
	else
	    feeRatio = 0.083f;

	return Math.round((getTransferAmount() * feeRatio) / 10.0f);
    }

    public int getTransferAmount() {
	return transferAmount * 10;
    }

    public DateTime getCreatedDate() {
	return createdDate;
    }

    public DateTime getScheduledDate() {
	return scheduledDate;
    }

}
