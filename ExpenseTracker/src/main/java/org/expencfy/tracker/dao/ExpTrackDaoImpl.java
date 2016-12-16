package org.expencfy.tracker.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.expencfy.tracker.bean.DeleteIds;
import org.expencfy.tracker.bean.EditDetails;
import org.expencfy.tracker.bean.ExpenseGetResponse;
import org.expencfy.tracker.bean.ExpensePostResponse;
import org.expencfy.tracker.bean.ExpensePutResponse;
import org.expencfy.tracker.bean.ExpenseTracker;
import org.expencfy.tracker.bean.Payload;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExpTrackDaoImpl implements ExpTrackDao {
	private static final String GET_RANGE = "from ExpenseTracker et where et.dates >= :beginDate and et.dates <= :endDate";
	private static final String GET_RANGE_CATEGORY = "from ExpenseTracker et where et.dates >= :beginDate and et.dates <= :endDate and et.category in (:categories)";
	private static final String GET = "from ExpenseTracker";
	private static final String UPDATED_DETAILS = "update ExpenseTracker et set et.amount = :amount,et.notes = :notes,et.dates = :dates,et.category = :category where et.id = :id ";
	private static final String DELETE_RECORD = "delete from ExpenseTracker et where et.id in (:id) ";
	private ExpensePostResponse expResp;
	ExpenseGetResponse expResponse = new ExpenseGetResponse();

	Session session = HibernateSessionManager.getSessionFactory().openSession();

	@Override
	public ExpensePostResponse doPost(Payload payload) {
		expResp = null;

		try {
			Transaction tx = session.beginTransaction();
			session.persist(payload);
			tx.commit();
			expResp = new ExpensePostResponse();
			expResp.setMessage("Success");
			expResp.setStatus("OK");

		} catch (Exception e) {
			e.printStackTrace();
			expResp = new ExpensePostResponse();
			expResp.setMessage("Failed to Insert into dB");
			expResp.setStatus("Error");
			return expResp;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return expResp;
	}

	@Override
	public ExpenseGetResponse doGet() {
		try {
			Query query = session.createQuery(GET);
			List<ExpenseTracker> expList = query.list();
			List<ExpenseTracker> expenses = new ArrayList<ExpenseTracker>();
			Payload payload = new Payload();
			for (ExpenseTracker expense : expList) {
				ExpenseTracker et = expense;
				expenses.add(et);
			}
			payload.setExpenses(expenses);
			expResponse.setPayload(payload);
			expResponse.setMessage("Success");
			expResponse.setStatus("OK");

		} catch (Exception e) {
			e.printStackTrace();
			expResponse.setMessage("Failed to Insert into dB");
			expResponse.setStatus("Error");
			return expResponse;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return expResponse;
	}

	@Override
	public ExpenseGetResponse doGetByRange(EditDetails editDetails) {
		Query query = null;
		try {
			if (editDetails.getCategories() != null) {
				query = session.createQuery(GET_RANGE_CATEGORY);
				query.setParameterList("categories", editDetails.getCategories());

			} else {
				query = session.createQuery(GET_RANGE);
			}
			query.setParameter("beginDate", editDetails.getStartDate());
			query.setParameter("endDate", editDetails.getEndDate());
			List<ExpenseTracker> expList = query.list();
			List<ExpenseTracker> expenses = new ArrayList<ExpenseTracker>();
			Payload payload = new Payload();
			for (ExpenseTracker expense : expList) {
				ExpenseTracker et = expense;
				expenses.add(et);
			}
			payload.setExpenses(expenses);
			expResponse.setPayload(payload);
			expResponse.setMessage("Success");
			expResponse.setStatus("OK");

		} catch (Exception e) {
			e.printStackTrace();
			expResponse.setMessage("Failed to Insert into dB");
			expResponse.setStatus("Error");

			return expResponse;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return expResponse;

	}

	@Override
	public ExpensePutResponse doPut(ExpenseTracker requestUpdateDetails) {
		ExpensePutResponse expPutResponse = new ExpensePutResponse();

		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(UPDATED_DETAILS);

			query.setParameter("dates", requestUpdateDetails.getDates());
			query.setParameter("category", requestUpdateDetails.getCategory());
			query.setParameter("amount", requestUpdateDetails.getAmount());
			query.setParameter("notes", requestUpdateDetails.getNotes());
			query.setParameter("id", requestUpdateDetails.getId());
			int rowsAffected = query.executeUpdate();
			tx.commit();
			if (rowsAffected > 0) {
				expPutResponse.setId(requestUpdateDetails.getId());
				expPutResponse.setMessage("Updated the reocrd for id " + requestUpdateDetails.getId());
				expPutResponse.setStatus("Success");
			} else {
				expPutResponse.setId(requestUpdateDetails.getId());
				expPutResponse.setMessage("Could not find the record for id " + requestUpdateDetails.getId());
				expPutResponse.setStatus("Failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			expPutResponse.setMessage("Failed to Update the dB");
			expPutResponse.setStatus("Error");

			return expPutResponse;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return expPutResponse;

	}

	@Override
	public ExpensePutResponse doDelete(DeleteIds deleteIds) {
		ExpensePutResponse expPutResponse = new ExpensePutResponse();

		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(DELETE_RECORD);
			query.setParameterList("id", deleteIds.getIds());
			int rowsAffected = query.executeUpdate();
			tx.commit();
			if (rowsAffected > 0) {
				expPutResponse.setMessage("Deleted the record for id ");
				expPutResponse.setStatus("Success");
				// expPutResponse.setId(deleteIds.getIds().toString());
			} else {
				// expPutResponse.setId(id);
				expPutResponse.setMessage("Could not find the record for id ");
				expPutResponse.setStatus("Failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			expPutResponse.setMessage("Failed to delete from dB");
			expPutResponse.setStatus("Error");

			return expPutResponse;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return expPutResponse;

	}
}
