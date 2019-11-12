package com.soursesense.emanuelepicariello.newscombinatorsoap.model;

import java.util.Date;

public class NyTimesArticleEntity extends NewsEntity {

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

}