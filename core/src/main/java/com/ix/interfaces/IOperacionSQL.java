package com.ix.interfaces;

import java.sql.CallableStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface IOperacionSQL<T>{
		
	int ejecutar(T t, CallableStatement cs) throws SQLException;
	
}
