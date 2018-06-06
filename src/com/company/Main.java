package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

import static java.time.LocalDate.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner keyboard = new Scanner(System.in);

        //Get the current time
        LocalDateTime rightNow = LocalDateTime.now();
        //Date from the user
        LocalDate userDate = null;

        //Set up formatters so that you can use them later
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter shortMonthFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter longFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter anotherFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd MM yyyy");


        //Time formatter (time only)
        DateTimeFormatter hr24 = DateTimeFormatter.ofPattern("kk:m");

        //Output today's date in formats that have been preset
        System.out.println("The current date is: " + rightNow.format(longFormat));
        System.out.println("This is the current date and time unformatted: " + rightNow);
        System.out.println("The current date in short Month Format: " + rightNow.format(shortMonthFormat));
        System.out.println("The current date in another Format: " + rightNow.format(anotherFormat));
        System.out.println("The current date in new Format: " + rightNow.format(newFormat) + "\n");

        //Output the current time in formats that have been preset
        System.out.println("This is the current system time: " + LocalTime.now());
        System.out.println("This is the current system time (24 h format): " + LocalTime.now().format(hr24) + "\n");

        //This is how you parse a string with a date time formatter
        String aDate = "22/05/2010";
        userDate = parse(aDate, dTF);
        System.out.println(userDate.format(longFormat));
        //Display the date entered
        System.out.println(userDate.format(shortMonthFormat) + "\n");


        //Display user input date in several format


        System.out.println("Now enter your date in dd/mm/yyyy");
        String thisDate = keyboard.nextLine();
        boolean validCheck = isThisDateValid(thisDate);

        while (validCheck == false) {
            System.out.println("Now enter your date in dd/mm/yyyy");
            thisDate = keyboard.nextLine();
            validCheck = isThisDateValid(thisDate);
        }
        boolean dateOrderCheck = isThisDateBeforeToday(thisDate);
        while (dateOrderCheck == false) {
            System.out.println("Now enter your date in dd/mm/yyyy");
            thisDate = keyboard.nextLine();
            dateOrderCheck = isThisDateBeforeToday(thisDate);
        }


        LocalDate userInDate = parse(thisDate, dTF);
        System.out.println("The date you entered in day, Month (3 letters) and year (4 digit) format is: " + userInDate.format(shortMonthFormat));
      //  System.out.println("The date you entered in long Month format is: " + userInDate.format(longFormat));
        System.out.println("The date you entered in Month (3 letters), day  and year (4 digit) format is: " + userInDate.format(anotherFormat));
        System.out.println("The date you entered in day, month (2 digits) and year format is: " + userInDate.format(newFormat));
    }


    public static boolean isThisDateValid(String dateToValidate) {
        if (dateToValidate.length() == 10) {
            char ch1 = dateToValidate.charAt(2);
            char ch2 = dateToValidate.charAt(5);
            if (ch1 == '/' && ch2 == '/') {
                String month = dateToValidate.substring(3,5);
                int monthInt=Integer.parseInt(month);
                //System.out.println("month in int %%%%%%%%%%%%%%%%%%%%"+ monthInt);
                if(monthInt<1 || monthInt >12) {
                    System.out.println("Please enter the date right format (dd/mm/yyyy)");
                    return false;
                }
                else
                    return true;
            }
            else {
                System.out.println("Please enter the date right format (dd/mm/yyyy)");
                return false;
            }
        } else {
            System.out.println("Please enter the date right format (dd/mm/yyyy)");
            return false;
        }
    }

    public static boolean isThisDateBeforeToday(String dateToValidate) throws ParseException {
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate rightNow = LocalDate.now();
       // LocalDate today = parse(rightNow.format(dTF));
        LocalDate userDate = parse(dateToValidate, dTF);

        if(rightNow.isAfter(userDate)) {
            //System.out.println("This is true");
            return true;
        }
        else {
            System.out.println("Please enter before today.");
            return false;
        }
    }

}
