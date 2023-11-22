package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.CatalogoBienDAO;
import com.simios.simioapp.dominio.entidades.CatalogoBienEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("catalogoBienNegocio")
public class CatalogoBienNegocio extends BaseNegocio {

    @Autowired
    private CatalogoBienDAO catalogoBienDAO;

    public CatalogoBienEntity selectByID(Integer catalogoBienID) throws Exception {
        CatalogoBienEntity catalogoBien = new CatalogoBienEntity();

        catalogoBien.setCatalogoBienID(catalogoBienID);

        return catalogoBienDAO.selectByID(catalogoBien);
    }

    public List<CatalogoBienEntity> select(CatalogoBienEntity filter) throws Exception {

        return catalogoBienDAO.select(filter);
    }

    public List<CatalogoBienEntity> selectByMap(Map<String, Object> params) throws Exception {

        return catalogoBienDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return catalogoBienDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(CatalogoBienEntity entity) throws Exception {

        catalogoBienDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(CatalogoBienEntity entity) throws Exception {

        catalogoBienDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(CatalogoBienEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        catalogoBienDAO.update(entity);
    }

}
