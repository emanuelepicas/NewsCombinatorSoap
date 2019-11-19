package com.soursesense.emanuelepicariello.newscombinatorsoap.model;

import java.util.Date;

public interface NewsInterface extends Comparable<NewsInterface> {

    String getUrl();

    String getTitle();

    Date getData();

}
