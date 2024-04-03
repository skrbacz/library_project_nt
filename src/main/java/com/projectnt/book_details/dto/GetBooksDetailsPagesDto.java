package com.projectnt.book_details.dto;

import java.util.List;

public class GetBooksDetailsPagesDto {

    private List<GetBookDetailsDto> booksDetails;
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private boolean hasMore;

    public GetBooksDetailsPagesDto(List<GetBookDetailsDto> booksDetails, int currentPage, long totalItems, int totalPages, boolean hasMore) {
        this.booksDetails = booksDetails;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.hasMore = hasMore;
    }

    public List<GetBookDetailsDto> getBooksDetails() {
        return booksDetails;
    }

    public void setBooksDetails(List<GetBookDetailsDto> booksDetails) {
        this.booksDetails = booksDetails;
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
