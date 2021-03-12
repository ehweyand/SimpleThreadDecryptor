/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplethreaddecryptor;

/**
 *
 * @author evand
 */
public class SimpleThreadDecryptor {

    // Constantes
    static final int START_CHAR_CODE = 42;
    static final int END_CHAR_CODE = 122;
    static final String REGEXP_WORDS = "[a-zA-Z ]+";
    static final String REGEXP_WORDS_NUMBERS = "[a-zA-Z 0-9]+";

    //Mensagem criptografada: {55,65,21,80,21,0,67,92,25,19,17,84,3,65,7,84,86,5,12,67,86,5,6,17,20,0,17,67,31,6,2}
    //Checksum da mensagem original: 2789
    //Dica: criptografado com senha de 4 caracteres formados por letras e números, método XOR posicional.
    public static void main(String[] args) {
        int[] encrypted = {21, 1, 65, 0, 10, 7, 8, 26, 19, 18, 77, 8, 6, 18, 0, 19, 12, 77, 11, 12, 65, 4, 0, 4, 30, 10, 7, 21, 17, 1, 65, 14, 14, 27, 14, 7};
        int expectedCheckSum = 141204219;
        int encryptedLength = encrypted.length;

        //Gerar todas as chaves de 4 letras e números possíveis
        // 4 fors encadeados e baseados no código da tabela ascii
        decryptSevenWordsKey(encrypted, expectedCheckSum);
    }

    public static String convertToString(int[] message) {
        String s = "";
        for (int i : message) {
            s += Character.toString((char) i);
        }

        return s;
    }

    // Start from character 118 to reduce processing
    private static void decryptSevenWordsKey(int[] encrypted, int expectedCheckSum) {
        for (int o = START_CHAR_CODE; o < END_CHAR_CODE; o++) {
            for (int n = START_CHAR_CODE; n < END_CHAR_CODE; n++) {
                for (int i = START_CHAR_CODE; i < END_CHAR_CODE; i++) {
                    for (int m = START_CHAR_CODE; m < END_CHAR_CODE; m++) {
                        for (int j = START_CHAR_CODE; j < END_CHAR_CODE; j++) {
                            for (int k = START_CHAR_CODE; k < END_CHAR_CODE; k++) {
                                for (int l = START_CHAR_CODE; l < END_CHAR_CODE; l++) {
                                    new Thread(new DecryptorThread("" + (char) o + (char) n + (char) i + (char) m + (char) j + (char) k + (char) l, encrypted, expectedCheckSum)).start();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
