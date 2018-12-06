package com.hudiilfeld.shiurdiario.models;

import java.util.List;

public class WebResponse_shiurDaf {

    List<Daf> pages;

    public WebResponse_shiurDaf(List<Daf> pages) {
        this.pages = pages;
    }


    public List<Daf> getPages() {
        return pages;
    }

    public void setPages(List<Daf> pages) {
        this.pages = pages;
    }
}

