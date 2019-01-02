package com.hudiilfeld.shiurdiario.models;

import java.util.List;

public class WebResponse_shiurDaf {

    private List<Daf> dapim;
    private List<Page> pages;



    public List<Daf> getDapim() {
        return dapim;
    }

    public void setDapim(List<Daf> dapim) {
        this.dapim = dapim;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }


    private class Page {

        int page_num;

        public int getPage_num() { return page_num; }
        public void setPage_num(int page_num) { this.page_num = page_num; }
    }
}

