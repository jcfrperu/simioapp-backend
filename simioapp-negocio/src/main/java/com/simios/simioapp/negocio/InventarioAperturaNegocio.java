package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.InventarioAperturaDAO;
import com.simios.simioapp.dominio.entidades.InventarioAperturaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("inventarioAperturaNegocio")
public class InventarioAperturaNegocio extends BaseNegocio {

    @Autowired
    private InventarioAperturaDAO inventarioAperturaDAO;

    public InventarioAperturaEntity selectByID(Integer inventarioAperturaID) throws Exception {
        InventarioAperturaEntity inventarioApertura = new InventarioAperturaEntity();

        inventarioApertura.setInventarioAperturaID(inventarioAperturaID);

        return inventarioAperturaDAO.selectByID(inventarioApertura);
    }

    public List<InventarioAperturaEntity> select(InventarioAperturaEntity filter) throws Exception {

        return inventarioAperturaDAO.select(filter);
    }

    public List<InventarioAperturaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return inventarioAperturaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return inventarioAperturaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(InventarioAperturaEntity entity) throws Exception {

        inventarioAperturaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(InventarioAperturaEntity entity) throws Exception {

        inventarioAperturaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(InventarioAperturaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        inventarioAperturaDAO.update(entity);
    }

}
