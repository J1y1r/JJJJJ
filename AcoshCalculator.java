/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Jayar
 */
public class AcoshCalculator {
    public static BigDecimal acosh(BigDecimal x, int precision) {
        BigDecimal onePlusX = x.add(BigDecimal.ONE);
        BigDecimal sqrtTerm = sqrt(onePlusX, precision);
        return ln(x.add(sqrtTerm), precision);
    }

    
    private static BigDecimal sqrt(BigDecimal x, int precision) {
        BigDecimal guess = x.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
        BigDecimal tolerance = BigDecimal.ONE.scaleByPowerOfTen(-precision);
        BigDecimal lastGuess;
        do {
            lastGuess = guess;
            guess = guess.add(x.divide(guess, MathContext.DECIMAL128)).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
        } while (guess.subtract(lastGuess).abs().compareTo(tolerance) > 0);
        return guess;
    }

    
    private static BigDecimal ln(BigDecimal x, int precision) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal term = x.subtract(BigDecimal.ONE);
        BigDecimal termSquared = term.multiply(term);
        BigDecimal numerator = term;
        BigDecimal denominator = BigDecimal.ONE;
        BigDecimal tolerance = BigDecimal.ONE.scaleByPowerOfTen(-precision);
        BigDecimal nextTerm = numerator.divide(denominator, MathContext.DECIMAL128);
        int i = 1;
        while (nextTerm.abs().compareTo(tolerance) > 0) {
            if (i % 2 == 1) {
                result = result.add(nextTerm);
            } else {
                result = result.subtract(nextTerm);
            }
            numerator = numerator.multiply(termSquared).negate();
            denominator = denominator.add(BigDecimal.ONE);
            nextTerm = numerator.divide(denominator, MathContext.DECIMAL128);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        BigDecimal x = new BigDecimal("3.0"); 
        int precision = 20; 
        BigDecimal result = acosh(x, precision);
        System.out.println("acosh(" + x + ") = " + result);
    }
    
}

