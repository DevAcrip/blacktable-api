package com.ix.servicios;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ix.dto.TiposIdentificacionDto;
import com.ix.interfaces.IRepository;
import com.ix.interfaces.IAplicacion;
import com.ix.utilidades.Excepciones;

public class TiposIdentificacionServicesTest {

    @Mock
    private IAplicacion aplicacion;

    @Mock
    private IRepository<TiposIdentificacionDto> repository;

    @InjectMocks
    private TiposIdentificacionServices service;

    @BeforeMethod
    public void setUp() {
        aplicacion = new AplicacionMock();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLista() throws Excepciones {
        TiposIdentificacionDto dto = new TiposIdentificacionDto();
        dto.setId(1);
        dto.setNombre("Test");
        dto.setEstado("Activo");

        when(repository.lista(TiposIdentificacionDto.class, List.of("S"), aplicacion.getBaseDatos()))
                .thenReturn(Arrays.asList(dto));

        List<TiposIdentificacionDto> result = service.lista();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getNombre());
    }

    @Test
    public void testObtenerEntidad() throws Excepciones {
        TiposIdentificacionDto dto = new TiposIdentificacionDto();
        dto.setId(1);
        dto.setNombre("Test");
        dto.setEstado("Activo");

        when(repository.lista(TiposIdentificacionDto.class, List.of(1), aplicacion.getBaseDatos()))
                .thenReturn(Arrays.asList(dto));

        TiposIdentificacionDto result = service.obtenerEntidad(1);
        assertNotNull(result);
        assertEquals("Test", result.getNombre());
    }

    @Test
    public void testCrea() throws Excepciones, SQLException {
        TiposIdentificacionDto dto = new TiposIdentificacionDto();
        dto.setId(1);
        dto.setNombre("Test");
        dto.setEstado("Activo");

        when(repository.crear(dto, aplicacion.getBaseDatos())).thenReturn(1);
        when(repository.lista(TiposIdentificacionDto.class, List.of(1), aplicacion.getBaseDatos()))
                .thenReturn(Arrays.asList(dto));

        TiposIdentificacionDto result = service.crea(dto);
        assertNotNull(result);
        assertEquals("Test", result.getNombre());
    }

    @Test
    public void testEdita() throws Excepciones, SQLException {
        TiposIdentificacionDto dto = new TiposIdentificacionDto();
        dto.setId(1);
        dto.setNombre("Test");
        dto.setEstado("Activo");

        when(repository.editar(dto, aplicacion.getBaseDatos())).thenReturn(1);
        when(repository.lista(TiposIdentificacionDto.class, List.of(1), aplicacion.getBaseDatos()))
                .thenReturn(Arrays.asList(dto));

        TiposIdentificacionDto result = service.edita(dto);
        assertNotNull(result);
        assertEquals("Test", result.getNombre());
    }

    @Test
    public void testElimina() throws Excepciones, SQLException {
        TiposIdentificacionDto dto = new TiposIdentificacionDto();
        dto.setId(1);

        when(repository.eliminar(dto, aplicacion.getBaseDatos())).thenReturn(1);

        TiposIdentificacionDto result = service.elimina(dto);
        assertNotNull(result);
    }
}