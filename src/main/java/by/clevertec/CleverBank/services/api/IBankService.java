package by.clevertec.CleverBank.services.api;

import by.clevertec.CleverBank.model.Bank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IBankService {

    Bank get (UUID uuid);
    List<Bank> getAll();

    boolean isExistByUuid (UUID uuid);
    Bank create (Bank bank);
    Bank delete (UUID uuid, LocalDateTime dbLastUpdate);
    Bank update (Bank bank, LocalDateTime dbLastUpdate);
}
