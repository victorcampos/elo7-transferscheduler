package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeAFeeCalculator;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeBFeeCalculator;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeCFeeCalculator;

public class TypeDScheduledTransfer extends ScheduledTransfer {

    private static String TYPE = "D";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	FeeCalculable feeCalculator;
	
	if (getTransferAmount() <= 25000000) {
	    feeCalculator = new TypeAFeeCalculator(getTransferAmount());
	} else if (getTransferAmount() <= 120000000) {
	    feeCalculator = new TypeBFeeCalculator(getTransferAmount(), getCreatedDate(), getScheduledDate());
	} else {
	    feeCalculator = new TypeCFeeCalculator(getTransferAmount(), getCreatedDate(), getScheduledDate());
	}

	return feeCalculator.calculateFee();
    }

}
