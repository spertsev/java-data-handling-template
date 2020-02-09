package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     *
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal decimalA = BigDecimal.valueOf(a);
        BigDecimal decimalB = BigDecimal.valueOf(b);
        return decimalA.divide(decimalB, range, RoundingMode.HALF_UP);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger currentNumber = new BigInteger("2");
        int counter = 0;
        while (counter < range) {
            currentNumber = currentNumber.add(BigInteger.ONE);
            if (currentNumber.isProbablePrime(5)) {
                counter++;
            }
        }
        return currentNumber;
    }
}
