import com.github.saga_pricer.Pricer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricerImplTest {

    @DisplayName("Nominal cases a price is expected.")
    @ParameterizedTest
    @MethodSource("pricer_test_providers.PricerTestCasesProvider#nominalTestCases")
    public void nominalPricerTest(String basketMock, Double expectedOutput) {
        Double actualPriceToPay = Pricer.getPrice(basketMock);
        assertEquals(expectedOutput, actualPriceToPay);
    }


}
