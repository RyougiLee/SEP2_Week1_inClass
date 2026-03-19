import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class ShoppingCartTest {

    private double simulateRun(String data) {
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ShoppingCart cart = new ShoppingCart();
            return cart.run();
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void testEnglishPath() {
        // 1: English, 1: num items, 10.0: price, 2: quantity
        String input = "1\n1\n10.0\n2\n";
        assertEquals(20.0, simulateRun(input), 0.001);
    }

    @Test
    void testFinnishPath() {
        // 2: Finnish, 1: num items, 5.5: price, 3: quantity
        String input = "2\n1\n5.5\n3\n";
        assertEquals(16.5, simulateRun(input), 0.001);
    }

    @Test
    void testSwedishPath() {
        // 3: Swedish, 1: num items, 100.0: price, 1: quantity
        String input = "3\n1\n100.0\n1\n";
        assertEquals(100.0, simulateRun(input), 0.001);
    }

    @Test
    void testDefaultLanguagePath() {
        // 4: Invalid (Default to English), 1: num items, 10.0: price, 1: quantity
        String input = "4\n1\n10.0\n1\n";
        assertEquals(10.0, simulateRun(input), 0.001);
    }

    @Test
    void testMultipleItems() {
        // 1: English, 2: num items, (10.0 * 2) + (5.0 * 3) = 35.0
        String input = "1\n2\n10.0\n2\n5.0\n3\n";
        assertEquals(35.0, simulateRun(input), 0.001);
    }
}