package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;

public class ScheduledTransferMock extends ScheduledTransfer {
    
    private static final long serialVersionUID = 1L;

    public ScheduledTransferMock(String originAccount,
	    String destinationAccount, int transferAmount,
	    DateTime createdDate, DateTime scheduledDate)
	    throws InvalidArgumentException {
	super(originAccount, destinationAccount, transferAmount, createdDate,
		scheduledDate);
    }

    @Override
    public String getType() {
	return "TestType";
    }

    @Override
    public int calculateFee() {
	return 0;
    }

}
