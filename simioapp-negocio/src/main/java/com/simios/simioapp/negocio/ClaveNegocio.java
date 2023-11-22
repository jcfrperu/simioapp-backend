package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.ClaveDAO;
import com.simios.simioapp.dominio.entidades.ClaveEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("claveNegocio")
public class ClaveNegocio extends BaseNegocio {

    @Autowired
    private ClaveDAO claveDAO;

    public ClaveEntity selectByID(Integer claveID) throws Exception {
        ClaveEntity clave = new ClaveEntity();

        clave.setClaveID(claveID);

        return claveDAO.selectByID(clave);
    }

    public List<ClaveEntity> select(ClaveEntity filter) throws Exception {

        return claveDAO.select(filter);
    }

    public List<ClaveEntity> selectByMap(Map<String, Object> params) throws Exception {

        return claveDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return claveDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(ClaveEntity entity) throws Exception {

        claveDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(ClaveEntity entity) throws Exception {

        claveDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(ClaveEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        claveDAO.update(entity);
    }

}
