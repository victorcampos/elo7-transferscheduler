package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeBFeeCalculator;

public class TypeBScheduledTransfer extends ScheduledTransfer {

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
