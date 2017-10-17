package assembler;

import java.util.regex.Pattern;

public enum PatternTable {
    DIRECTIVE   (Pattern.compile("^(.*)[ \\t]*\\.([a-z]+)[ \\t]+([0-9a-zA-Z]+)[ \\t]*(;[ \\t]+.+)?$"))                                      ,
    MNEMONIC    (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+(([a-zA-Z0-9#\\]\\[]+,?[ \\t])+([a-zA-Z0-9#\\]\\[]+)[ \\t]*)?(;[ \\t]+.+)?$"))     ,
    LABEL       (Pattern.compile("^[ \\t]*([a-z]+):[ \\t]*.*(;[ \\t]+.+)?$"))                                                               ,
    COMMENT     (Pattern.compile("^[ \\t]*;[ \\t]+.+$"))                                                                                    ,
    EMPTY_LINE  (Pattern.compile("^\\s*$"))                                                                                                 ,

    R_TYPE      (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+(x([0-9]+),?[ \\t]+)(x([0-9]+),?[ \\t]+)(x([0-9]+),?[ \\t]+)[ \\t]*(;[ \\t]+.+)?$"))                                       ;



    private final Pattern _pattern;

    PatternTable (Pattern p) {
        _pattern = p;
    }

    public Pattern pattern () {
        return this._pattern;
    }

}
