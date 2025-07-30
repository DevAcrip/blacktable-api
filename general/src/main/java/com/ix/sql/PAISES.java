package com.ix.sql;

public class PAISES {
    
    // CREAR: AAPLICACION, AEMPRESA, AROL, AUSUARIO, AIP, ISOTEXTO, ISONUMERICO, NOMBRE
    public static final String CREAR = """
            {?=call IGENERAL.GESTIONPAISES.CREAR(?,?,?,?,?,
             ?,?,?)}""";

    // EDITAR: AAPLICACION, AEMPRESA, AROL, AUSUARIO, AIP, ID, ISOTEXTO, ISONUMERICO, NOMBRE
    public static final String EDITAR = """
            {?=call IGENERAL.GESTIONPAISES.EDITAR(?,?,?,?,?,
             ?,?,?,?)}""";

    // ELIMINAR: AAPLICACION, AEMPRESA, AROL, AUSUARIO, AIP, ID
    public static final String ELIMINAR = """
            {?=call IGENERAL.GESTIONPAISES.ELIMINAR(?,?,?,?,?,
             ?)}""";

    // CONSULTAR: AAPLICACION, AEMPRESA, AROL, AUSUARIO, AIP
    public static final String CONSULTAR = """
            {?=call IGENERAL.GESTIONPAISES.CONSULTAR(?,?,?,?,?)}""";

    // OBTENER_POR_ID: AAPLICACION, AEMPRESA, AROL, AUSUARIO, AIP, ID
    public static final String OBTENER_POR_ID = """
            {?=call IGENERAL.GESTIONPAISES.OBTENER_POR_ID(?,?,?,?,?,
             ?)}""";

    // CONSULTAR_POR_NOMBRE: AAPLICACION, AEMPRESA, AROL, AUSUARIO, AIP, NOMBRE
    public static final String CONSULTAR_POR_NOMBRE = """
            {?=call IGENERAL.GESTIONPAISES.CONSULTAR_POR_NOMBRE(?,?,?,?,?,
             ?)}""";
}