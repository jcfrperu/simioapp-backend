package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.CuentaEntity;
import com.simios.simioapp.negocio.CuentaNegocio;
import com.simios.simioapp.servicios.CuentaService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("cuentaService")
public class CuentaServiceImpl extends BaseService implements CuentaService {

    private static final Logger log = Logger.getLogger(CuentaServiceImpl.class.getName());

    @Autowired
    @Qualifier("cuentaNegocio")
    private CuentaNegocio cuentaNegocio;

    public CuentaEntity selectByID(Integer cuentaID) throws Exception {

        try {

            return cuentaNegocio.selectByID(cuentaID);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBI-043", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<CuentaEntity> select(CuentaEntity filter) throws Exception {

        try {

            return cuentaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SEL-044", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<CuentaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return cuentaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBM-045", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return cuentaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBG-046", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(CuentaEntity entity) throws Exception {

        try {

            cuentaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-INS-047", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(CuentaEntity entity) throws Exception {

        try {

            cuentaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-UPD-048", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(CuentaEntity entity) throws Exception {

        try {

            cuentaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-DEL-049", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectCuentaCombo(CuentaEntity filtro) throws Exception {

        return cuentaNegocio.selectCuentaCombo(filtro);
    }

}
