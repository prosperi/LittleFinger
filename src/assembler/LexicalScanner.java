package assembler;


import exceptions.IllegalDirectiveException;
import exceptions.IllegalLabelException;
import exceptions.IllegalMnemonicException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalScanner {

    public LexicalScanner () {

    }

    void scan (String line) {

        if (checkComment(line) || checkEmptyLine(line)) return ;


        checkMnemonic(line);
        checkDirective(line);
        checkLabel(line);



    }

    void checkDirective (String line) throws IllegalDirectiveException {
        Pattern directivePattern = Pattern.compile("^(.*)[ \\t]*\\.([a-z]+)[ \\t]+([0-9a-zA-Z]+)[ \\t]*(;[ \\t]+.+)?$");
        Matcher matcher = directivePattern.matcher(line);

        if (matcher.find()) {
            try {
                DirectiveTable directive = DirectiveTable.valueOf(matcher.group(2).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalDirectiveException("Illegal directive found, LINE:\t" + line);
            }
        }
    }

    void checkMnemonic (String line) throws IllegalMnemonicException {
        Pattern mnemonicPattern = Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+([a-zA-Z0-9#\\]\\[]+,?[ \\t])+([a-zA-Z0-9#\\]\\[]+)[ \\t]*(;[ \\t]+.+)?$");
        Matcher matcher = mnemonicPattern.matcher(line);

        if (matcher.find()) {
            try {
                OpcodeTable mnemonic = OpcodeTable.valueOf(matcher.group(1));
            } catch (IllegalArgumentException e) {
                throw new IllegalMnemonicException("Illegal mnemonic found, LINE:\t" + line);
            }
        }
    }

    void checkLabel (String line) throws IllegalLabelException {
        Pattern labelPattern = Pattern.compile("^[ \\t]*([a-z]+):[ \\t]*.*(;[ \\t]+.+)?$");
        Matcher matcher = labelPattern.matcher(line);

        if (matcher.find()) {
            try {
                LabelTable label = LabelTable.valueOf(matcher.group(1).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalLabelException("Illegal label found, LINE:\t" + line);
            }
        }
    }

    boolean checkComment (String line) {
        Pattern commentPattern = Pattern.compile("^[ \\t]*;[ \\t]+.+$");
        Matcher matcher = commentPattern.matcher(line);

        if (matcher.find()) return true;

        return false;
    }

    boolean checkEmptyLine (String line) {
        Pattern emptyLinePattern = Pattern.compile("^\\s*$");
        Matcher matcher = emptyLinePattern.matcher(line);

        if (matcher.find()) return true;

        return false;
    }

}
