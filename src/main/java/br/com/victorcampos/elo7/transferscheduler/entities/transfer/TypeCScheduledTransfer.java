package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeCFeeCalculator;

public class TypeCScheduledTransfer extends ScheduledTransfer {

    public TypeCScheduledTransfer(String originAccount,
	    String destinationAccount, int transferAmount,
	    DateTime createdDate, DateTime scheduledDate)
	    throws InvalidArgumentException {
	super(originAccount, destinationAccount, transferAmount, createdDate,
		scheduledDate);
    }

    private static String TYPE = "C";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	FeeCalculable typeCFeeCalculator = new TypeCFeeCalculator(
		getTransferAmount(), getCreatedDate(), getScheduledDate());

	return typeCFeeCalculator.calculateFee();
    }

}
