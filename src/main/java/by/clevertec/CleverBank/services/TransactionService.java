package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.dao.TransactionStorage;
import by.clevertec.CleverBank.dao.api.ITransactionStorage;
import by.clevertec.CleverBank.model.Transaction;
import by.clevertec.CleverBank.services.api.ITransactionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ITransactionService {
    private final ITransactionStorage transactionStorage = new TransactionStorage();

    @Override
    public Transaction get(UUID uuid) {
        return transactionStorage.get(uuid);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionStorage.getAll();
    }

    @Override
    public Transaction create(Transaction transaction) {
        LocalDateTime ldt = LocalDateTime.now();
        transaction.setUuid(UUID.randomUUID());
        transaction.setDbCreate(ldt);
        transaction.setDbLastUpdate(ldt);
        return transactionStorage.create(transaction);
    }

    @Override
    public Transaction delete(UUID uuid, LocalDateTime dbLastUpdate) {
        return transactionStorage.delete(uuid, dbLastUpdate);
    }

    @Override
    public Transaction update(Transaction transaction, LocalDateTime dbLastUpdate) {
        return transactionStorage.update(transaction, transaction.getUuid(), dbLastUpdate);
    }
}
