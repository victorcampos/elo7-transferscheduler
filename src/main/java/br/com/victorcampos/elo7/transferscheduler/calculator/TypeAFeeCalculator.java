package br.com.victorcampos.elo7.transferscheduler.calculator;

public class TypeAFeeCalculator implements FeeCalculable {

    private int transferAmount;

    public TypeAFeeCalculator(int transferAmount) {
	this.transferAmount = transferAmount;
    }

    public int calculateFee() {
	// TODO: rounding errors on int casting will be a problem?
	// TODO: should the fee for 0.01 add at least another 0.01 or does it
	// round it down?
	int calculatedFee = (int) (getTransferAmount() * 0.03) + 2000;

	return calculatedFee;
    }

    public int getTransferAmount() {
	return transferAmount;
    }

}
