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

}
