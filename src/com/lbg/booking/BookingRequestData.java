package com.lbg.booking;

import java.time.LocalDateTime;

public class BookingRequestData {
    private LocalDateTime requestDate;
    private LocalDateTime bookingDate;
    private String employeeId;
    private float noOfHours;

    public BookingRequestData() {
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public float getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(float noOfHours) {
        this.noOfHours = noOfHours;
    }

    @Override
    public String toString() {
        return "BookingData{" +
                "requestDate=" + requestDate +
                ", bookingDate=" + bookingDate +
                ", employeeId='" + employeeId + '\'' +
                ", noOfHours=" + noOfHours +
                '}';
    }
}
