package ro.ase.ppoo.models;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	
	int id;
	int moneyAmount;
	TransactionType type;
	
	
	public Transaction(int id,  int moneyAmount, TransactionType type) {
		super();
		this.id = id;
		this.moneyAmount = moneyAmount;
		this.type = type;
	}


	public int getId() {
		return id;
	}




	public int getMoneyAmount() {
		return moneyAmount;
	}


	public TransactionType getType() {
		return type;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}


	public void setType(TransactionType type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", moneyAmount=" + moneyAmount + ", type=" + type + "]";
	}

	
	
	
}
