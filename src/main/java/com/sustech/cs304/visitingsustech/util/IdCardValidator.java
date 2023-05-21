package com.sustech.cs304.visitingsustech.util;

/**
 * Details of IdCard validation handling.
 *
 * @author sui_h
 */
public class IdCardValidator {
    private static final int[] FACTORS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final String[] CHECK_CODES = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    public static boolean isValid(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return false;
        }
        char[] chars = idCard.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            char c = chars[i];
            if (c < '0' || c > '9') {
                return false;
            }
            int num = c - '0';
            sum += num * FACTORS[i];
        }
        int remainder = sum % 11;
        String checkCode = CHECK_CODES[remainder];
        return checkCode.equals(String.valueOf(chars[17]));
    }
}
