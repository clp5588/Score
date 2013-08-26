package com.sanzhong.score.common;

/**
 * User: liuchuandong
 * Date: 13-8-25
 * Time: 下午10:11
 * Func:
 */
public class Page {

    private int current=1;
    private int pages=0;
    private int perPage=10;
    private int total=0;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        if(total%perPage==0){
            this.pages=total/perPage;
        }else {
            this.pages=total/perPage+1;
        }
        return pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
    public void setPages(){

    }
}
