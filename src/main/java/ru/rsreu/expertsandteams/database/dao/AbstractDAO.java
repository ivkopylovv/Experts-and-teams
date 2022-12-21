package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.database.ConnectionPool;

import java.sql.Connection;

public abstract class AbstractDAO {
    protected Connection connection;
    protected Resourcer resourcer;

    public AbstractDAO() {
        this.connection = ConnectionPool.getConnection();
        this.resourcer = ProjectResourcer.getInstance();
    }
}
