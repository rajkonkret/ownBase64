import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class Main {
    public static void main(String[] args) {

        String input = "ABC";
        log.info(Base64.getEncoder().encodeToString(input.getBytes()));
        String ownBase64 = ownBase64Encode(input);
        log.info(ownBase64);
        log.info(String.valueOf(ownBase64.length()));
        log.info(new String(decode("QUJD")));
    }

    /**
     * Translates the specified String into Base64 string.
     *
     * @param s the byte array (not null)
     * @return the translated Base64 string (not null)
     */
    public static String ownBase64Encode(String s) {
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

    /**
     * Translates the specified Base64 string into a byte array.
     *
     * @param s the Base64 string (not null)
     * @return the byte array (not null)
     */
    public static byte[] decode(String s){
        final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        int[]  toInt   = new int[128];

            for(int i=0; i< ALPHABET.length; i++){
                toInt[ALPHABET[i]]= i;
            }



        int delta = s.endsWith( "==" ) ? 2 : s.endsWith( "=" ) ? 1 : 0;
        byte[] buffer = new byte[s.length()*3/4 - delta];
        int mask = 0xFF;
        int index = 0;
        for(int i=0; i< s.length(); i+=4){
            int c0 = toInt[s.charAt( i )];
            int c1 = toInt[s.charAt( i + 1)];
            buffer[index++]= (byte)(((c0 << 2) | (c1 >> 4)) & mask);
            if(index >= buffer.length){
                return buffer;
            }
            int c2 = toInt[s.charAt( i + 2)];
            buffer[index++]= (byte)(((c1 << 4) | (c2 >> 2)) & mask);
            if(index >= buffer.length){
                return buffer;
            }
            int c3 = toInt[s.charAt( i + 3 )];
            buffer[index++]= (byte)(((c2 << 6) | c3) & mask);
        }
        return buffer;
    }
}
