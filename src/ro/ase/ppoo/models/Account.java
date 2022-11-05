package ro.ase.ppoo.models;

import java.io.Serializable;

public class Account implements Serializable {
	private String iban;
	private int currentBalance;
	private String bank;
	
	public Account(String iban, int currentBalance, String bank) {
		super();
		this.iban = iban;
		this.currentBalance = currentBalance;
		this.bank = bank;
	}
	
	public String getIban() {
		return iban;
	}
	public int getCurrentBalance() {
		return currentBalance;
	}
	public String getBank() {
		return bank;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "Account [id=" + iban + ", currentBalance=" + currentBalance + ", bank=" + bank + "]";
	}
	
	
	
}
