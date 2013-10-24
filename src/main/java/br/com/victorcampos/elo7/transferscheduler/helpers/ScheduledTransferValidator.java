package br.com.victorcampos.elo7.transferscheduler.helpers;

import org.joda.time.DateTime;

public class ScheduledTransferValidator {

    public static boolean isValidFormat(String accountNumber) {
	return accountNumber.matches("[0-9]{5}-[0-9]");
    }

    public static boolean isValidCreatedDate(DateTime createdDate,
	    DateTime scheduledDate) {
	boolean isValid = false;

	if (scheduledDate == null) {
	    isValid = true;
	} else if (createdDate != null) {
	    isValid = isValidInterval(createdDate, scheduledDate);
	}

	return isValid;
    }

    public static boolean isValidScheduledDate(DateTime createdDate,
	    DateTime scheduledDate) {
	boolean isValid = false;

	if (createdDate == null) {
	    isValid = true;
	} else if (scheduledDate != null) {
	    isValid = isValidInterval(createdDate, scheduledDate);
	}

	return isValid;
    }

    private static boolean isValidInterval(DateTime createdDate,
	    DateTime scheduledDate) {
	return createdDate.compareTo(scheduledDate) <= 0;
    }

    public static boolean isValidTransferAmount(int transferAmount) {
	return transferAmount > 0;
    }

}
