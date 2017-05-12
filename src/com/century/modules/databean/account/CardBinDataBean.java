package com.century.modules.databean.account;


/**
 * 
 */
public class CardBinDataBean {

	private String cardBin;
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行卡类型
	 */
	private String cardType;
	
	private String cardLenth;
	
	private String cardState;
	
	private String cardTypeLabel;

	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardLenth() {
		return cardLenth;
	}

	public void setCardLenth(String cardLenth) {
		this.cardLenth = cardLenth;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public String getCardTypeLabel() {
		return cardTypeLabel;
	}

	public void setCardTypeLabel(String cardTypeLabel) {
		this.cardTypeLabel = cardTypeLabel;
	}
	
}
