package by.clevertec.CleverBank.services.api;

import by.clevertec.CleverBank.model.Account;
import by.clevertec.CleverBank.model.Bank;
import by.clevertec.CleverBank.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IAccountService {

    Account get (UUID uuid);
    List<Account> gatAll ();
    Account create (Account account);
    Account delete (UUID uuid, LocalDateTime dbLastUpdate);
    Account update (Account account, LocalDateTime dbLastUpdate);
}
