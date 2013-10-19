package br.com.victorcampos.elo7.transferscheduler.helpers;


public class AccountFormatValidator {
    
    public static boolean isValidFormat(String accountNumber) {
	return accountNumber.matches("[0-9]{5}-[0-9]");
    }

}
