package br.com.victorcampos.elo7.transferscheduler.entities.transfer.factory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.restlet.data.Form;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.ScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeAScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeBScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeCScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeDScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.helpers.ScheduledTransferValidator;

public class ScheduledTransferFactory {

    // TODO: Refactor to an Enum when needed
    private static final Set<String> TYPES = new HashSet<String>(
	    Arrays.asList(new String[] { "A", "B", "C", "D" }));

    public static ScheduledTransfer buildFromPostData(Form postData)
	    throws InvalidArgumentException {
	String originAccount = postData.getFirstValue("origin_account");
	String destinationAccount = postData
		.getFirstValue("destination_account");

	String scheduledDateParameter = postData
		.getFirstValue("scheduled_date");
	String transferAmountParameter = postData
		.getFirstValue("transfer_amount");
	String operationType = postData.getFirstValue("operation_type");

	DateTime scheduledDate;
	try {
	    scheduledDate = buildScheduledDate(scheduledDateParameter);
	} catch (Exception e) {
	    throw new InvalidArgumentException();
	}

	int transferAmount = getTransferAmount(transferAmountParameter);

	ScheduledTransfer scheduledTransfer;
	if (ScheduledTransferValidator.isValidFormat(originAccount)
		&& ScheduledTransferValidator.isValidFormat(destinationAccount)
		&& TYPES.contains(operationType) && transferAmount > 0) {
	    scheduledTransfer = buildTransferForOperationType(originAccount,
		    destinationAccount, scheduledDate, transferAmount,
		    operationType);
	} else {
	    throw new InvalidArgumentException();
	}

	return scheduledTransfer;
    }

    public static DateTime buildScheduledDate(String date) {
	DateTimeFormatter dateTimeFormatter = DateTimeFormat
		.forPattern("yyyy-MM-dd");

	return dateTimeFormatter.parseDateTime(date);
    }

    public static int getTransferAmount(String transferAmountParameter)
	    throws InvalidArgumentException {
	if (StringUtils.isNotBlank(transferAmountParameter)
		&& StringUtils.isNumeric(transferAmountParameter)) {
	    int transferAmount = Integer.parseInt(transferAmountParameter) * 10;

	    return transferAmount;
	} else {
	    throw new InvalidArgumentException();
	}
    }

    // TODO: Refactor to builder classes and instantiate builders on Enum, get
    // rid of this method
    public static ScheduledTransfer buildTransferForOperationType(
	    String originAccount, String destinationAccount,
	    DateTime scheduledDate, int transferAmount, String operationType)
	    throws InvalidArgumentException {
	ScheduledTransfer scheduledTransfer;
	DateTime now = new DateTime();
	
	switch (operationType) {
	case "A":
	    scheduledTransfer = new TypeAScheduledTransfer(originAccount,
		    destinationAccount, transferAmount, now, scheduledDate);
	    break;
	case "B":
	    scheduledTransfer = new TypeBScheduledTransfer(originAccount,
		    destinationAccount, transferAmount, now, scheduledDate);
	    break;
	case "C":
	    scheduledTransfer = new TypeCScheduledTransfer(originAccount,
		    destinationAccount, transferAmount, now, scheduledDate);
	    break;
	case "D":
	    scheduledTransfer = new TypeDScheduledTransfer(originAccount,
		    destinationAccount, transferAmount, now, scheduledDate);
	    break;
	default:
	    scheduledTransfer = null;
	    break;
	}

	return scheduledTransfer;
    }

}
