package com.lbg.booking;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.regex.Pattern;

public class InputDataVerifier {
    public static boolean verifyOfficeHours(String[] officeHoursArrays, BookingMetaData bookingMetaData){
        if(!verifyTimeFormat(officeHoursArrays[0])){
            return false;
        }
        if(!verifyTimeFormat(officeHoursArrays[1])){
            return false;
        }
        if(Integer.parseInt(officeHoursArrays[0]) >= Integer.parseInt(officeHoursArrays[1])){
            return false;
        }
        bookingMetaData.setOfficeStartTime(officeHoursArrays[0]);
        bookingMetaData.setOfficeCloseTime(officeHoursArrays[1]);
        return true;
    }
    public static boolean verifyTimeFormat(String input){
        try {
            if (Pattern.matches("([01]?[0-9]|2[0-3])([0-5][0-9])", input)) {
                return true;
            }
        }catch (Exception e){
            System.out.println("Error while parsing data:"+e.getLocalizedMessage());
        }
        return false;
    }
    public static boolean verifyBookingData(String[] bookingArray, BookingRequestData bookingRequestData){
        try {
            LocalDateTime requestDate = parseToDateTime(bookingArray[0] + "T" + bookingArray[1]);
            String employeeId = bookingArray[2];
            LocalDateTime bookingDate = parseToDateTime(bookingArray[3] + "T" + bookingArray[4]);
            float noOfHours = Float.parseFloat(bookingArray[5]);
            bookingRequestData.setBookingDate(bookingDate);
            bookingRequestData.setRequestDate(requestDate);
            bookingRequestData.setEmployeeId(employeeId);
            bookingRequestData.setNoOfHours(noOfHours);
            return true;
        }catch (Exception e){
            System.out.println("Error while parsing booking data:"+e.getLocalizedMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static LocalDateTime parseToDateTime(String data){
        LocalDateTime dateTime = LocalDateTime.parse(data);
        return dateTime;
    }

    public static LocalDateTime getCurrentLocalDateTime(){
        Date currentDate = new Date();
        Instant instant = Instant.ofEpochMilli(currentDate.getTime());
        LocalDateTime currentLocalDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return currentLocalDateTime;
    }
    public static boolean verifyBookingDate(BookingRequestData bookingRequestData,BookingMetaData bookingMetaData,BookingData bookingData) {
        if(bookingRequestData.getBookingDate().isAfter(getCurrentLocalDateTime()) &&
                verifyBookingTimeAndOfficeHours(bookingRequestData,bookingMetaData,bookingData)){
            return true;
        }else{
            System.out.println("Booking Date and Time Should be future date and also with in office hours");
            return false;
        }
    }

    public static boolean verifyBookingTimeAndOfficeHours(BookingRequestData bookingRequestData,BookingMetaData bookingMetaData,BookingData bookingData) {
        int meetingStartHours = bookingRequestData.getBookingDate().getHour();
        int meetingStartMinutes = bookingRequestData.getBookingDate().getMinute();
        float duration = bookingRequestData.getNoOfHours();
        int durationInHours = (int) ((duration * 60) /60);
        int durationInMinutes = (int) ((duration * 60) % 60);
        int meetingEndHours = bookingRequestData.getBookingDate().getHour() + durationInHours;
        int meetingEndMinutes = bookingRequestData.getBookingDate().getMinute() + durationInMinutes;
        durationInHours = durationInHours + ((int) ((meetingEndMinutes * 60) /60));
        meetingEndMinutes = (int) ((meetingEndMinutes * 60) % 60);

        int officeStartHour = Integer.parseInt(bookingMetaData.getOfficeStartTime().substring(0,2));
        int officeStartMins = Integer.parseInt(bookingMetaData.getOfficeStartTime().substring(2,4));

        int officeEndHour = Integer.parseInt(bookingMetaData.getOfficeCloseTime().substring(0,2));
        int officeEndMins = Integer.parseInt(bookingMetaData.getOfficeCloseTime().substring(2,4));

        if(
                (compareRange(officeStartHour,officeEndHour,meetingStartHours)) &&
                (compareMinsRange(officeStartHour,officeStartMins,officeEndHour, officeEndMins, meetingStartHours,meetingStartMinutes)) &&
                (compareRange(officeStartHour,officeEndHour,meetingEndHours)) &&
                (compareMinsRange(officeStartHour,officeStartMins,officeEndHour, officeEndMins, meetingEndHours,meetingEndMinutes))
        ){
            bookingData.setMeetingStartTime(bookingRequestData.getBookingDate().toInstant(ZoneOffset.UTC).toEpochMilli());
            long durationInMs = (long) (bookingRequestData.getNoOfHours() * 60 * 60 * 1000);
            bookingData.setMeetingEndTime(bookingData.getMeetingStartTime() + durationInMs);
            return true;
        }else{
            System.out.println("Not in Office hours range.");
            return false;
        }
    }

    public static boolean compareRange(int sourceStart,int sourceEnd,int input){
        if(input >= sourceStart && input <= sourceEnd){
            return true;
        }
        return false;
    }

    public static boolean compareRange(long sourceStart,long sourceEnd,long input){
        if(input >= sourceStart && input <= sourceEnd){
            return true;
        }
        return false;
    }

    public static boolean compareMinsRange(int sourceStartHrs,int sourceStart,int sourceEndHrs,int sourceEnd,int inputHrs,int input){
        input = input + (inputHrs*60);
        sourceStart = sourceStart + (sourceStartHrs*60);
        sourceEnd = sourceEnd + (sourceEndHrs*60);
        return compareRange(sourceStart,sourceEnd,input);
    }
}
