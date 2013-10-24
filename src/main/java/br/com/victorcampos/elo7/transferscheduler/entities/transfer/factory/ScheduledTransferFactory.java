package br.com.victorcampos.elo7.transferscheduler.entities.transfer.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

public class ScheduledTransferFactory {

    public enum OperationTypes {
	A(TypeAScheduledTransfer.class), B(TypeBScheduledTransfer.class), C(
		TypeCScheduledTransfer.class), D(TypeDScheduledTransfer.class);

	private Class<? extends ScheduledTransfer> clazz;

	OperationTypes(Class<? extends ScheduledTransfer> scheduleTransferClass) {
	    this.clazz = scheduleTransferClass;
	}

	public static Class<? extends ScheduledTransfer> getScheduledTransferForOperationType(
		String type) {
	    return valueOf(type).clazz;
	}
    }

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
	try {
	    scheduledTransfer = buildTransferForOperationType(
	    	originAccount, destinationAccount, scheduledDate,
	    	transferAmount, operationType);
	} catch (NoSuchMethodException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvocationTargetException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
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
	    int transferAmount = Integer.parseInt(transferAmountParameter);

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
	    throws InvalidArgumentException, NoSuchMethodException,
	    SecurityException, InstantiationException, IllegalAccessException,
	    InvocationTargetException {
	ScheduledTransfer scheduledTransfer;
	DateTime now = new DateTime();

	try {
	    Class<? extends ScheduledTransfer> scheduledTransferForOperationType = OperationTypes
		    .getScheduledTransferForOperationType(operationType);
	    Constructor<? extends ScheduledTransfer> constructor = scheduledTransferForOperationType
		    .getConstructor(String.class, String.class, Integer.class,
			    DateTime.class, DateTime.class);
	    scheduledTransfer = constructor.newInstance(originAccount, destinationAccount,
		    transferAmount, now, scheduledDate);
	} catch (IllegalArgumentException e) {
	    throw new InvalidArgumentException(
		    "Invalid operation type, should be in [A, B, C, D]");
	}

	return scheduledTransfer;
    }
}
