/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplethreaddecryptor;

import static simplethreaddecryptor.SimpleThreadDecryptor.REGEXP_WORDS;
import static simplethreaddecryptor.SimpleThreadDecryptor.convertToString;

/**
 *
 * @author evand
 */
public class DecryptorThread implements Runnable {

    private String key;
    private int[] msg;
    private int expectedChecksum;

    public DecryptorThread(String key, int[] msg, int expectedChecksum) {
        this.key = key;
        this.msg = msg;
        this.expectedChecksum = expectedChecksum;
    }

    @Override
    public void run() {
        tryToDecryptMsg(key, msg, expectedChecksum);
    }

    private static void tryToDecryptMsg(String key, int[] msg, int checkSum) {
        String aux = convertToString(msg);
        int x = 0;
        int originalSum = 0;

        String temp = "";
        for (int i = 0; i < aux.length(); i++) {
            temp += (char) (aux.charAt(i) ^ key.charAt(x));
            x++;

            originalSum += (char) temp.charAt(i);
            // Maior que 3, pois a chave tem 4 caracteres
            if (x > 3) {
                x = 0;
            }
        }

        if (originalSum == checkSum && checkStringChars(temp)) {
            System.out.println(temp);
            System.out.println(originalSum);
            System.out.println("Chave utilizada: " + key);
            System.exit(0);
        }

    }

    private static boolean checkStringChars(String s) {
        return s.matches(REGEXP_WORDS);
    }

}
