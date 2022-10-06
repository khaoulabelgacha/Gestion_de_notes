package dao;

import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;

public abstract class IActionDao<T> {
    protected Connection connect = null;
	
	public abstract T add(T t) throws SQLException;
	public abstract List<T> list() throws SQLException;
	public abstract T  getbyId(int id) throws SQLException;
	public abstract T  getFiliereByCode(String code) throws SQLException;
    public abstract void delete (int id) throws SQLException;
	public abstract void update(T t ) throws SQLException;
}
