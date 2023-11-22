package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.UsuarioDAO;
import com.simios.simioapp.dominio.entidades.UsuarioEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("usuarioNegocio")
public class UsuarioNegocio extends BaseNegocio {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public UsuarioEntity selectByID(String usuarioID) throws Exception {
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setUsuarioID(usuarioID);

        return usuarioDAO.selectByID(usuario);
    }

    public List<UsuarioEntity> select(UsuarioEntity filter) throws Exception {

        return usuarioDAO.select(filter);
    }

    public List<UsuarioEntity> selectByMap(Map<String, Object> params) throws Exception {

        return usuarioDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return usuarioDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(UsuarioEntity entity) throws Exception {

        usuarioDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(UsuarioEntity entity) throws Exception {

        usuarioDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(UsuarioEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        usuarioDAO.update(entity);
    }

}
