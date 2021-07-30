import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void main() {
    }

    @Test
    void ownBase64StringTestSchouldReturn() {
        String input = "Test";
        String res = Main.ownBase64(input);
        Assert.assertEquals(res, "ŐƔǌǐ");
    }

    @Test
    void ownBase64StringTestSchouldReturnFalse() {
        String input = "Rade";
        String res = Main.ownBase64(input);
        Assert.assertNotEquals(res, "ŐƔǌǐ");
    }
}