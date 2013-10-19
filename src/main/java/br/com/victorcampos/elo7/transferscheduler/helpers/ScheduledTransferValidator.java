package br.com.victorcampos.elo7.transferscheduler.helpers;

import org.joda.time.DateTime;


public class ScheduledTransferValidator {
    
    public static boolean isValidFormat(String accountNumber) {
	return accountNumber.matches("[0-9]{5}-[0-9]");
    }
    
    public static boolean isValidPeriodBetweenCreatedAndScheduledDates(
	    DateTime createdDate, DateTime scheduledDate) {
	if (createdDate.compareTo(scheduledDate) > 0)
	    return false;

	return true;
    }

}
