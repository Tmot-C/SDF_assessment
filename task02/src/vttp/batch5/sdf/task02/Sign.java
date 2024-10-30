package vttp.batch5.sdf.task02;

public enum Sign {
    
    /*enum class, technically not necessary for this implementation, but used as good practice
     since tic tac toe only involves 3 values, and the implementation can theoretically be expanded on in the future.*/
    X('X'), O('O'), BLANK('.');

    private final char sign;

    Sign(char initSign) {
        this.sign = initSign;
    }

    public boolean isSigned() {
        return this != BLANK;
    }

    public char getSign() {
        return this.sign;
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }

}