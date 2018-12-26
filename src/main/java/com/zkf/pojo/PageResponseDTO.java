package com.zkf.pojo;

import java.util.List;

/**
 * Created by zkf on 2018/8/11.
 */
public class PageResponseDTO<T> {

    private List<T> content;
    private int number;
    private int numberOfElements;
    private int size;
    private int numbtotalElementser;
    private int totalPages;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumbtotalElementser() {
        return numbtotalElementser;
    }

    public void setNumbtotalElementser(int numbtotalElementser) {
        this.numbtotalElementser = numbtotalElementser;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
