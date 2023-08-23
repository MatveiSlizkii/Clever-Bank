package by.clevertec.CleverBank.dao.api;

import by.clevertec.CleverBank.model.Bank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IBankStorage extends ICUDRepository<Bank, UUID> {

    Bank get (UUID uuid);

    List<Bank> getAll ();

    @Override
    Bank create(Bank bank);

    @Override
    Bank update(Bank bank, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;

    @Override
    Bank delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;
}
