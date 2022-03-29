package com.lbg.booking;

public class BookingMetaData {
    private String officeStartTime;
    private String officeCloseTime;

    public BookingMetaData() {
    }

    public String getOfficeStartTime() {
        return officeStartTime;
    }

    public void setOfficeStartTime(String officeStartTime) {
        this.officeStartTime = officeStartTime;
    }

    public String getOfficeCloseTime() {
        return officeCloseTime;
    }

    public void setOfficeCloseTime(String officeCloseTime) {
        this.officeCloseTime = officeCloseTime;
    }

    @Override
    public String toString() {
        return "BookingMetaData{" +
                "officeStartTime='" + officeStartTime + '\'' +
                ", officeCloseTime='" + officeCloseTime + '\'' +
                '}';
    }
}
