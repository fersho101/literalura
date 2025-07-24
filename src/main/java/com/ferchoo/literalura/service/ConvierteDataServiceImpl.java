package com.ferchoo.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDataServiceImpl implements ConvierteDataService {
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public <T> T obtenerData(String json, Class<T> clase) {
		try {
			return objectMapper.readValue(json, clase);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
