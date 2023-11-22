package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dominio.entidades.CatalogoEntity;
import com.simios.simioapp.negocio.CatalogoNegocio;
import com.simios.simioapp.servicios.CatalogoService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("catalogoService")
public class CatalogoServiceImpl extends BaseService implements CatalogoService {

    private static final Logger log = Logger.getLogger(CatalogoServiceImpl.class.getName());

    @Autowired
    @Qualifier("catalogoNegocio")
    private CatalogoNegocio catalogoNegocio;

    public CatalogoEntity selectByID(Integer catalogoID) throws Exception {

        try {

            return catalogoNegocio.selectByID(catalogoID);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBI-015", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<CatalogoEntity> select(CatalogoEntity filter) throws Exception {

        try {

            return catalogoNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SEL-016", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<CatalogoEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return catalogoNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBM-017", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return catalogoNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBG-018", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(CatalogoEntity entity) throws Exception {

        try {

            catalogoNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-INS-019", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(CatalogoEntity entity) throws Exception {

        try {

            catalogoNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-UPD-020", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(CatalogoEntity entity) throws Exception {

        try {

            catalogoNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-DEL-021", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectCatalogoCombo(String catalogo, boolean soloDetalles, boolean soloActivos) throws Exception {
        try {

            CatalogoEntity filtro = new CatalogoEntity();

            filtro.setCatalogo(catalogo);

            if (soloDetalles) {
                filtro.setTipo(Constantes.CATALOGO_TIPO_DETALLE);
            }

            if (soloActivos) {
                filtro.setIndDel(Constantes.REGISTRO_ACTIVO);
            }

            return catalogoNegocio.selectCatalogoCombo(filtro);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBC-052", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }
}
