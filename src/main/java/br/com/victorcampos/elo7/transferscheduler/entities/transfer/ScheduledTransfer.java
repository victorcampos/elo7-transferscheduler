package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;
import org.joda.time.Period;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.entities.Account;

public abstract class ScheduledTransfer {

    private Account originAccount;
    private Account destinationAccount;
    private int transferAmount;
    private DateTime createdDate;
    private DateTime scheduledDate;
    private int fee;

    public abstract String getType();

    public abstract int calculateFee();

    public Account getOriginAccount() {
	return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
	this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
	return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
	this.destinationAccount = destinationAccount;
    }

    public int getTransferAmount() {
	return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
	this.transferAmount = transferAmount;
    }

    public DateTime getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(DateTime createdDate)
	    throws InvalidArgumentException {
	if (getScheduledDate() != null && createdDate != null) {
	    if (!isValidPeriodBetweenCreatedAndScheduledDates(createdDate,
		    getScheduledDate()))
		throw new InvalidArgumentException();

	    this.createdDate = createdDate;
	} else if (createdDate != null) {
	    this.createdDate = createdDate;
	}
    }

    public DateTime getScheduledDate() {
	return scheduledDate;
    }

    public void setScheduledDate(DateTime scheduledDate)
	    throws InvalidArgumentException {
	if (getCreatedDate() != null && scheduledDate != null) {
	    if (!isValidPeriodBetweenCreatedAndScheduledDates(getCreatedDate(),
		    scheduledDate))
		throw new InvalidArgumentException();

	    this.scheduledDate = scheduledDate;
	} else if (scheduledDate != null) {
	    this.scheduledDate = scheduledDate;
	}
    }

    private boolean isValidPeriodBetweenCreatedAndScheduledDates(
	    DateTime createdDate, DateTime scheduledDate) {
	Period period = new Period(createdDate, scheduledDate);
	if (period.toStandardDuration().getMillis() < 0)
	    return false;

	return true;
    }

    public int getFee() {
	return fee;
    }

    public void setFee(int fee) {
	this.fee = fee;
    }
}
