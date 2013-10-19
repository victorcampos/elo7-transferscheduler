package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeAFeeCalculator;

public class TypeAScheduledTransfer extends ScheduledTransfer {

    public TypeAScheduledTransfer(String originAccount,
	    String destinationAccount, int transferAmount,
	    DateTime createdDate, DateTime scheduledDate)
	    throws InvalidArgumentException {
	super(originAccount, destinationAccount, transferAmount, createdDate,
		scheduledDate);
    }

    private static String TYPE = "A";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	FeeCalculable calculator = new TypeAFeeCalculator(getTransferAmount());
	
	return calculator.calculateFee();
    }

}
