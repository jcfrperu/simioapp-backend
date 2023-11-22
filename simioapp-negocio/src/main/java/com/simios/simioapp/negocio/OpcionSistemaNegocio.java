package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.OpcionSistemaDAO;
import com.simios.simioapp.dominio.entidades.OpcionSistemaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("opcionSistemaNegocio")
public class OpcionSistemaNegocio extends BaseNegocio {

    @Autowired
    private OpcionSistemaDAO opcionSistemaDAO;

    public OpcionSistemaEntity selectByID(Integer opcionSistemaID) throws Exception {
        OpcionSistemaEntity opcionSistema = new OpcionSistemaEntity();

        opcionSistema.setOpcionID(opcionSistemaID);

        return opcionSistemaDAO.selectByID(opcionSistema);
    }

    public List<OpcionSistemaEntity> select(OpcionSistemaEntity filter) throws Exception {

        return opcionSistemaDAO.select(filter);
    }

    public List<OpcionSistemaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return opcionSistemaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return opcionSistemaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(OpcionSistemaEntity entity) throws Exception {

        opcionSistemaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(OpcionSistemaEntity entity) throws Exception {

        opcionSistemaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(OpcionSistemaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        opcionSistemaDAO.update(entity);
    }

}
