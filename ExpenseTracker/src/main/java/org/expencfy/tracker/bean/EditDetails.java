package org.expencfy.tracker.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class EditDetails {
	private Date startDate;
	private Date endDate;
	@JsonIgnoreProperties(value = { "categories" })
	private String[] categories;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

}
