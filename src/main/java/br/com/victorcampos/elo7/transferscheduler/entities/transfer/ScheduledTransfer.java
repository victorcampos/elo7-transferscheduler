package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import java.io.Serializable;

import org.joda.time.DateTime;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.helpers.ScheduledTransferValidator;

public abstract class ScheduledTransfer implements Serializable {

    private static final long serialVersionUID = 2494135117769099151L;

    private String uuid;
    private String originAccount;
    private String destinationAccount;
    private int transferAmount;
    private DateTime createdDate;
    private DateTime scheduledDate;
    private int fee;

    public abstract String getType();

    public abstract int calculateFee();

    public ScheduledTransfer(String originAccount, String destinationAccount,
	    int transferAmount, DateTime createdDate, DateTime scheduledDate)
	    throws InvalidArgumentException {
	setOriginAccount(originAccount);
	setDestinationAccount(destinationAccount);

	this.transferAmount = transferAmount;

	setCreatedDate(createdDate);
	setScheduledDate(scheduledDate);
    }

    public String getUuid() {
	return uuid;
    }

    public void setUuid(String uuid) {
	this.uuid = uuid;
    }

    public String getOriginAccount() {
	return originAccount;
    }

    public void setOriginAccount(String originAccount)
	    throws InvalidArgumentException {
	if (ScheduledTransferValidator.isValidFormat(originAccount)) {
	    this.originAccount = originAccount;
	} else {
	    throw new InvalidArgumentException(
		    "Invalid origin account format, expected XXXXX-X");
	}
    }

    public String getDestinationAccount() {
	return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount)
	    throws InvalidArgumentException {
	if (ScheduledTransferValidator.isValidFormat(destinationAccount)) {
	    this.destinationAccount = destinationAccount;
	} else {
	    throw new InvalidArgumentException(
		    "Invalid destination account format, expected XXXXX-X");
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
	    if (!ScheduledTransferValidator
		    .isValidPeriodBetweenCreatedAndScheduledDates(createdDate,
			    getScheduledDate()))
		throw new InvalidArgumentException(
			"Created date should be before scheduled date");

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
	    if (!ScheduledTransferValidator
		    .isValidPeriodBetweenCreatedAndScheduledDates(
			    getCreatedDate(), scheduledDate))
		throw new InvalidArgumentException(
			"Scheduled date should be after created date");

	    this.scheduledDate = scheduledDate;
	} else if (scheduledDate != null) {
	    this.scheduledDate = scheduledDate;
	}
    }

    public int getFee() {
	return fee;
    }

    public void setFee(int fee) {
	this.fee = fee;
    }
}
