package com.ix.implementacion;

import com.ix.interfaces.IAplicacion;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@RequestScoped
public class Aplicacion implements IAplicacion {

    @Inject
    private HttpServletRequest request;

    @Inject
    private ServletContext context;   

    @Override
    public Integer getId() {
        String id = request.getHeader("id-aplicacion");
        return id != null ? Integer.parseInt(id) : null;
    }

    @Override
    public ServletContext getContext() {
        return context;
    }

}
