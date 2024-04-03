package com.projectnt.loan.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Date;

public class PatchLoanDto {
    private JsonNullable<Date> returnDate;

    public PatchLoanDto(JsonNullable<Date> returnDate) {
        this.returnDate = returnDate;
    }

    public JsonNullable<Date> getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(JsonNullable<Date> returnDate) {
        this.returnDate = returnDate;
    }
}
