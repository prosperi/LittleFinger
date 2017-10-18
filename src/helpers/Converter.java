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
        System.out.print(reversed + "\t");

        return reversed;
    }

    public static String decimalToBinary (int decimal, int size) {
        String binary = decimalToBinary(decimal);

        for (int i = binary.length() - 1; i < size; i++) {
            binary = "0" + binary;
        }

        return binary;
    }

    public static String binaryToHex (String binary) {
        String output = "";
        String hex = "0123456789ABCDEF";

        for (int i = 0; i < binary.length() - 4; i += 4) {
            int sum = (binary.charAt(i) - '0')   * 8 +
                    (binary.charAt(i + 1) - '0') * 4 +
                    (binary.charAt(i + 2) - '0') * 2 +
                    (binary.charAt(i + 3) - '0');
            output += hex.charAt(sum);
        }

        return output;
    }

}
