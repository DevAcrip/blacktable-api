package com.ix.excepciones;

public class ExcepcionAplicacion extends Exception {

    private static final long serialVersionUID = 1L;

    public ExcepcionAplicacion(String mensaje) {
        super(mensaje);
    }

    public ExcepcionAplicacion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ExcepcionAplicacion(Throwable causa) {
        super(causa);
    }

    public ExcepcionAplicacion(String mensaje, Throwable causa, boolean enableSuppression, boolean writableStackTrace) {
        super(mensaje, causa, enableSuppression, writableStackTrace);
    }
}

