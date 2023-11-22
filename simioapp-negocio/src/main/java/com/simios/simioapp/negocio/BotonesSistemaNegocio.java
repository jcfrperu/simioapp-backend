package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.BotonesSistemaDAO;
import com.simios.simioapp.dominio.entidades.BotonesSistemaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("botonesSistemaNegocio")
public class BotonesSistemaNegocio extends BaseNegocio {

    @Autowired
    private BotonesSistemaDAO botonesSistemaDAO;

    public BotonesSistemaEntity selectByID(Integer botonesSistemaID) throws Exception {
        BotonesSistemaEntity botonesSistema = new BotonesSistemaEntity();

        botonesSistema.setBotonID(botonesSistemaID);

        return botonesSistemaDAO.selectByID(botonesSistema);
    }

    public List<BotonesSistemaEntity> select(BotonesSistemaEntity filter) throws Exception {

        return botonesSistemaDAO.select(filter);
    }

    public List<BotonesSistemaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return botonesSistemaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return botonesSistemaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(BotonesSistemaEntity entity) throws Exception {

        botonesSistemaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(BotonesSistemaEntity entity) throws Exception {

        botonesSistemaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(BotonesSistemaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        botonesSistemaDAO.update(entity);
    }

}
