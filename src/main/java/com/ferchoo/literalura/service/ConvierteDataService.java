package com.ferchoo.literalura.service;

public interface ConvierteDataService {
	<T> T obtenerData(String json, Class<T> clase);
}
