package by.clevertec.CleverBank;

import by.clevertec.CleverBank.dao.UserStorage;
import by.clevertec.CleverBank.model.User;
import by.clevertec.CleverBank.services.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestService {


    public static void main(String[] args) {
        UserService userService = new UserService();
        UUID uuid = UUID.fromString("4c5d4f30-5c52-4631-8591-841c406cb3e3");
       LocalDateTime ldt = LocalDateTime.parse("2023-08-28T00:26:15.545");
      System.out.println(ldt);
        User user = new User();
        //user.setUuid(uuid);
        user.setName("Matty3");
        user.setSurname("Pretty4");
        user.setPatronymic("Alex5");
       // userService.create(user);
//        System.out.println("Great");
         //System.out.println(userService.get(uuid));
       // System.out.println(userService.getAll());
        //System.out.println(userService.update(user, ldt));
      //  System.out.println(userService.delete(uuid, ldt));
        System.out.println(userService.isExist(uuid));
    }
}
