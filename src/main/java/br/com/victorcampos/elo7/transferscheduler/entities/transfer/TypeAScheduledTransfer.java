package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeAFeeCalculator;

public class TypeAScheduledTransfer extends ScheduledTransfer {

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
