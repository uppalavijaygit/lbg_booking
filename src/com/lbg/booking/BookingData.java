package com.lbg.booking;

import java.time.LocalDateTime;

public class BookingData {
    private String bookedBy;
    private LocalDateTime requestedOn;
    private LocalDateTime bookedOn;
    private long meetingStartTime;
    private long meetingEndTime;
    private BookingRequestData requestObj;

    public BookingData() {
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public LocalDateTime getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(LocalDateTime requestedOn) {
        this.requestedOn = requestedOn;
    }

    public LocalDateTime getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(LocalDateTime bookedOn) {
        this.bookedOn = bookedOn;
    }

    public long getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(long meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public long getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(long meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public BookingRequestData getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(BookingRequestData requestObj) {
        this.requestObj = requestObj;
    }

    @Override
    public String toString() {
        return "BookingData{" +
                "bookedBy='" + bookedBy + '\'' +
                ", requestedOn=" + requestedOn +
                ", bookedOn=" + bookedOn +
                ", meetingStartTime=" + meetingStartTime +
                ", meetingEndTime=" + meetingEndTime +
                ", requestObj=" + requestObj +
                '}';
    }
}
