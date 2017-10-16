package assembler;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalScanner {

    public LexicalScanner () {
    }

    void scan (String line) {

        Matcher matcher;

        Pattern _directive_pattern = Pattern.compile("^(.*)[ \\t]*(\\.[a-z]+)[ \\t]+([0-9a-zA-Z]+)[ \\t]*(;[ \\t]+.+)?$");
        Pattern _comment_pattern = Pattern.compile("^[ \\t]*;[ \\t]+.+$");
        Pattern _label_pattern = Pattern.compile("^[ \\t]*([a-z]+:)[ \\t]*.*(;[ \\t]+.+)?$");
        Pattern _mnemonic_pattern = Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+([a-zA-Z0-9#\\]\\[]+,?[ \\t])+([a-zA-Z0-9#\\]\\[]+)[ \\t]*(;[ \\t]+.+)?$");


        matcher = _mnemonic_pattern.matcher(line);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));

            try {
//                System.out.println(OpcodeTable.valueOf(matcher.group(1)).instruction().opcode().binary());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("\n\n");
        }




    }
}
