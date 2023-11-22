package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.ParametroDAO;
import com.simios.simioapp.dominio.entidades.ParametroEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("parametroNegocio")
public class ParametroNegocio extends BaseNegocio {

    @Autowired
    private ParametroDAO parametroDAO;

    public ParametroEntity selectByID(Integer parametroID) throws Exception {
        ParametroEntity parametro = new ParametroEntity();

        parametro.setParametroID(parametroID);

        return parametroDAO.selectByID(parametro);
    }

    public List<ParametroEntity> select(ParametroEntity filter) throws Exception {

        return parametroDAO.select(filter);
    }

    public List<ParametroEntity> selectByMap(Map<String, Object> params) throws Exception {

        return parametroDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return parametroDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(ParametroEntity entity) throws Exception {

        parametroDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(ParametroEntity entity) throws Exception {

        parametroDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(ParametroEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        parametroDAO.update(entity);
    }

}
