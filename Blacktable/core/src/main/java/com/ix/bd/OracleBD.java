package com.ix.bd;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ix.interfaces.IConexion;
import com.ix.interfaces.IOperacionSQL;
import com.ix.utilidades.Excepciones;

import jakarta.servlet.jsp.jstl.sql.Result;
import jakarta.servlet.jsp.jstl.sql.ResultSupport;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

/**
*/
public class OracleBD extends BaseDatos {		
		
	public OracleBD(Conexion cx) {
		super(cx);
		//TODO Auto-generated constructor stub
	}

	public OracleBD() {
		super();
	}

	@Override
	public void setConexion(IConexion conexion) {
		this.conexion=conexion;
	}

	@Override
	public Result datos(String sql) throws SQLException{
		Result resultado=null;
		CallableStatement cs=null;
	   	try{
	   		this.conexion.conectar();	 	   		
	   		cs=this.conexion.getConnection().prepareCall (sql);
	   		cs.registerOutParameter(1, OracleTypes.CURSOR);
	   		cs.execute();
			
			resultado = ResultSupport.toResult((ResultSet)cs.getObject(1));
					
			} catch (SQLException e) {						
				throw e;
	    	}finally{   	
	    		try
	    		{
	    			if(cs!=null) cs.close();
	    		}
	    		catch(Exception ex)
	    		{
	    			ex.printStackTrace();
	    		}
	    		this.conexion.desconectar();
	    	}
	    return resultado;	
	  } 	
	
	@Override
	public Result datos(String sql,List<Object> parametros) throws SQLException
	{
		Result resultado=null;
		CallableStatement cs=null;		
		try {
			
			this.conexion.conectar();	 	   		
	   		cs=this.conexion.getConnection().prepareCall (sql);

		    cs.registerOutParameter (1, OracleTypes.CURSOR);
		    
	   		for(int i=0;i<parametros.size();i++)
						cs.setObject(i+2,parametros.get(i));

		    cs.execute ();
		    resultado= ResultSupport.toResult((ResultSet)cs.getObject (1));

		}catch (SQLException e) {
			throw e;
    	} 
		 finally{
			 try
			 {
				 if(cs!=null) cs.close();
			 }
			 catch(Exception ex)
			 {
				 ex.printStackTrace();
			 }
			 this.conexion.desconectar();	
			}
		return resultado;
	}
	
	@Override
	public <D> int ejecutarSQL(D d, Map<String,IOperacionSQL<D>> mapOperacionesSQL) throws Excepciones, SQLException {

		int resultado=0;
		List<CallableStatement> lstCallableStatement= new ArrayList<CallableStatement>();
		try {
			getConexion().conectar();		
			getConexion().getConnection().setAutoCommit(false);					
			
			for (Map.Entry<String,IOperacionSQL<D>> entry : mapOperacionesSQL.entrySet()) {
				String sql = entry.getKey();	            
				IOperacionSQL<D> operacion= entry.getValue();
	            System.out.println("Ejecutando SQL: " + sql);	            
	            try {
	            	CallableStatement cs= getConexion().getConnection().prepareCall(sql);
	            	lstCallableStatement.add(cs);
	               int i= operacion.ejecutar(d,cs);
	            } catch (SQLException e) {
	            	try {
	                	getConexion().getConnection().rollback();
	                        System.out.println("Transaction rolled back");
	                } catch (SQLException se) {
	                    se.printStackTrace();
	                }        	
	                e.printStackTrace();
	                throw e; 
	            }
	        }
			getConexion().getConnection().commit();
        } finally {
            	for (CallableStatement cs : lstCallableStatement) {
    			    if (cs != null) {
    			        try {
    			            cs.close();
    			            System.out.println("Cerrando cs");
    			        } catch (SQLException se) {
    			            se.printStackTrace();
    			        }
    			    }
    			}
               getConexion().desconectar();            
        }
		return resultado;
	}

	@Override
	public <D> int ejecutarSql(D d, String sql, IOperacionSQL<D> operacionSQL) throws Excepciones {
		CallableStatement cs=null;
		int resultado=0;
		try {
			getConexion().conectar();		
			getConexion().getConnection().setAutoCommit(false);		
			cs= getConexion().getConnection().prepareCall(sql);			
			
			resultado=operacionSQL.ejecutar(d,cs);
			
			getConexion().getConnection().commit();
		} catch (SQLException e) {
            e.printStackTrace();
            try {
               getConexion().getConnection().rollback();
                    System.out.println("Transaction rolled back");
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
               getConexion().desconectar();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
		return resultado;
	}
		
}
