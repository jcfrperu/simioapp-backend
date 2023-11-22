package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.BienDAO;
import com.simios.simioapp.dominio.entidades.BienEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("bienNegocio")
public class BienNegocio extends BaseNegocio {

    @Autowired
    private BienDAO bienDAO;

    public BienEntity selectByID(Integer bienID) throws Exception {
        BienEntity bien = new BienEntity();

        bien.setBienID(bienID);
        
        return bienDAO.selectByID(bien);
    }

    public List<BienEntity> select(BienEntity filter) throws Exception {

        return bienDAO.select(filter);
    }

    public List<BienEntity> selectByMap(Map<String, Object> params) throws Exception {

        return bienDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return bienDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(BienEntity entity) throws Exception {

        bienDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(BienEntity entity) throws Exception {

        bienDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(BienEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        bienDAO.update(entity);
    }

}
