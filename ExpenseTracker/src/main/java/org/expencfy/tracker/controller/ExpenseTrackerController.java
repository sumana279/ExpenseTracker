package org.expencfy.tracker.controller;

import org.expencfy.tracker.bean.DeleteIds;
import org.expencfy.tracker.bean.EditDetails;
import org.expencfy.tracker.bean.ExpenseGetResponse;
import org.expencfy.tracker.bean.ExpensePostResponse;
import org.expencfy.tracker.bean.ExpensePutResponse;
import org.expencfy.tracker.bean.ExpenseTracker;
import org.expencfy.tracker.bean.JsonMapper;
import org.expencfy.tracker.bean.Payload;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ExpenseTrackerController {
	Payload request;
	EditDetails requestDetatils;
	ExpenseTracker requestUpdateDetails;
	DeleteIds requestDeleteDetails;

	@RequestMapping(value = "/insert", method = RequestMethod.POST, headers = "Accept=application/json")
	public ExpensePostResponse addRecord(@RequestBody String inputData) {
		ExpenseHelper exphelp = new ExpenseHelper();

		JsonMapper jm = new JsonMapper();
		if (getValidtion(inputData)) {
			try {
				request = (Payload) jm.mapFromJson(inputData, Payload.class);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return exphelp.doPost(request);

	}

	private Boolean getValidtion(String inputData) {
		if (inputData != null) {
			return true;
		}
		return false;

	}

	@RequestMapping(value = "/get", method = RequestMethod.GET, headers = "Accept=application/json")
	public ExpenseGetResponse getRecord() {
		ExpenseHelper exphelp = new ExpenseHelper();

		return exphelp.doGet();
	}

	@RequestMapping(value = "/getByRange", method = RequestMethod.POST, headers = "Accept=application/json")
	public ExpenseGetResponse getRecordByFilter(@RequestBody String editDetails) {
		ExpenseHelper exphelp = new ExpenseHelper();
		JsonMapper jm = new JsonMapper();
		if (getValidtion(editDetails)) {
			try {
				requestDetatils = (EditDetails) jm.mapFromJson(editDetails, EditDetails.class);
				System.out.println("MadeHere");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return exphelp.doGetByRange(requestDetatils);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ExpensePutResponse editRecord(@RequestBody String editDetails) {
		ExpenseHelper exphelp = new ExpenseHelper();
		JsonMapper jm = new JsonMapper();
		if (getValidtion(editDetails)) {
			try {
				requestUpdateDetails = (ExpenseTracker) jm.mapFromJson(editDetails, ExpenseTracker.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return exphelp.doPut(requestUpdateDetails);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ExpensePutResponse deleteRecord(@RequestBody String editDetails) {
		ExpenseHelper exphelp = new ExpenseHelper();

		JsonMapper jm = new JsonMapper();
		if (getValidtion(editDetails)) {
			try {
				requestDeleteDetails = (DeleteIds) jm.mapFromJson(editDetails, DeleteIds.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return exphelp.doDelete(requestDeleteDetails);
	}
}
