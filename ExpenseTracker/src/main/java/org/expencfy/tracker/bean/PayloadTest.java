package org.expencfy.tracker.bean;

import java.util.ArrayList;

import com.google.gson.Gson;

public class PayloadTest {
	public static void main(String[] args) {
		DeleteIds ed = new DeleteIds();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ids.add(10);
		ids.add(2);
		ed.setIds(ids);

		Gson gson = new Gson();
		String json = gson.toJson(ed);
		System.out.println(json);
	}
}
