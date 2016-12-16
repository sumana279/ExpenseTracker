package org.expencfy.tracker.dao;

import org.expencfy.tracker.bean.DeleteIds;
import org.expencfy.tracker.bean.EditDetails;
import org.expencfy.tracker.bean.ExpenseGetResponse;
import org.expencfy.tracker.bean.ExpensePostResponse;
import org.expencfy.tracker.bean.ExpensePutResponse;
import org.expencfy.tracker.bean.ExpenseTracker;
import org.expencfy.tracker.bean.Payload;

public interface ExpTrackDao {
	ExpensePostResponse doPost(Payload payload);

	ExpenseGetResponse doGet();

	ExpenseGetResponse doGetByRange(EditDetails editDetails);

	ExpensePutResponse doPut(ExpenseTracker updateDetails);

	ExpensePutResponse doDelete(DeleteIds deleteIds);

}
