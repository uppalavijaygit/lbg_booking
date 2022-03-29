package com.lbg.booking;

import java.util.Scanner;

public class StartBookingService {
    public static void main(String args[]){
        Scanner scanner= new Scanner(System.in);
        BookingMetaData bookingMetaData = new BookingMetaData();
        InputDataListener.collectOfficeHours(scanner,bookingMetaData,true);
        InputDataListener.collectBookingDataAndBook(scanner,bookingMetaData,true);
    }
}
