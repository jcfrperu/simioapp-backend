package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.UsuarioSessionDAO;
import com.simios.simioapp.dominio.entidades.UsuarioSessionEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("usuarioSessionNegocio")
public class UsuarioSessionNegocio extends BaseNegocio {

    @Autowired
    private UsuarioSessionDAO usuarioSessionDAO;

    public UsuarioSessionEntity selectByID(String usuarioID) throws Exception {
        UsuarioSessionEntity usuarioSession = new UsuarioSessionEntity();

        usuarioSession.setUsuarioID(usuarioID);

        return usuarioSessionDAO.selectByID(usuarioSession);
    }

    public List<UsuarioSessionEntity> select(UsuarioSessionEntity filter) throws Exception {

        return usuarioSessionDAO.select(filter);
    }

    public List<UsuarioSessionEntity> selectByMap(Map<String, Object> params) throws Exception {

        return usuarioSessionDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return usuarioSessionDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(UsuarioSessionEntity entity) throws Exception {

        usuarioSessionDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(UsuarioSessionEntity entity) throws Exception {

        usuarioSessionDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(UsuarioSessionEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        usuarioSessionDAO.update(entity);
    }

}
