package by.clevertec.CleverBank;

import by.clevertec.CleverBank.model.Bank;
import by.clevertec.CleverBank.model.User;
import by.clevertec.CleverBank.services.BankService;
import by.clevertec.CleverBank.services.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestService {


    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        UUID uuid = UUID.fromString("4c5d4f30-5c52-4631-8591-841c406cb3e3");
       LocalDateTime ldt = LocalDateTime.parse("2023-08-28T00:26:15.545");
      System.out.println(ldt);
        User user = new User();
        user.setName("Аркадий");
        user.setSurname("Кац");
        user.setPatronymic("Михайлович");
        userService.create(user);

        user.setName("Арнольд");
        user.setSurname("Цукерман");
        user.setPatronymic("Давидович");
        userService.create(user);

        user.setName("Яков");
        user.setSurname("Шварц");
        user.setPatronymic("Борисович");
        userService.create(user);

        user.setName("Ольга");
        user.setSurname("Бутько");
        user.setPatronymic("Владимировна");
        userService.create(user);

        user.setName("Израиль");
        user.setSurname("Колодницкий");
        user.setPatronymic("Яковлевич");
        userService.create(user);

        user.setName("Иосиф");
        user.setSurname("Бродский");
        user.setPatronymic("Александрович");
        userService.create(user);

        user.setName("Владимир");
        user.setSurname("Марцинкевич");
        user.setPatronymic("Михайлович");
        userService.create(user);

        user.setName("Лев");
        user.setSurname("Лещенко");
        user.setPatronymic("Львович");
        userService.create(user);

        user.setName("Геннадий");
        user.setSurname("Хазанов");
        user.setPatronymic("Викторович");
        userService.create(user);

        user.setName("Ефрем");
        user.setSurname("Амирамов");
        user.setPatronymic("Абрамович");
        userService.create(user);

        user.setName("Александр");
        user.setSurname("Градский");
        user.setPatronymic("Борисович");
        userService.create(user);

        user.setName("Ольга");
        user.setSurname("Бутько");
        user.setPatronymic("Владимировна");
        userService.create(user);

        user.setName("Валерий");
        user.setSurname("Гергиев");
        user.setPatronymic("Абисалович");
        userService.create(user);

        user.setName("Рудольф");
        user.setSurname("Нуриев");
        user.setPatronymic("Хаметович");
        userService.create(user);

        user.setName("Лидия");
        user.setSurname("Аксельрович");
        user.setPatronymic("Моисеевна");
        userService.create(user);

        user.setName("Борис");
        user.setSurname("Гребенщиков");
        user.setPatronymic("Борисович");
        userService.create(user);

        user.setName("Даниил");
        user.setSurname("Хармс");
        user.setPatronymic("Иванович");
        userService.create(user);

        user.setName("Николай");
        user.setSurname("Крыленко");
        user.setPatronymic("Александрович");
        userService.create(user);

        user.setName("Виктор");
        user.setSurname("Тюменский");
        user.setPatronymic("Николаевич");
        userService.create(user);

        user.setName("Евгений");
        user.setSurname("Науменко");
        user.setPatronymic("Петрович");
        userService.create(user);

//        System.out.println("Great");
         //System.out.println(userService.get(uuid));
       // System.out.println(userService.getAll());
        //System.out.println(userService.update(user, ldt));
      //  System.out.println(userService.delete(uuid, ldt));
//        System.out.println(userService.isExistByUuid(uuid));
//
//        BankService bankService = BankService.getInstance();
//
//        Bank bank = new Bank();
//        bank.setName("Status Bank");
//        bankService.create(bank);


    }
}
