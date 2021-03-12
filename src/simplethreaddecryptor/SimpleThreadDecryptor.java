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
        int[] encrypted = {55, 65, 21, 80, 21, 0, 67, 92, 25, 19, 17, 84, 3, 65, 7, 84, 86, 5, 12, 67, 86, 5, 6, 17, 20, 0, 17, 67, 31, 6, 2};
        int expectedCheckSum = 2789;
        int encryptedLength = encrypted.length;

        //Gerar todas as chaves de 4 letras e números possíveis
        // 4 fors encadeados e baseados no código da tabela ascii
        decryptFourWordsKey(encrypted, expectedCheckSum);
    }

    public static String convertToString(int[] message) {
        String s = "";
        for (int i : message) {
            s += Character.toString((char) i);
        }

        return s;
    }

    // Start from character 118 to reduce processing
    private static void decryptFourWordsKey(int[] encrypted, int expectedCheckSum) {
        for (int i = END_CHAR_CODE - 4; i < END_CHAR_CODE; i++) {
            for (int j = START_CHAR_CODE; j < END_CHAR_CODE; j++) {
                for (int k = START_CHAR_CODE; k < END_CHAR_CODE; k++) {
                    for (int l = START_CHAR_CODE; l < END_CHAR_CODE; l++) {
                        new Thread(new DecryptorThread("" + (char) i + (char) j + (char) k + (char) l, encrypted, expectedCheckSum)).start();
                    }
                }
            }
        }
    }

}
