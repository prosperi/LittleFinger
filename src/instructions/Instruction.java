package instructions;

/**
 * Instruction abstract class, each instruction type extends Instruction
 */
public abstract class Instruction {
    public abstract String binary ();
    public abstract String hex();

    public abstract String opcode();
    public abstract String rt();
    public abstract String rm();
    public abstract String rn();
    public abstract String rd();
    public abstract String shamt();
    public abstract String immediate();
    public abstract String address();

}
