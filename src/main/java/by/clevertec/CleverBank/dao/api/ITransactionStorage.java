package by.clevertec.CleverBank.dao.api;

import by.clevertec.CleverBank.model.Transaction;
import by.clevertec.CleverBank.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ITransactionStorage extends ICUDRepository<Transaction, UUID>{
    Transaction get (UUID uuid);
    List<Transaction> getAll();
    @Override
    Transaction create(Transaction transaction);

    @Override
    Transaction update(Transaction transaction, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;

    @Override
    Transaction delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;
}
