package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.CatalogoDAO;
import com.simios.simioapp.dominio.entidades.CatalogoEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("catalogoNegocio")
public class CatalogoNegocio extends BaseNegocio {

    @Autowired
    private CatalogoDAO catalogoDAO;

    public CatalogoEntity selectByID(Integer catalogoID) throws Exception {
        CatalogoEntity catalogo = new CatalogoEntity();

        catalogo.setCatalogoID(catalogoID);

        return catalogoDAO.selectByID(catalogo);
    }

    public List<CatalogoEntity> select(CatalogoEntity filter) throws Exception {

        return catalogoDAO.select(filter);
    }

    public List<CatalogoEntity> selectByMap(Map<String, Object> params) throws Exception {

        return catalogoDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return catalogoDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(CatalogoEntity entity) throws Exception {

        catalogoDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(CatalogoEntity entity) throws Exception {

        catalogoDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(CatalogoEntity entity) throws Exception {
        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        catalogoDAO.update(entity);
    }

    public List<ListaItem> selectCatalogoCombo(CatalogoEntity filter) throws Exception {
        return catalogoDAO.selectCatalogoCombo(filter);
    }
}
