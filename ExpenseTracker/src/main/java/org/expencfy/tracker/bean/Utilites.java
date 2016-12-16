package org.expencfy.tracker.bean;

import java.sql.Timestamp;
import java.util.Date;

public class Utilites {
	public static Date toSqlDate(Date date) {
		return new Timestamp(date.getTime());

	}

}
