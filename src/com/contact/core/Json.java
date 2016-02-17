package com.contact.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class Json {
	private static ObjectMapper mapper = new ObjectMapper();
	private static JsonFactory factory = mapper.getJsonFactory();

	public void writeJSON(HttpServletResponse response, Object obj)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		JsonGenerator responseJsonGenerator = factory.createJsonGenerator(
				response.getOutputStream(), JsonEncoding.UTF8);
		responseJsonGenerator.writeObject(obj);
	}
}
