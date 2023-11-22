package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.DependenciaDAO;
import com.simios.simioapp.dominio.entidades.DependenciaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("dependenciaNegocio")
public class DependenciaNegocio extends BaseNegocio {

    @Autowired
    private DependenciaDAO dependenciaDAO;

    public DependenciaEntity selectByID(Integer dependenciaID) throws Exception {
        DependenciaEntity dependencia = new DependenciaEntity();

        dependencia.setDependenciaID(dependenciaID);

        return dependenciaDAO.selectByID(dependencia);
    }

    public List<DependenciaEntity> select(DependenciaEntity filter) throws Exception {

        return dependenciaDAO.select(filter);
    }

    public List<DependenciaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return dependenciaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return dependenciaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(DependenciaEntity entity) throws Exception {

        dependenciaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(DependenciaEntity entity) throws Exception {

        dependenciaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(DependenciaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        dependenciaDAO.update(entity);
    }

    public List<ListaItem> selectDependenciaCombo(DependenciaEntity filter) throws Exception {
        return dependenciaDAO.selectDependenciaCombo(filter);
    }

}
