package com.hudiilfeld.shiurdiario.models;

import java.util.List;

public class WebResponse_daf {

    private List<Daf> past_pages;

    public WebResponse_daf(List<Daf> past_pages) {
        this.past_pages = past_pages;
    }

    public List<Daf> getPast_pages() {
        return past_pages;
    }

    public void setPast_pages(List<Daf> d) {
        this.past_pages = d;
    }
}
