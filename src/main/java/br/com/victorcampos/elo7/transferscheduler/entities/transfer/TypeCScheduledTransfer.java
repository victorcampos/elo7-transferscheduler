package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

import br.com.victorcampos.elo7.transferscheduler.calculator.FeeCalculable;
import br.com.victorcampos.elo7.transferscheduler.calculator.TypeCFeeCalculator;

public class TypeCScheduledTransfer extends ScheduledTransfer {

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
