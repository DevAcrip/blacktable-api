package com.ix.interfaces;

import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.jsp.jstl.sql.Result;

public interface IConsultorSQL {
    Result consultar(String sql) throws SQLException;
    Result consultar(String sql, List<Object> parametros) throws SQLException;
}
