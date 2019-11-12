package com.soursesense.emanuelepicariello.newscombinatorsoap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HackerNewsEntity extends NewsEntity {


	private Date time;
	

	private String title;


	public void setTime(Long time) {
		Date timeConverted = new java.util.Date(((long) time * 1000));
		this.time = timeConverted;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Date getData() {
		return time;
	}



}