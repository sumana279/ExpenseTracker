package org.expencfy.tracker.controller;

import org.expencfy.tracker.bean.DeleteIds;
import org.expencfy.tracker.bean.EditDetails;
import org.expencfy.tracker.bean.ExpenseGetResponse;
import org.expencfy.tracker.bean.ExpensePostResponse;
import org.expencfy.tracker.bean.ExpensePutResponse;
import org.expencfy.tracker.bean.ExpenseTracker;
import org.expencfy.tracker.bean.Payload;
import org.expencfy.tracker.dao.ExpTrackDaoImpl;

public class ExpenseHelper {
	private static volatile ExpenseHelper expHelper = null;
	private ExpTrackDaoImpl expTackDao;

	public ExpenseHelper() {
		expTackDao = new ExpTrackDaoImpl();
	}

	public ExpTrackDaoImpl getExpTackDao() {
		return expTackDao;
	}

	public void setExpTackDao(ExpTrackDaoImpl expTackDao) {
		this.expTackDao = expTackDao;
	}

	public ExpensePostResponse doPost(Payload payload) {
		return expTackDao.doPost(payload);
	}

	public ExpenseGetResponse doGet() {
		return expTackDao.doGet();
	}

	public ExpenseGetResponse doGetByRange(EditDetails editDetails) {
		return expTackDao.doGetByRange(editDetails);
	}

	public ExpensePutResponse doPut(ExpenseTracker requestUpdateDetails) {
		return expTackDao.doPut(requestUpdateDetails);
	}

	public ExpensePutResponse doDelete(DeleteIds requestDeleteDetails) {
		// TODO Auto-generated method stub
		return expTackDao.doDelete(requestDeleteDetails);
	}

}
