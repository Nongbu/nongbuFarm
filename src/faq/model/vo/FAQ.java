package faq.model.vo;

import java.sql.Connection;

public class FAQ implements java.io.Serializable{
	private static final long serialVersionUID = 3L;
	
	private int faqNum;
	private String faqCategory;
	private String faqTitle;
	private String faqContent;
	
	public FAQ() {}

	public FAQ(int faqNum, String faqCategory, String faqTitle, String faqContent) {
		super();
		this.faqNum = faqNum;
		this.faqCategory = faqCategory;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}

	public int getFaqNum() {
		return faqNum;
	}

	public void setFaqNum(int faqNum) {
		this.faqNum = faqNum;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getFaqCategory() {
		return faqCategory;
	}

	public void setFaqCategory(String faqCategory) {
		this.faqCategory = faqCategory;
	}

	@Override
	public String toString() {
		return "FAQ [faqNum=" + faqNum + ", faqCategory=" + faqCategory + ", faqTitle=" + faqTitle + ", faqContent="
				+ faqContent + "]";
	}

}

