package helpers;

public final class Converter {

    private Converter () {

    }

    public static String decimalToBinary (int decimal) {
        String output = "";
        while (decimal > 0) {
            output += decimal % 2;
            decimal = decimal / 2;
        }

        String reversed = new StringBuilder(output).reverse().toString();
        return reversed;
    }

    public static String decimalToBinary (int decimal, int size) {
        String binary = decimalToBinary(decimal);

        for (int i = binary.length(); i < size; i++) {
            binary = "0" + binary;
        }

        return binary;
    }

    public static String binaryToHex (String binary) {
        String output = "";
        String hex = "0123456789ABCDEF";

        for (int i = 0; i < binary.length(); i += 4) {
            int sum = (binary.charAt(i) - '0')   * 8 +
                    (binary.charAt(i + 1) - '0') * 4 +
                    (binary.charAt(i + 2) - '0') * 2 +
                    (binary.charAt(i + 3) - '0');
            output += hex.charAt(sum);
        }

        return output;
    }

    public static int hexToDecimal (String h) {
        return Integer.parseInt(h, 16);
    }

    public static String hexToBinary (String hex) {
        return decimalToBinary(hexToDecimal(hex));
    }

    public static String hexToBinary (String hex, int size) {
        return decimalToBinary(hexToDecimal(hex), size);
    }

    public static String formatHex (String hex, int size) {

        if (hex.length() > size) return hex.substring(hex.length() - size, hex.length());

        for (int i = hex.length(); i < size; i++) {
            hex = "0" + hex;
        }

        return hex;
    }

    public static String decimalToHex (int decimal) {
        String output = "";
        String hex = "0123456789ABCDEF";

        while (decimal > 0) {
            output += hex.charAt(decimal % 16);
            decimal /= 16;
        }

        if (output.length() == 0) output = "0";

        String reversed = new StringBuilder(output).reverse().toString();
        return "0x" + reversed;
    }

}
