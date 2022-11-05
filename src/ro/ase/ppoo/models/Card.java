package ro.ase.ppoo.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	int cardNo;
	int pinCode;

	int CVV;
	String[] benefits;

	public Card(int cardNo, int pinCode, int cVV, String[] benefits) {
		super();
		this.cardNo = cardNo;
		this.pinCode = pinCode;

		this.CVV = cVV;
		this.benefits = Arrays.copyOf(benefits, benefits.length);
	}

	public int getCardNo() {
		return cardNo;
	}

	public int getPinCode() {
		return pinCode;
	}



	public int getCVV() {
		return CVV;
	}

	public String[] getBenefits() {
		return benefits;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}


	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public void setBenefits(String[] benefits) {
		this.benefits = Arrays.copyOf(benefits, benefits.length);
	}

	@Override
	public String toString() {
		return "Card [cardNo=" + cardNo + ", pinCode=" + pinCode +  ", CVV=" + CVV
				+ ", benefits=" + Arrays.toString(benefits) + "]";
	}

}
