package br.com.victorcampos.elo7.transferscheduler.calculator;

import org.joda.time.DateTime;

public class TypeBFeeCalculator implements FeeCalculable {

    private final int transferAmount;
    private final DateTime createdDate;
    private final DateTime scheduledDate;

    public TypeBFeeCalculator(int transferAmount, DateTime createdDate,
	    DateTime scheduledDate) {
	this.transferAmount = transferAmount;
	this.createdDate = createdDate;
	this.scheduledDate = scheduledDate;
    }

    public int calculateFee() {
	if (getScheduledDate().isAfter(getCreatedDate().plusDays(30)))
	    return 8000;
	else
	    return 10000;
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
