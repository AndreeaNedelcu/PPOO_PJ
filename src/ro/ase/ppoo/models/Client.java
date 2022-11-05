package ro.ase.ppoo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	int cnp;
	String firstName;
	String lastName;
	String email;
	Card card;
	HashMap<String,Account> accounts;
	List<Transaction> transactions;
	
	
	public Client(int cnp, String firstName, String lastName, String email,	Card card, HashMap<String,Account> accounts, List<Transaction> transactions) {
		super();
		this.cnp = cnp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.card=new Card(card.cardNo, card.pinCode, card.CVV, card.benefits);
		this.accounts = new HashMap<>(accounts);
		this.transactions=new ArrayList<Transaction>(transactions);
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public int getCnp() {
		return cnp;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getEmail() {
		return email;
	}


	public HashMap<String, Account> getAccounts() {
		return accounts;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setCnp(int cnp) {
		this.cnp = cnp;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setAccounts(HashMap<String, Account> accounts) {
		this.accounts = new HashMap<String, Account>(accounts);
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = new ArrayList<>(transactions);;
	}
	
	

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		
		this.card.setCardNo(card.cardNo);
		this.card.setCVV(card.CVV);
		this.card.setPinCode(card.pinCode);
		this.card.setBenefits(card.benefits);
	}

	@Override
	public String toString() {
		return "Client [cnp=" + cnp + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", card=" + card + ", accounts=" + accounts + ", transactions=" + transactions + "]";
	}



	
	
	
	
	
	
	
}
