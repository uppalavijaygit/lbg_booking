package com.lbg.booking;

import java.util.*;

import static com.lbg.booking.InputDataVerifier.getCurrentLocalDateTime;

public class BookingService {
    private static final Map<String, List<BookingData>> bookings = new HashMap<>();
    private static final BookingService bookingService = new BookingService();

    private BookingService(){

    }

    public static BookingService getInstance(){
        return bookingService;
    }

    public boolean bookMeetingRoom(BookingMetaData bookingMetaData,BookingRequestData bookingRequestData){
        BookingData bookingData = new BookingData();
        boolean dataValidity = InputDataVerifier.verifyBookingDate(bookingRequestData,bookingMetaData,bookingData);
        if(dataValidity){
            return checkAvailabilityAndBook(bookingRequestData,bookingData);
        }
        return false;
    }

    private boolean checkAvailabilityAndBook(BookingRequestData bookingRequestData, BookingData bookingData) {
        int day = bookingRequestData.getBookingDate().getDayOfMonth();
        int month = bookingRequestData.getBookingDate().getMonthValue();
        int year = bookingRequestData.getBookingDate().getYear();
        String key = year+"-"+month+"-"+day;
        List<BookingData> dayBookings = bookings.get(key);
        bookingData.setBookedBy(bookingRequestData.getEmployeeId());
        bookingData.setBookedOn(getCurrentLocalDateTime());
        bookingData.setRequestedOn(bookingRequestData.getRequestDate());
        bookingData.setRequestObj(bookingRequestData);
        if(dayBookings == null || dayBookings.size() == 0 ){
            System.out.println("Found free slot");
            dayBookings = new ArrayList<>();
            dayBookings.add(bookingData);
            bookings.put(key,dayBookings);
            return true;
        }else{
            for(BookingData previousBooking: dayBookings){

                if(
                        (!InputDataVerifier.compareRange(previousBooking.getMeetingStartTime(),previousBooking.getMeetingEndTime(),bookingData.getMeetingStartTime())) &&
                        (!InputDataVerifier.compareRange(previousBooking.getMeetingStartTime(),previousBooking.getMeetingEndTime(),bookingData.getMeetingEndTime()))
                ){
                    System.out.println("Found free slot");
                    dayBookings.add(bookingData);
                    return true;
                }
            }
        }
        System.out.println("Not able to find free slots please try different time range");
        return false;
    }
}
