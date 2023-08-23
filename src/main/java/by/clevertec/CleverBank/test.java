package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class test {

    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            System.out.println("Error loading properties file");
        }
        String url = props.getProperty("jdbc.url");
        System.out.println(url);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        String formattedDateTime = ldt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println(formattedDateTime);

        String formattedDateTime1 = ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(formattedDateTime1);

        LocalDateTime ldt1 = LocalDateTime.now();
        String numTransaction = "12345";
        String typeTransaction = "Перевод";
        String bank1 = "Clever-Bank";
        String bank2 = "Clever-Bank";
        String account1 = "1234567390";
        String account2 = "0987654321";
        Double sum = 1000.00;
        String currency = "BYN";

        String s1 = "----------------------------------------";
        String s2 = "|           Банковский чек             |";
        int ss3 = 31 - numTransaction.length();
        String s3 = "| Чек: " + " ".repeat(ss3) + numTransaction + " |";
        String s4 = "| " + formattedDateTime + "                  " + formattedDateTime1 + " |";
        int ss5 = 27 - typeTransaction.length();
        String s5 = "| Тип транзакции: " + " ".repeat(ss5) + typeTransaction + " |";
        int ss6 = 18 - bank1.length();
        String s6 = "| Банк отправителя: " + " ".repeat(ss6) + bank1 + " |";
        int ss7 = 19 - bank2.length();
        String s7 = "| Банк получателя: " + " ".repeat(ss7) + bank2 + " |";
        String s8 = "| Счет отправителя:         " + account1 + " |";
        String s9 = "| Счет получателя:          " + account2 + " |";
        int ss10 = 28 - sum.toString().length() - currency.length();
        String s10 = "| Сумма: " + " ".repeat(ss10) + sum + " " + currency + " |";
        String s11 = "|                                      |";
        String s12 = "----------------------------------------";

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
        System.out.println(s7);
        System.out.println(s8);
        System.out.println(s9);
        System.out.println(s10);
        System.out.println(s11);
        System.out.println(s12);


        String name = "Сотников";
        String surname = "Кирилл";
        String patronymic = "Артемович";
        String account3 = "0987654321";
        String currency1 = "BYN";
        LocalDateTime localDateTime1 = LocalDateTime.now();
        String formattedDateTime11 = localDateTime1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDateTime localDateTime2 = LocalDateTime.now();
        String formattedDateTime22 = localDateTime2.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDateTime localDateTime3 = LocalDateTime.now();
        String formattedDateTime33 = localDateTime3.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String formattedDateTime333 = localDateTime3.format(DateTimeFormatter.ofPattern("HH.mm"));
        Double sum1 = 10000.00;
        String surname3 = "ванов";
        Double sum33 = 2512.72;
        String bankName = "Clever-Bank";

        String S1 = "                         Выписка";
        String Se1 = "                            " + bankName;
        String S2 = "Клиент                      | " + name + " " + surname + " " + patronymic;
        String S3 = "Счет                        | " + account3;
        String S4 = "Валюта                      | " + currency1;
        String S5 = "Дата открытия               | " + formattedDateTime11;
        String S6 = "Период                      | " + formattedDateTime11 + " - " + formattedDateTime22;
        String S7 = "Дата и время формирования   | " + formattedDateTime33 + ", " + formattedDateTime333;
        String S8 = "Остаток                     | " + sum1 + " " + currency;
        String S9 = "   Дата    |        Примечание                       | Сумма";
        String S10 = "-----------------------------------------------------------------";

        System.out.println();
        System.out.println();

        System.out.println(S1);
        System.out.println(Se1);
        System.out.println(S2);
        System.out.println(S3);
        System.out.println(S4);
        System.out.println(S5);
        System.out.println(S6);
        System.out.println(S7);
        System.out.println(S8);
        System.out.println(S9);
        System.out.println(S10);

    }
}
