package org.expencfy.tracker.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

//import org.codehaus.jackson.JsonParseException;

public class JsonMapper {
	@SuppressWarnings("unchecked")
	public Object mapFromJson(String requestJson, Class classOfRequest) throws Exception {

		Object requestObject = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			requestObject = mapper.readValue(requestJson, classOfRequest);

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}

		return requestObject;
	}

}
