package com.ix.servicios;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.ix.dto.PaisesDto;
import com.ix.interfaces.IAplicacion;
import com.ix.interfaces.IPaisesRepository;
import com.ix.interfaces.IPaisesServices;
import com.ix.interfaces.IRepository;
import com.ix.utilidades.Constantes;
import com.ix.utilidades.Excepciones;
import com.ix.utilidades.Resultado;

public class PaisesServices implements IPaisesServices {

    private final IAplicacion aplicacion;
    private final IPaisesRepository paisesRepository;

    public PaisesServices(IAplicacion aplicacion, IPaisesRepository repository) {
        this.aplicacion = aplicacion;
        this.paisesRepository = repository;
    }

    @Override
    public IRepository<PaisesDto> getRepository() {
        // IPaisesRepository extiende ICrudRepository, no IRepository
        // Esto puede necesitar ajuste en la arquitectura
        return null; // Temporal
    }

    @Override
    public IAplicacion getAplicacion() {
        return aplicacion;
    }

    @Override
    public List<PaisesDto> lista() throws Excepciones {
        return paisesRepository.obtenerTodos(PaisesDto.class);
    }

    @Override
    public PaisesDto obtenerEntidad(int id) throws Excepciones {
        Optional<PaisesDto> resultado = paisesRepository.obtenerPorId(PaisesDto.class, id);
        
        if (resultado.isEmpty()) {
            throw new Excepciones("País no encontrado con ID: " + id);
        }
        
        return resultado.get();
    }

    @Override
    public PaisesDto crea(PaisesDto dto) throws Excepciones, SQLException {
        int resultado = paisesRepository.insertar(dto);

        Resultado.validar(() -> resultado >= 0, String.format(Constantes.ERRORSERVICIO, resultado));

        return obtenerEntidad(resultado); // El resultado es el ID generado
    }

    @Override
    public PaisesDto edita(PaisesDto dto) throws Excepciones, SQLException {
        int resultado = paisesRepository.actualizar(dto);

        Resultado.validar(() -> resultado >= 0, String.format(Constantes.ERRORSERVICIO, resultado));

        return obtenerEntidad(dto.getId());
    }

    @Override
    public PaisesDto elimina(PaisesDto dto) throws Excepciones, SQLException {
        int resultado = paisesRepository.eliminar(dto.getId());

        Resultado.validar(() -> resultado >= 0, String.format(Constantes.ERRORSERVICIO, resultado));
        
        return new PaisesDto();
    }

    @Override
    public List<PaisesDto> consultarPorNombre(String nombre) throws Excepciones {
        return paisesRepository.consultarPorNombre(nombre);
    }
}