package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     * <p>
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base   - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove, "");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     * <p>
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        boolean isQuestion = false;
        isQuestion = text.equals("") ? false : text.substring(text.length() - 1).equals("?");
        return isQuestion;
    }

    /**
     * Реализовать функционал соединения переданных строк.
     * <p>
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder resultStringBuilder = new StringBuilder("");
        for (String currentElement : elements) {
            resultStringBuilder.append(currentElement);
        }
        return resultStringBuilder.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     * <p>
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        if (text.equals("")) {

        } else {
            text = text.substring(0, 1).toLowerCase() + text.substring(1);
            String editedSubstring = "";
            int i;
            for (i = 2; i <= text.length(); i++) {
                editedSubstring = (i % 2 == 0) ? text.substring(i - 1, i).toUpperCase() : text.substring(i - 1, i).toLowerCase();
                text = text.substring(0, i - 1) + editedSubstring + text.substring(i);
            }
        }
        return text;
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     * <p>
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     * <p>
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        boolean result = false;
        if (string.equals("")) {

        } else {
            string = string.toLowerCase().replace(" ", "");
            StringBuilder stringBuilder = new StringBuilder(string);
            result = string.equals(stringBuilder.reverse().toString());
        }
        return result;
    }
}
