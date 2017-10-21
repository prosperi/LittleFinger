package helpers;

public final class Converter {

    private Converter () {

    }

    public static String decimalToBinary (int decimal) {
        System.out.println("Converted decimal: " + decimal);
        String output = "";
        while (decimal > 0) {
            output += decimal % 2;
            decimal = decimal / 2;
        }

        String reversed = new StringBuilder(output).reverse().toString();
        System.out.println("\t to: \t" + reversed);
        return reversed;
    }

    public static String decimalToBinary (int decimal, int size) {
        String binary = decimalToBinary(decimal);

        for (int i = binary.length(); i < size; i++) {
            binary = "0" + binary;
        }

        System.out.println("Converted decimal: " + decimal + " size: " + size + " to: \t" + binary);

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

    public static int hexToDecimal (int hex) {
        int decimal = 0;
        int i = 0;

        while (hex > 0) {
            decimal += hex % 10 * Math.pow(16, i);
            hex /= 10;
            i++;
        }

        return decimal;
    }

    public static int hexToDecimal (String hex) {
        return hexToDecimal(Integer.parseInt(hex));
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

}
