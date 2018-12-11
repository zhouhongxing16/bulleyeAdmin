package com.chris.bulleyeadmin.pojo;

/**
 * TODO
 *
 * @author zj
 * @date 2017/7/19.
 */
public class Page {
    private int total;
    private int rows;
    private int page;
    private int stt;
    private int limit = 10;
    private int offset;
    private int stb;
    private String sval;
    private String q;
    private String key;
    private String value;
    private String order;
    private String search;
    private String sort;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.search = q;
        this.q = q;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSval() {
        return sval;
    }

    public void setSval(String sval) {
        this.sval = sval;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        if(rows > 0){
            return offset;
        }else{
            return offset/limit;
        }
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getStb() {
        stb = offset;
        return stb;
    }

    public void setStb(int stb) {
        this.stb = stb;
    }

    public int getStt() {
        stt = rows * (page - 1);
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.limit = rows;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.offset = page-1;
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", rows=" + rows +
                ", page=" + page +
                ", stt=" + stt +
                ", limit=" + limit +
                ", offset=" + offset +
                ", stb=" + stb +
                ", sval='" + sval + '\'' +
                ", q='" + q + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", order='" + order + '\'' +
                ", search='" + search + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
