package br.com.ksgprod.domain;

import static br.com.ksgprod.utils.Dates.YYY_MM_DD_T_HH_MM_SS;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.google.gson.Gson;

public class DomainModel<T extends DomainModel<T>> {
	
	private String id;
	
	@Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = YYY_MM_DD_T_HH_MM_SS)
	private String timestamp;
	
	public String getId() {
		return id;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
