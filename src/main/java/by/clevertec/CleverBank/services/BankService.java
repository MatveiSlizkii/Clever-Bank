package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.dao.BankStorage;
import by.clevertec.CleverBank.dao.api.IBankStorage;
import by.clevertec.CleverBank.model.Bank;
import by.clevertec.CleverBank.model.User;
import by.clevertec.CleverBank.services.api.IBankService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BankService implements IBankService {
    private final IBankStorage bankStorage = new BankStorage();
    @Override
    public Bank get(UUID uuid) {

        return bankStorage.get(uuid);
    }

    @Override
    public List<Bank> gatAll() {
        return bankStorage.getAll();
    }

    @Override
    public Bank create(Bank bank) {
        LocalDateTime ldt = LocalDateTime.now();

        bank.setUuid(UUID.randomUUID());
        bank.setDbCreate(ldt);
        bank.setDbLastUpdate(ldt);
        bankStorage.create(bank);

        return bank;
    }

    @Override
    public Bank delete(UUID uuid, LocalDateTime dbLastUpdate) {

        return bankStorage.delete(uuid, dbLastUpdate);
    }

    @Override
    public Bank update(Bank bank, LocalDateTime dbLastUpdate) {
        return bankStorage.update(bank, bank.getUuid(), dbLastUpdate);
    }
}
