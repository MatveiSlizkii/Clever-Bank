package by.clevertec.CleverBank.services.api;

import by.clevertec.CleverBank.model.Bank;
import by.clevertec.CleverBank.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IBankService {

    Bank get (UUID uuid);
    List<Bank> gatAll ();
    Bank create (Bank bank);
    Bank delete (UUID uuid, LocalDateTime dbLastUpdate);
    Bank update (Bank bank, LocalDateTime dbLastUpdate);
}
