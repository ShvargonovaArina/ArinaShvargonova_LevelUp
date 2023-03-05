package ru.levelp.at.homework2;

import static ru.levelp.at.homework2.LuckyTicketCheck.ticketIsLucky;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LuckyTicketIT {

    @Test
    @Tag (TagNames.POSITIVE_TAG_NAME)
    void ticketSumFirst3DigitsEqualSumSecond3Digits() {
        String ticketNumber = "123123";
        Assertions.assertTrue(ticketIsLucky(ticketNumber));
    }

    @Test
    @Tag (TagNames.NEGATIVE_TAG_NAME)
    void ticketSumFirst3DigitsNotEqualSumSecond3Digits() {
        String ticketNumber = "123456";
        Assertions.assertFalse(ticketIsLucky(ticketNumber));
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework2.LuckyTicketDataProvider#lengthLessThanSixDataProvider")
    @Tag (TagNames.NEGATIVE_TAG_NAME)
    void ticketLengthLessThan6(String ticketNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticketIsLucky(ticketNumber));
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework2.LuckyTicketDataProvider#lengthMoreThanSixDataProvider")
    @Tag (TagNames.NEGATIVE_TAG_NAME)
    void ticketLengthMoreThan6(String ticketNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticketIsLucky(ticketNumber));
    }
}
