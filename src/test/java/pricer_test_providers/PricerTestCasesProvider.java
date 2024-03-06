package pricer_test_providers;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PricerTestCasesProvider {
   public static Stream<Arguments> nominalTestCases() {
        return Stream.of(
                Arguments.of(Named.of("5 SAGAS", "Back to the Future 1\n" +
                        "Back to the Future 2\nBack to the Future 3"), 36.),
                Arguments.of(Named.of("2 SAGAS", "Back to the Future 1" +
                        "\nBack to the Future 3"), 27.),
                Arguments.of(Named.of("1 SAGA", "Back to the Future 1"), 15.),
                Arguments.of(Named.of("4 SAGAS with duplicate", "Back to the Future 1" +
                        "\n Back to the Future 2\n Back to the Future 3\n Back to the Future 2\n"), 48.),
                Arguments.of(Named.of("3 SAGAS and 1 none SAGA", "Back to the " +
                        "Future 1\nBack to the Future 2\nBack to the Future 3\nLa chèvre"), 56.)
        );
    }
}
