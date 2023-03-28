package ru.levelp.at.homework2;

public class LuckyTicketCheck {
    public static boolean ticketIsLucky(String ticketNumber) {
        if (ticketNumber.length() != 6) {
            throw new IllegalArgumentException("В номере билетика должно быть 6 цифр");
        } else {
            int sumFirstPart = (int) ticketNumber.charAt(0)
                + (int) ticketNumber.charAt(1)
                + (int) ticketNumber.charAt(2);
            int sumSecondPart = (int) ticketNumber.charAt(3)
                + (int) ticketNumber.charAt(4)
                + (int) ticketNumber.charAt(5);
            return sumFirstPart == sumSecondPart;
        }
    }
}
