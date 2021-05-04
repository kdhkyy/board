package com.example.util;

//페이징 버튼처리 클래스
public class PageMarker {

    private Criteria cri;
    private long totalCount;
    private long startPage;
    private long endPage;
    private boolean prev;
    private boolean next;
    private long displayPageNum = 5;

    public Criteria getCri() {
        return cri;
    }
    public void setCri(Criteria cri) {
        this.cri = cri;
    }
    public long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        endPage = (long) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;

        long tempEndPage = (long) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;

    }

    public long getStartPage() {
        return startPage;
    }
    public void setStartPage(long startPage) {
        this.startPage = startPage;
    }
    public long getEndPage() {
        return endPage;
    }
    public void setEndPage(long endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public long getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(long displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

}
