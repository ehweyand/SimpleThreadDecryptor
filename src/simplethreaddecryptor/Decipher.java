/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplethreaddecryptor;

import static simplethreaddecryptor.SimpleThreadDecryptor.END_CHAR_CODE;
import static simplethreaddecryptor.SimpleThreadDecryptor.REGEXP_WORDS;
import static simplethreaddecryptor.SimpleThreadDecryptor.START_CHAR_CODE;

/**
 *
 * @author thom-mg
 */
public class Decipher implements Runnable{
    private int sc;
    private int[] encrypted;
    private int expectedCheckSum;
    
    public Decipher(int sc, int[] encrypted, int expectedCheckSum) {
        this.sc = sc;
        this.encrypted = encrypted;
        this.expectedCheckSum = expectedCheckSum;
    }
    
    @Override
    public void run() {
        for (int i = sc ; i < END_CHAR_CODE; i++) {
            for (int j = START_CHAR_CODE; j < END_CHAR_CODE; j++) {
                for (int k = START_CHAR_CODE; k < END_CHAR_CODE; k++) {
                    for (int l = START_CHAR_CODE; l < END_CHAR_CODE; l++) {
                        tryToDecryptMsg("" + (char) i + (char) j + (char) k + (char) l, encrypted, expectedCheckSum);
                    }
                }
            }
        }
    }
    
    private static void tryToDecryptMsg(String key, int[] msg, int checkSum) {
        int x = 0;
        int originalSum = 0;

        String temp = "";
        for (int i = 0; i < msg.length; i++) {
            temp += (char) (msg[i] ^ key.charAt(x));
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
        }

    }
    
    private static boolean checkStringChars(String s) {
        return s.matches(REGEXP_WORDS);
    }
    
}
