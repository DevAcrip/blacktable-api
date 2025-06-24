package com.ix.implementacion;

import com.ix.bd.Conexion;
import com.ix.interfaces.IAplicacion;
import com.ix.interfaces.IConexion;
import com.ix.interfaces.IJndiResolver;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@RequestScoped
public class ConexionProducer {

    @Inject
    private IAplicacion aplicacion;

    @Inject
    private IJndiResolver jndiResolver;

    @Produces
    public IConexion producirConexion() {
        Integer idAplicacion = aplicacion.getId();
        String jndi = jndiResolver.resolverJndi(idAplicacion);
        return new Conexion(jndi);
    }
    
    /*@RequestScoped
	public class RolesRepository {

    private final BaseDatos baseDatos;

    @Inject
    public RolesRepository(IConexion conexion) {
        this.baseDatos = new BaseDatos(conexion);
    }

    // métodos que usan baseDatos.consultar(...), ejecutar(...), etc.
		}*/
}
