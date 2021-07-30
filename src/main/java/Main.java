import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String Symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        String input = "Tes";
        String ownBase64 = ownBase64(input);
        System.out.println(ownBase64);
        System.out.println(ownBase64.length());

        for (int i = 0; i < ownBase64.length() - 6; i += 6) {
            System.out.println(ownBase64.substring(i, i + 6));
        }
    }

    static public String ownBase64(String s) {

        String binary = "";
        for (int i = 0; i < s.length(); i++) {
            binary += String.format("%8s", Integer.toBinaryString(s.charAt(i))).replace(' ', '0');

        }
//        String raw = Arrays.stream(binary.split(" "))
//                .map(s1 -> Integer.parseInt(s1, 2))
//                .map(Character::toString)
//                .collect(Collectors.joining()); // cut the space
//        return raw;
        return binary;
    }
}
