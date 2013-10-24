package br.com.victorcampos.elo7.transferscheduler.entities.transfer.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.junit.Test;
import org.restlet.data.Form;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.ScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeAScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeBScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeCScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeDScheduledTransfer;

public class ScheduledTransferFactoryTest {

    @Test
    public void testBuildScheduledDateFromString() {
	DateTime scheduledDate = ScheduledTransferFactory
		.buildScheduledDate("2013-10-30");

	assertEquals(2013, scheduledDate.getYear());
	assertEquals(10, scheduledDate.getMonthOfYear());
	assertEquals(30, scheduledDate.getDayOfMonth());
    }

    @Test(expected = IllegalFieldValueException.class)
    public void testFailScheduledDateWithInvalidDate() {
	ScheduledTransferFactory.buildScheduledDate("2013-10-32");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailScheduledDateWithInvalidFormat() {
	ScheduledTransferFactory.buildScheduledDate("30-10-2013");
    }

    @Test
    public void testBuildFromPostData() throws InvalidArgumentException {
	Form postData = new Form();
	postData.add("origin_account", "12345-6");
	postData.add("destination_account", "23456-7");
	postData.add("scheduled_date", "2013-10-30");
	postData.add("transfer_amount", "10000");
	postData.add("operation_type", "A");

	ScheduledTransfer scheduledTransfer = ScheduledTransferFactory
		.buildFromPostData(postData);

	assertEquals("12345-6", scheduledTransfer.getOriginAccount());
	assertEquals("23456-7", scheduledTransfer.getDestinationAccount());
	assertEquals(2013, scheduledTransfer.getScheduledDate().getYear());
	assertEquals(10, scheduledTransfer.getScheduledDate().getMonthOfYear());
	assertEquals(30, scheduledTransfer.getScheduledDate().getDayOfMonth());
	assertEquals(10000, scheduledTransfer.getTransferAmount());
	assertEquals("A", scheduledTransfer.getType());
    }

    @Test
    public void testGetTransferAmount() throws InvalidArgumentException {
	int transferAmount = ScheduledTransferFactory
		.getTransferAmount("10000");

	assertEquals(10000, transferAmount);
    }

    @Test(expected = InvalidArgumentException.class)
    public void testShouldFailGetTransferAmountWhenNotNumeric()
	    throws InvalidArgumentException {
	ScheduledTransferFactory.getTransferAmount("AAAA");
    }

    @Test
    public void testBuildTransferForOperationTypeA()
	    throws InvalidArgumentException {
	String originAccount = "12345-6";
	String destinationAccount = "23456-7";
	DateTime scheduledDate = new DateTime().plusDays(10);
	int transferAmount = 100000;
	String operationType = "A";

	ScheduledTransfer scheduledTransfer = ScheduledTransferFactory
		.buildTransferForOperationType(originAccount,
			destinationAccount, scheduledDate, transferAmount,
			operationType);

	assertEquals("A", scheduledTransfer.getType());
    }

    @Test
    public void testBuildTransferForOperationTypeB()
	    throws InvalidArgumentException {
	String originAccount = "12345-6";
	String destinationAccount = "23456-7";
	DateTime scheduledDate = new DateTime().plusDays(10);
	int transferAmount = 100000;
	String operationType = "B";

	ScheduledTransfer scheduledTransfer = ScheduledTransferFactory
		.buildTransferForOperationType(originAccount,
			destinationAccount, scheduledDate, transferAmount,
			operationType);

	assertEquals("B", scheduledTransfer.getType());
    }

    @Test
    public void testBuildTransferForOperationTypeC()
	    throws InvalidArgumentException {
	String originAccount = "12345-6";
	String destinationAccount = "23456-7";
	DateTime scheduledDate = new DateTime().plusDays(10);
	int transferAmount = 100000;
	String operationType = "C";

	ScheduledTransfer scheduledTransfer = ScheduledTransferFactory
		.buildTransferForOperationType(originAccount,
			destinationAccount, scheduledDate, transferAmount,
			operationType);

	assertEquals("C", scheduledTransfer.getType());
    }

    @Test
    public void testBuildTransferForOperationTypeD()
	    throws InvalidArgumentException {
	String originAccount = "12345-6";
	String destinationAccount = "23456-7";
	DateTime scheduledDate = new DateTime().plusDays(10);
	int transferAmount = 100000;
	String operationType = "D";

	ScheduledTransfer scheduledTransfer = ScheduledTransferFactory
		.buildTransferForOperationType(originAccount,
			destinationAccount, scheduledDate, transferAmount,
			operationType);

	assertEquals("D", scheduledTransfer.getType());
    }

    @Test
    public void testBuildInvalidTransferOperationTypeShouldFailAndGiveMessage() {
	String originAccount = "12345-6";
	String destinationAccount = "23456-7";
	DateTime scheduledDate = new DateTime().plusDays(10);
	int transferAmount = 100000;
	String operationType = "E";

	try {
	    ScheduledTransferFactory.buildTransferForOperationType(
		    originAccount, destinationAccount, scheduledDate,
		    transferAmount, operationType);
	    fail("Should have thrown an exception");
	} catch (InvalidArgumentException e) {
	    assertEquals("Invalid operation type, should be in [A, B, C, D]",
		    e.getMessage());
	}

    }

    @Test
    public void testOperationTypesEnumShouldReturnClassForOperationTypeA() {
	Class<?> scheduledTransferClass = ScheduledTransferFactory.OperationTypes
		.getScheduledTransferForOperationType("A");
	assertEquals(TypeAScheduledTransfer.class, scheduledTransferClass);
    }
    
    @Test
    public void testOperationTypesEnumShouldReturnClassForOperationTypeB() {
	Class<?> scheduledTransferClass = ScheduledTransferFactory.OperationTypes
		.getScheduledTransferForOperationType("B");
	assertEquals(TypeBScheduledTransfer.class, scheduledTransferClass);
    }
    
    @Test
    public void testOperationTypesEnumShouldReturnClassForOperationTypeC() {
	Class<?> scheduledTransferClass = ScheduledTransferFactory.OperationTypes
		.getScheduledTransferForOperationType("C");
	assertEquals(TypeCScheduledTransfer.class, scheduledTransferClass);
    }
    
    @Test
    public void testOperationTypesEnumShouldReturnClassForOperationTypeD() {
	Class<?> scheduledTransferClass = ScheduledTransferFactory.OperationTypes
		.getScheduledTransferForOperationType("D");
	assertEquals(TypeDScheduledTransfer.class, scheduledTransferClass);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOperationTypesEnumShouldFailForInvalidOperationType() {
	Class<?> scheduledTransferClass = ScheduledTransferFactory.OperationTypes
		.getScheduledTransferForOperationType("X");
	assertEquals(TypeDScheduledTransfer.class, scheduledTransferClass);
    }

}
