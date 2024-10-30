package vttp.batch5.sdf.task02;

public enum Sign {

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