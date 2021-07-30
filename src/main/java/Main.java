import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        String input = "ABC";
        System.out.println(Base64.getEncoder().encodeToString(input.getBytes()));
        String ownBase64 = ownBase64Encode(input);
        System.out.println(ownBase64);
        System.out.println(ownBase64.length());
    }

    static public String ownBase64Encode(String s) {
        String Symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        String output = "";
        String binary = "";
        String pad = "=";
        boolean addPAd = false;
        boolean addTwoPad = false;

        for (int i = 0; i < s.length(); i++) {
            String factor = String.format("%8s", Integer.toBinaryString(s.charAt(i))).replace(' ', '0');
            binary += factor;
        }

        System.out.println(binary.length());
        int bl = binary.length();
        for (int i = 0; i < bl; i += 6) {
            if (bl - i == 16) {
                binary += "00";
                addPAd = true;
            } else if (bl - i == 8) {
                binary += "0000";
                addTwoPad = true;
            }
            String factor = binary.substring(i, i + 6);
            output += Symbols.charAt(Integer.parseInt(factor, 2));
        }

        if (addPAd) {
            output += "=";
        } else if (addTwoPad) {
            output += "==";
        }

        return output;
    }
}
