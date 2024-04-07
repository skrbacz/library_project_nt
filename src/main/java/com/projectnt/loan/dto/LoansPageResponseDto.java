package com.projectnt.loan.dto;

import java.util.List;

public class LoansPageResponseDto {

    private List<LoanDto> loans;

    private int currentPage;

    private long totalItems;

    private int totalPages;

    private boolean hasMore;

    public LoansPageResponseDto(List<LoanDto> loans, int currentPage, long totalItems, int totalPages, boolean hasMore) {
        this.loans = loans;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.hasMore = hasMore;
    }

    public List<LoanDto> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDto> loans) {
        this.loans = loans;
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
