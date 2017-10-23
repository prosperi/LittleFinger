package assembler;


import exceptions.IllegalDirectiveException;
import exceptions.IllegalLabelException;
import exceptions.IllegalLittleFingerException;
import exceptions.IllegalMnemonicException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LexicalScanner scans each line that is supposed to be parsed and converted
 * to machine language by assembler and throws exceptions if unsupported code was
 * provided
 */
public class LexicalScanner {

    public LexicalScanner () {

    }

    /**
     *  Scan the provided line for illegal syntax suing regular expressions
     * @param line line that needs to be scanned and then parsed
     */
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

    /**
     *  Check if line contains Directive
     * @param line line that needs to be checked if it contains directive
     * @return  true if line contains directive, false if it does not
     * @throws IllegalDirectiveException
     */
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

    /**
     *  Check if line contains Mnemonic
     * @param line line that needs to be checked if it contains mnemonic
     * @return true if it contains directive, false otherwise
     * @throws IllegalMnemonicException
     */
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

    /**
     *  Check if line contains Label
     * @param line line that needs to be checked if it contains label
     * @return true if it does, false otherwise
     * @throws IllegalLabelException
     */
    boolean checkLabel (String line) throws IllegalLabelException {
        Pattern labelPattern = PatternTable.LABEL.pattern();
        Matcher matcher = labelPattern.matcher(line);

        if (matcher.find()) {

            return true;
        }

        return false;
    }

    /**
     *  Check if line contains comment
     * @param line line that needs to be checked if it contains a comment
     * @return true if it does, false otherwise
     */
    boolean checkComment (String line) {
        Pattern commentPattern = PatternTable.COMMENT.pattern();
        Matcher matcher = commentPattern.matcher(line);

        if (matcher.find()) return true;

        return false;
    }

    /**
     *  Check if provided  line is an empty line
     * @param line line that needs to be checked if it is an empty line
     * @return true if it is empty line, false otherwise
     */
    boolean checkEmptyLine (String line) {
        Pattern emptyLinePattern = PatternTable.EMPTY_LINE.pattern();
        Matcher matcher = emptyLinePattern.matcher(line);

        if (matcher.find()) return true;

        return false;
    }

}
