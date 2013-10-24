package br.com.victorcampos.elo7.transferscheduler;

public class InvalidArgumentException extends Exception {

    private static final long serialVersionUID = -9022484748418266335L;
    
    public InvalidArgumentException() {
    }
    
    public InvalidArgumentException(String message) {
	super(message);
    }

}
