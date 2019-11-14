package com.soursesense.emanuelepicariello.newscombinatorsoap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HackerNewsEntity implements NewsInterface  {

	private String url;

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

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(NewsInterface o) {
		return this.getData().compareTo(o.getData());
	}
}