package ru.levelp.at.homework2;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class LuckyTicketDataProvider {
    static Stream<Arguments> lengthLessThanSixDataProvider() {
        return Stream.of(
            Arguments.of("12345"),
            Arguments.of("1234"),
            Arguments.of("123"),
            Arguments.of("12"),
            Arguments.of("1")
        );
    }

    static Stream<Arguments> lengthMoreThanSixDataProvider() {
        return Stream.of(
            Arguments.of("1234567"),
            Arguments.of("12345678")
        );
    }
}
