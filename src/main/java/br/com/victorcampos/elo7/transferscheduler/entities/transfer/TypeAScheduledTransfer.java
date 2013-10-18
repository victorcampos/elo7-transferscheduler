package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

public class TypeAScheduledTransfer extends ScheduledTransfer {

    private static String TYPE = "A";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	// TODO: rounding errors on int casting will be a problem?
	int calculatedFee = (int) (getTransferAmount() * 0.03) + 200;

	return calculatedFee;
    }

}
