package com.epam.izh.rd.online.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String readData = "";
        try{
            readData = new String (Files.readAllBytes(Paths.get("src/main/resources/sensitive_data.txt")));
        }catch (IOException e) {
            e.printStackTrace();
        }
        Pattern accountNumberPattern = Pattern.compile("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}");
        Matcher accountNumberMatcher = accountNumberPattern.matcher(readData);
        while (accountNumberMatcher.find()) {
            String accountNumber = accountNumberMatcher.group();
            String hideAccountNumber = accountNumber.substring(0, 4) + " **** **** " + accountNumber.substring(15, 19);
            readData = readData.replace(accountNumber, hideAccountNumber);
        }
        return readData;
    }


    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String readData = "";
        try{
            readData = new String (Files.readAllBytes(Paths.get("src/main/resources/sensitive_data.txt")));
        }catch (IOException e) {
            e.printStackTrace();
        }
        readData = readData.replaceAll("\\$\\{+payment_amount+}", String.valueOf(paymentAmount).substring(0, 1));
        readData = readData.replaceAll("\\$\\{+balance+}", String.valueOf(balance).substring(0, 1));
        return readData;
    }
}

