package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.helpers.AccountFormatValidator;

public abstract class ScheduledTransfer {

    private String originAccount;
    private String destinationAccount;
    private int transferAmount;
    private DateTime createdDate;
    private DateTime scheduledDate;
    private int fee;

    public abstract String getType();

    public abstract int calculateFee();

    public ScheduledTransfer(String originAccount, String destinationAccount,
	    int transferAmount, DateTime createdDate, DateTime scheduledDate) throws InvalidArgumentException {
	setOriginAccount(originAccount);
	setDestinationAccount(destinationAccount);
	
	this.transferAmount = transferAmount;
	
	setCreatedDate(createdDate);
	setScheduledDate(scheduledDate);
    }

    public String getOriginAccount() {
	return originAccount;
    }

    public void setOriginAccount(String originAccount)
	    throws InvalidArgumentException {
	if (AccountFormatValidator.isValidFormat(originAccount)) {
	    this.originAccount = originAccount;
	} else {
	    throw new InvalidArgumentException();
	}
    }

    public String getDestinationAccount() {
	return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount)
	    throws InvalidArgumentException {
	if (AccountFormatValidator.isValidFormat(destinationAccount)) {
	    this.destinationAccount = destinationAccount;
	} else {
	    throw new InvalidArgumentException();
	}
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
	if (createdDate.compareTo(scheduledDate) > 0)
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
