package by.clevertec.CleverBank.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface IRowMapper <T>{
    T mapRow (ResultSet rs) throws SQLException;
}
