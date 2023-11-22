package com.simios.simioapp.negocio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.CuentaDAO;
import com.simios.simioapp.dominio.entidades.CuentaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;

@Component("cuentaNegocio")
public class CuentaNegocio extends BaseNegocio {

    @Autowired
    private CuentaDAO cuentaDAO;

    public CuentaEntity selectByID(Integer cuentaID) throws Exception {
        CuentaEntity cuenta = new CuentaEntity();

        cuenta.setCuentaID(cuentaID);

        return cuentaDAO.selectByID(cuenta);
    }

    public List<CuentaEntity> select(CuentaEntity filter) throws Exception {

        return cuentaDAO.select(filter);
    }

    public List<CuentaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return cuentaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return cuentaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(CuentaEntity entity) throws Exception {

        cuentaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(CuentaEntity entity) throws Exception {

        cuentaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(CuentaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        cuentaDAO.update(entity);
    }

    public List<ListaItem> selectCuentaCombo(CuentaEntity filter) throws Exception {
        return cuentaDAO.selectCuentaCombo(filter);
    }

}
