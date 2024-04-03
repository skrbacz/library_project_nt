package com.projectnt.book.dto;

import java.util.List;

public class GetBooksPageResponseDto {

    private List<GetBookDto> books;
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private boolean hasMore;

    public GetBooksPageResponseDto(List<GetBookDto> books, int currentPage, long totalItems, int totalPages, boolean hasMore) {
        this.books = books;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.hasMore = hasMore;
    }

    public List<GetBookDto> getBooks() {
        return books;
    }

    public void setBooks(List<GetBookDto> books) {
        this.books = books;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
