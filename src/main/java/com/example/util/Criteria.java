package com.example.util;

public class Criteria {

    //현재 페이지 번호
    private long page;

    private TypeCheck typeCheck;

    //한 페이지당 보여줄 게시글 수
    private long perPageNum;

    //특정 페이지의 게시글 시작번호
    public long getPageStart() {
        return (this.page-1)*perPageNum;
    }

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public long getPage() {
        return page;
    }
    public void setPage(long page) {

        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }

    }
    public long getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(long pageCount) {
        long cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }
}