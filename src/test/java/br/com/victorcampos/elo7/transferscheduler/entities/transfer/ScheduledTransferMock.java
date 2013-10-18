package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

public class ScheduledTransferMock extends ScheduledTransfer {

    @Override
    public String getType() {
	return "TestType";
    }

    @Override
    public int calculateFee() {
	return 0;
    }

}
