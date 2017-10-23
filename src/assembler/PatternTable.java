package assembler;

import java.util.regex.Pattern;

/**
 * PatternTable stores all the regular expression used to scan and parse provided file
 */
public enum PatternTable {
    DIRECTIVE   (Pattern.compile("^(.*)[ \\t]*\\.([a-z]+)[ \\t]+([0-9a-zA-Z]+)[ \\t]*(;[ \\t]+.+)?$"))                                      ,
    MNEMONIC    (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]*(([a-zA-Z0-9#\\]\\[]+,?[ \\t]*)+([a-zA-Z0-9#\\]\\[]+)[ \\t]*)?(;[ \\t]+.+)?$"))     ,
    LABEL       (Pattern.compile("^[ \\t]*([a-z]+):[ \\t]*.*(;[ \\t]+.+)?$"))                                                               ,
    COMMENT     (Pattern.compile("^[ \\t]*;[ \\t]+.+$"))                                                                                    ,
    EMPTY_LINE  (Pattern.compile("^\\s*$"))                                                                                                 ,

    R_TYPE          (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+([xX]([0-9]+),?[ \\t]+)([xX]([0-9]+),?[ \\t]+)([xX]([0-9]+),?[ \\t]*)[ \\t]*(;[ \\t]+.+)?$"))                      ,
    R_TYPE_SHIFT    (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+([xX]([0-9]+),?[ \\t]+)([xX]([0-9]+),?[ \\t]+)(#?([0-9]+),?[ \\t]*)[ \\t]*(;[ \\t]+.+)?$"))                        ,
    I_TYPE          (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+([xX]([0-9]+),?[ \\t]+)([xX]([0-9]+),?[ \\t]+)(#?x?([0-9]+),?[ \\t]*)[ \\t]*(;[ \\t]+.+)?$"))                      ,
    D_TYPE          (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+([xX]([0-9]+),?[ \\t]+)([ \\t]*\\[[ \\t]*[xX]([0-9]+),?[ \\t]+)(#?x?([0-9]+),?[ \\t]*\\])[ \\t]*(;[ \\t]+.+)?$"))  ,
    B_TYPE          (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+(#?([0-9]+)[ \\t]*)(;[ \\t]+.+)?$"))                                               ,
    CB_TYPE         (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]+([xX]([0-9]+),?[ \\t]+)(#?([0-9]+),?[ \\t]*)[ \\t]*(;[ \\t]+.+)?$"))                                               ,
    Z_TYPE          (Pattern.compile("^[ \\t]*([A-Z]+)[ \\t]*(;[ \\t]+.+)?$"))                                                                                                  ;


    private final Pattern _pattern;

    /**
     *
     * @param p pattern
     */
    PatternTable (Pattern p) {
        _pattern = p;
    }

    public Pattern pattern () {
        return this._pattern;
    }

}
