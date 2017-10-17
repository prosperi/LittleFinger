package assembler;


import exceptions.IllegalDirectiveException;
import exceptions.IllegalLabelException;
import exceptions.IllegalLittleFingerException;
import exceptions.IllegalMnemonicException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalScanner {

    public LexicalScanner () {

    }

    void scan (String line) {

        boolean comment, emptyLine, mnemonic, directive, label;

        comment = checkComment(line);
        emptyLine = checkEmptyLine(line);

        if (comment || emptyLine) return ;


        mnemonic = checkMnemonic(line);
        directive = checkDirective(line);
        label = checkLabel(line);

        if (!(mnemonic || directive || label))
            throw new IllegalLittleFingerException("\u001B[36m Illegal little-finger found, LINE:\t \u001B[0m" + line);


    }

    boolean checkDirective (String line) throws IllegalDirectiveException {
        Pattern directivePattern = PatternTable.DIRECTIVE.pattern();
        Matcher matcher = directivePattern.matcher(line);

        if (matcher.find()) {
            try {
                DirectiveTable directive = DirectiveTable.valueOf(matcher.group(2).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalDirectiveException("Illegal directive found, LINE:\t" + line);
            }

            return true;
        }

        return false;
    }

    boolean checkMnemonic (String line) throws IllegalMnemonicException {
        Pattern mnemonicPattern = PatternTable.MNEMONIC.pattern();
        Matcher matcher = mnemonicPattern.matcher(line);

        if (matcher.find()) {
            try {
                OpcodeTable mnemonic = OpcodeTable.valueOf(matcher.group(1));
            } catch (IllegalArgumentException e) {
                throw new IllegalMnemonicException("Illegal mnemonic found, LINE:\t" + line);
            }

            return true;
        }

        return false;
    }

    boolean checkLabel (String line) throws IllegalLabelException {
        Pattern labelPattern = PatternTable.LABEL.pattern();
        Matcher matcher = labelPattern.matcher(line);

        if (matcher.find()) {
            try {
                LabelTable label = LabelTable.valueOf(matcher.group(1).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalLabelException("Illegal label found, LINE:\t" + line);
            }

            return true;
        }

        return false;
    }

    boolean checkComment (String line) {
        Pattern commentPattern = PatternTable.COMMENT.pattern();
        Matcher matcher = commentPattern.matcher(line);

        if (matcher.find()) return true;

        return false;
    }

    boolean checkEmptyLine (String line) {
        Pattern emptyLinePattern = PatternTable.EMPTY_LINE.pattern();
        Matcher matcher = emptyLinePattern.matcher(line);

        if (matcher.find()) return true;

        return false;
    }

}
