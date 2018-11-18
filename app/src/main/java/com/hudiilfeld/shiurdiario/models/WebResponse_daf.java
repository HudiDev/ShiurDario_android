package com.hudiilfeld.shiurdiario.models;

import java.util.List;

public class WebResponse_daf {

    private List<DafGemara> past_pages;

    public WebResponse_daf(List<DafGemara> past_pages) {
        this.past_pages = past_pages;
    }

    public List<DafGemara> getPast_pages() {
        return past_pages;
    }

    public void setPast_pages(List<DafGemara> d) {
        this.past_pages = d;
    }
}
