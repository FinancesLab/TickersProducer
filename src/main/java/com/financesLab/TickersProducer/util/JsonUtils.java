package com.financesLab.TickersProducer.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static String toJson(Object object) throws RuntimeException {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
