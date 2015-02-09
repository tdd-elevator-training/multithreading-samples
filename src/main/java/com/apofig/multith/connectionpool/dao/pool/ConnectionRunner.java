package com.apofig.multith.connectionpool.dao.pool;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ConnectionRunner<T> {
    T connect(PreparedStatement statement) throws SQLException;
}