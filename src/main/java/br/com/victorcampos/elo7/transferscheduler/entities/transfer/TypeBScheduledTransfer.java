package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeBFeeCalculator;

public class TypeBScheduledTransfer extends ScheduledTransfer {

    public TypeBScheduledTransfer(String originAccount,
	    String destinationAccount, int transferAmount,
	    DateTime createdDate, DateTime scheduledDate)
	    throws InvalidArgumentException {
	super(originAccount, destinationAccount, transferAmount, createdDate,
		scheduledDate);
    }

    private static String TYPE = "B";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	FeeCalculable typeBFeeCalculator = new TypeBFeeCalculator(
		getTransferAmount(), getCreatedDate(), getScheduledDate());

	return typeBFeeCalculator.calculateFee();
    }

}
