package org.expencfy.tracker.bean;

import java.util.List;

public class Payload {

	private int id;

	private List<ExpenseTracker> expenses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ExpenseTracker> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<ExpenseTracker> expenses) {
		this.expenses = expenses;
	}

}
