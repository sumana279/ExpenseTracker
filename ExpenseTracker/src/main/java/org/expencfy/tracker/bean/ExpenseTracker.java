package org.expencfy.tracker.bean;

import java.util.Date;

public class ExpenseTracker {

	private int id;
	private Date dates;
	private String category;
	private String amount;
	private String notes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

}
