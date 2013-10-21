package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import org.joda.time.DateTime;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeAFeeCalculator;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeBFeeCalculator;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeCFeeCalculator;

public class TypeDScheduledTransfer extends ScheduledTransfer {

    public TypeDScheduledTransfer(String originAccount,
	    String destinationAccount, int transferAmount,
	    DateTime createdDate, DateTime scheduledDate)
	    throws InvalidArgumentException {
	super(originAccount, destinationAccount, transferAmount, createdDate,
		scheduledDate);
    }

    private static String TYPE = "D";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	FeeCalculable feeCalculator;

	if (getTransferAmount() <= 2500000) {
	    feeCalculator = new TypeAFeeCalculator(getTransferAmount());
	} else if (getTransferAmount() <= 12000000) {
	    feeCalculator = new TypeBFeeCalculator(getCreatedDate(),
		    getScheduledDate());
	} else {
	    feeCalculator = new TypeCFeeCalculator(getTransferAmount(),
		    getCreatedDate(), getScheduledDate());
	}

	return feeCalculator.calculateFee();
    }

}
