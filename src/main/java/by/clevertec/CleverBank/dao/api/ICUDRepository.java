package by.clevertec.CleverBank.dao.api;

import java.time.LocalDateTime;

public interface ICUDRepository<T, ID> {
    /**
     * Создание сущноти в хранилище
     * @param item сущность которую создаём
     * @return
     */
    T create(T item);

    /**
     * Обновление сущности по координатам
     * @param item поля для обновлениея
     * @param id координата сущности однозначно
     *           идентифицирующая обновляемую сущность
     * @param lastUpdate координата сущности когда её
     *                   в последний раз обновляли
     * @throws EssenceNotFound - если не удалось найти обновляемую сущность
     * @return сущность с обновлёнными полями
     */
    T update(T item, ID id, LocalDateTime lastUpdate) throws EssenceNotFound;

    /**
     * Удаление сущности по координатам
     * @param id координата сущности однозначно
     *           идентифицирующая удаляемую сущность
     * @param lastUpdate координата сущности когда её
     *                   в последний раз обновляли
     * @throws EssenceNotFound - если не удалось найти удаляемую сущность
     */
    T delete(ID id, LocalDateTime lastUpdate) throws EssenceNotFound;
}
