package jmul.math.numbers.operations;


import jmul.math.numbers.digits.Digit;


public class CalculationResult {

    private final Digit result;
    private final Digit carry;

    CalculationResult(Digit result, Digit carry) {

        this.result = result;
        this.carry = carry;
    }

    public Digit result() {

        return result;
    }

    public Digit carry() {

        return carry;
    }

}
