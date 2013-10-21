package br.com.victorcampos.elo7.transferscheduler.calculator;

import org.joda.time.DateTime;

public class TypeBFeeCalculator implements FeeCalculable {

    private final DateTime createdDate;
    private final DateTime scheduledDate;

    public TypeBFeeCalculator(DateTime createdDate,
	    DateTime scheduledDate) {
	this.createdDate = createdDate;
	this.scheduledDate = scheduledDate;
    }

    public int calculateFee() {
	if (getScheduledDate().isAfter(getCreatedDate().plusDays(30)))
	    return 800;
	else
	    return 1000;
    }

    public DateTime getCreatedDate() {
	return createdDate;
    }

    public DateTime getScheduledDate() {
	return scheduledDate;
    }

}
