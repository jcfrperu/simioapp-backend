package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.OpcionBotonesDAO;
import com.simios.simioapp.dominio.entidades.OpcionBotonesEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("opcionBotonesNegocio")
public class OpcionBotonesNegocio extends BaseNegocio {

    @Autowired
    private OpcionBotonesDAO opcionBotonesDAO;

    public OpcionBotonesEntity selectByID(Integer opcionBotonesID) throws Exception {
        OpcionBotonesEntity opcionBotones = new OpcionBotonesEntity();

        opcionBotones.setOpcionID(opcionBotonesID);
        opcionBotones.setBotonID(opcionBotonesID);

        return opcionBotonesDAO.selectByID(opcionBotones);
    }

    public List<OpcionBotonesEntity> select(OpcionBotonesEntity filter) throws Exception {

        return opcionBotonesDAO.select(filter);
    }

    public List<OpcionBotonesEntity> selectByMap(Map<String, Object> params) throws Exception {

        return opcionBotonesDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return opcionBotonesDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(OpcionBotonesEntity entity) throws Exception {

        opcionBotonesDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(OpcionBotonesEntity entity) throws Exception {

        opcionBotonesDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(OpcionBotonesEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        opcionBotonesDAO.update(entity);
    }

}
