package org.expencfy.tracker.bean;

public class ExpenseGetResponse {
	private String status;
	private String message;
	private Payload payload;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}
}
