package com.soursesense.emanuelepicariello.newscombinatorsoap.model;

import java.util.Date;

public abstract class NewsEntity {
	

	private String url;


	public abstract Date getData();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
