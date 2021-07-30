import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.HashMap;

class MainTest {
    @Test
    void main() {
    }

    @Test
    void ownBase64StringTestSchouldReturn() {
        String[] input = {"ABC\n", "ab@yz\n", "Radek", "GRa", "klasa23", "Tak@","kartaGina@#$"};
        HashMap<String, String> expected = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            String encoded = Base64.getEncoder().encodeToString(input[i].getBytes());
            expected.put(input[i], encoded);
        }
        System.out.println(expected);
        expected.forEach(
                (k, v) -> Assert.assertEquals(v, Main.ownBase64Encode(k))
        );
    }

    @Test
    void ownBase64StringTestSchouldReturnFalse() {
        String input = "Rade";
        String res = Main.ownBase64Encode(input);
        Assert.assertNotNull(res);
    }
}