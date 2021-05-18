package me.tapumandal.jewellery.util;

public class MyPagenation {

    private int currentPage;
    private int totalPage;
    private int pageSize;
    private int totalElement;
    private String nextPageUrl;
    private String previousPageUrl;

    public MyPagenation() {
    }

    public MyPagenation(int currentPage, int totalPage, int pageSize, int totalElement, String nextPageUrl, String previousPageUrl) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.totalElement = totalElement;
        this.nextPageUrl = nextPageUrl;
        this.previousPageUrl = previousPageUrl;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(String previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    @Override
    public String toString() {
        return "MyPagenation{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", totalElement=" + totalElement +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                ", previousPageUrl='" + previousPageUrl + '\'' +
                '}';
    }
}
