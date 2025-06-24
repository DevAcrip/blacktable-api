package com.ix.implementacion;

import com.ix.interfaces.IAplicacion;
import com.ix.interfaces.IRepository;
import com.ix.interfaces.IServices;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class Services<T> implements IServices<T> {

    protected final IAplicacion aplicacion;
    protected final IRepository<T> repository;

    @Inject
    public Services(IAplicacion aplicacion, IRepository<T> repository) {
        this.aplicacion = aplicacion;
        this.repository = repository;
    }

    @Override
    public IRepository<T> getRepository() {
        return repository;
    }

    @Override
    public IAplicacion getAplicacion() {
        return aplicacion;
    }

    // Puedes exponer aquí métodos genéricos de acceso si deseas
}
