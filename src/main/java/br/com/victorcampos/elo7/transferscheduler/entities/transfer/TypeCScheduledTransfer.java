package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

public class TypeCScheduledTransfer extends ScheduledTransfer {
    
    private static String TYPE = "C";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	double feeRatio = 0;
	
	if (getScheduledDate().isAfter(getCreatedDate().plusDays(30)))
	    feeRatio = 0.012;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(25)))
	    feeRatio = 0.021;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(20)))
	    feeRatio = 0.043;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(15)))
	    feeRatio = 0.054;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(10)))
	    feeRatio = 0.067;
	else if (getScheduledDate().isAfter(getCreatedDate().plusDays(5)))
	    feeRatio = 0.074;
	else
	    feeRatio = 0.083;
	
	return (int) (getTransferAmount() * feeRatio);
    }

}
