package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.EmpleadoUbicacionDAO;
import com.simios.simioapp.dominio.entidades.EmpleadoUbicacionEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("empleadoUbicacionNegocio")
public class EmpleadoUbicacionNegocio extends BaseNegocio {

    @Autowired
    private EmpleadoUbicacionDAO empleadoUbicacionDAO;

    public EmpleadoUbicacionEntity selectByID(Integer empleadoUbicacionID) throws Exception {
        EmpleadoUbicacionEntity empleadoUbicacion = new EmpleadoUbicacionEntity();

        empleadoUbicacion.setEmpleadoUbicacionID(empleadoUbicacionID);

        return empleadoUbicacionDAO.selectByID(empleadoUbicacion);
    }

    public List<EmpleadoUbicacionEntity> select(EmpleadoUbicacionEntity filter) throws Exception {

        return empleadoUbicacionDAO.select(filter);
    }

    public List<EmpleadoUbicacionEntity> selectByMap(Map<String, Object> params) throws Exception {

        return empleadoUbicacionDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return empleadoUbicacionDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(EmpleadoUbicacionEntity entity) throws Exception {

        empleadoUbicacionDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(EmpleadoUbicacionEntity entity) throws Exception {

        empleadoUbicacionDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(EmpleadoUbicacionEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        empleadoUbicacionDAO.update(entity);
    }

}
