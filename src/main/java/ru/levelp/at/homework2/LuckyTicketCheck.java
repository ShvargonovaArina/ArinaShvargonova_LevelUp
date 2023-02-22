package ru.levelp.at.homework2;

import java.util.Scanner;

public class LuckyTicketCheck {
    public static void main(String[] args) {
        System.out.print("Введите номер вашего билетика: ");
        Scanner keyboard = new Scanner(System.in);
        int ticketNumber = keyboard.nextInt();
        if (ticketIsLucky(Integer.toString(ticketNumber))) {
            System.out.print("Ваш билетик счастливый");
        } else {
            System.out.print("Ваш билетик обычный");
        }
    }

    public static boolean ticketIsLucky(String ticketNumber) {
        if (ticketNumber.length() != 6) {
            return false;
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
