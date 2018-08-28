package com.medicine.main.config;

public interface RedisService {
	public Object getValue(String key);

	public void setValue(String key, Object value);

	public void deleteValue(String key);

}

