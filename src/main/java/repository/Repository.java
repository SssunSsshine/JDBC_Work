package repository;

import entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;

public interface Repository <T extends Entity>{
    T get(Long id) throws SQLException;
    void insert(T entity);
    void delete(@Nullable String whereCondition);
    void update(String setCondition, @Nullable String whereCondition);

}
