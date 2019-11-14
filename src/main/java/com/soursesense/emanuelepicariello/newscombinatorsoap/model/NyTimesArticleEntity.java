package com.soursesense.emanuelepicariello.newscombinatorsoap.model;

import java.util.Date;

public class NyTimesArticleEntity implements NewsInterface {

	private String url;

	private String title;

	private Date created_date;


	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	@Override
	public Date getData() {
		return created_date;
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