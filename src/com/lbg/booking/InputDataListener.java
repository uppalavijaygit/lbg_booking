package com.lbg.booking;

import java.util.Scanner;

public class InputDataListener {
    public static void collectOfficeHours(Scanner scanner,BookingMetaData bookingMetaData,boolean printInfo){
        if(printInfo)
            System.out.println("Please enter Office Start time in 24hrs format <starttime> <closetime>, ex: 0900 2030");
        String officeHoursText = scanner.nextLine();
        String[] officeHoursArrays = officeHoursText.split(" ");

        if(officeHoursArrays.length < 2 ||
                !InputDataVerifier.verifyOfficeHours(officeHoursArrays,bookingMetaData)){
            collectOfficeHours(scanner,bookingMetaData,true);
        }
        System.out.println("Office Hours:"+officeHoursText+":bookingMetaData:"+bookingMetaData);
    }

    public static BookingRequestData collectBookingDataAndBook(Scanner scanner, BookingMetaData bookingMetaData, boolean printInfo){
        if(printInfo) {
            System.out.println("Please enter Booking Data in n SUBMISSION_DATE_YYYY-MM-DD HH:MM:SS EMPLOYEE_ID START_TIME_YYYY-MM-DD HH:MM DURATION_IN_HOURS");
            System.out.println("Ex:2022-07-18 10:17:06 EMP001 2022-07-21 09:00 2");
        }
        String bookingDataStr = scanner.nextLine();
        BookingRequestData bookingRequestData = new BookingRequestData();
        String[] bookingDataArray = bookingDataStr.split(" ");
        if(bookingDataArray.length < 6 || !InputDataVerifier.verifyBookingData(bookingDataArray, bookingRequestData)){
            collectBookingDataAndBook(scanner,bookingMetaData,true);
        }else{
            System.out.println("bookingData:"+ bookingRequestData);
            boolean response = BookingService.getInstance().bookMeetingRoom(bookingMetaData,bookingRequestData);
            collectBookingDataAndBook(scanner,bookingMetaData,true);
        }
        return bookingRequestData;
    }
}
