package br.com.victorcampos.elo7.transferscheduler.entities.transfer;

public class TypeBScheduledTransfer extends ScheduledTransfer {
    
    private static String TYPE = "B";

    @Override
    public String getType() {
	return TYPE;
    }

    @Override
    public int calculateFee() {
	if (getScheduledDate().isAfter(getCreatedDate().plusDays(30)))
	    return 800;
	else
	    return 1000;
    }

}
