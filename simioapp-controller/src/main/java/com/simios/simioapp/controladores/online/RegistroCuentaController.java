package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.CuentaEntity;
import com.simios.simioapp.servicios.CuentaService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RegistroCuentaController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroCuentaController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-cuenta";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("cuentaService")
    private CuentaService cuentaService;

    public ModelAndView buscarCuenta(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String cuenta = JS.toUpperBlank(request.getParameter("txt_cuenta"));
            String denomina = JS.toUpperBlank(request.getParameter("txt_denomina"));
            String tipoCta = JS.toBlank(request.getParameter("txt_tipoCta"));
            String flagCta = JS.toBlank(request.getParameter("txt_flagCta"));
            String usoCta = JS.toBlank(request.getParameter("txt_usoCta"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "cuenta-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(cuenta)) params.put("cuenta", "%" + cuenta + "%");
            if (!JS._vacio(denomina)) params.put("denomina", "%" + denomina + "%");
            if (!JS._vacio(tipoCta)) params.put("tipoCta", "%" + tipoCta + "%");
            if (!JS._vacio(flagCta)) params.put("flagCta", "%" + flagCta + "%");
            if (!JS._vacio(usoCta)) params.put("usoCta", usoCta + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaCuenta = cuentaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaCuenta);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-BCA-233", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private CuentaEntity buscarRegistroPorUnique(String cuenta) throws Exception {

        CuentaEntity filtro = new CuentaEntity();

        filtro.setCuenta(cuenta);

        List<CuentaEntity> resultados = cuentaService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String cuentaID = JS.toBlank(request.getParameter("cuentaID"));
            if (!JS._numeroEntero(cuentaID)) throw new SimioException("RCC-ECA-234", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CuentaEntity entity = new CuentaEntity();

            entity.setCuentaID(JS.toInt(cuentaID));

            cuentaService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-ELI-235", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    public ModelAndView guardarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            Map<String, Object> result = validarEditar(request);

            String msgError = MapUtils.getString(result, "msgError");
            if (!JS._vacio(msgError)) {
                throw new SimioException("RCC-GED-236", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CuentaEntity registro = (CuentaEntity) result.get("registro");

            cuentaService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GUA-237", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    public ModelAndView guardarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            Map<String, Object> result = validarGuardar(request);
            String msgError = MapUtils.getString(result, "msgError");
            if (!JS._vacio(msgError)) {
                throw new SimioException("RCC-GNU-238", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CuentaEntity registro = (CuentaEntity) result.get("registro");

            cuentaService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getCuentaID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GNU-239", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

        // limpiar los atributos de session
        // setSessionAttribute(request,
        // Constantes.ADMIN_REGISTRO_CATEGORIAS.SESSION_XXXX, null);
    }

    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "cuenta-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboTipoCuenta", catalogo.CUENTA_TIPOCUENTA);
            cargarComboCatalogoTodos(request, "cboFlagCuenta", catalogo.CUENTA_FLAGCUENTA);
            cargarComboCatalogoTodos(request, "cboUsoCuenta", catalogo.CUENTA_USOCUENTA);
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaCuenta = cuentaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaCuenta);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String cuentaID = JS.toBlank(request.getParameter("cuentaID"));
            if (!JS._numeroEntero(cuentaID)) throw new SimioException("RCC-MED-240", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "cuenta-editar", modulo, plantilla);

            // delegar logica a servicios
            CuentaEntity registro = cuentaService.selectByID(JS.toInt(cuentaID));
            if (registro == null) throw new SimioException("RCC-MED-241", "Cuenta no encontrada");

            cargarComboCatalogoSeleccione(request, "cboTipoCuenta", catalogo.CUENTA_TIPOCUENTA, registro.getTipoCta());
            cargarComboCatalogoSeleccione(request, "cboFlagCuenta", catalogo.CUENTA_FLAGCUENTA, registro.getFlagCta());
            cargarComboCatalogoSeleccione(request, "cboUsoCuenta", catalogo.CUENTA_USOCUENTA, registro.getUsoCta());
            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-MED-212", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "cuenta-nuevo", modulo, plantilla);

            cargarComboCatalogoSeleccione(request, "cboTipoCuenta", catalogo.CUENTA_TIPOCUENTA);
            cargarComboCatalogoSeleccione(request, "cboFlagCuenta", catalogo.CUENTA_FLAGCUENTA);
            cargarComboCatalogoSeleccione(request, "cboUsoCuenta", catalogo.CUENTA_USOCUENTA);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String cuentaID = JS.toUpperBlank(request.getParameter("int_cuentaID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para cuentaID
            if (!JS._vacio(cuentaID)) {

                String msg = JS._numeroEnteroNoNegativoString(cuentaID, "CuentaID");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_cuentaID");
                    result.put("msgError", msg);

                    throw new SimioException("RCC-VBU-242", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-VAB-243", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String cuentaID = JS.toBlank(request.getParameter("int_cuentaID"));
        String cuenta = JS.toUpperBlank(request.getParameter("txt_cuenta"));
        String denomina = StringUtils.upperCase(request.getParameter("txt_denomina"));
        String tipoCta = JS.toBlank(request.getParameter("txt_tipoCta"));
        String flagCta = JS.toBlank(request.getParameter("txt_flagCta"));
        String usoCta = JS.toBlank(request.getParameter("txt_usoCta"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar cuentaID
        String msg = JS._numeroEnteroNoNegativoString(cuentaID, "Código");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_cuentaID");
            result.put("msgError", msg);

            return result;
        }

        // validar cuenta
        msg = JS._campoNoVacio(cuenta, "Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_cuenta");
            result.put("msgError", msg);

            return result;
        }

        // validar denomina
        msg = JS._campoNoVacio(denomina, "Denominación");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_denomina");
            result.put("msgError", msg);

            return result;
        }

        // validar tipoCta
        msg = JS._campoNoVacio(tipoCta, "Tipo Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_tipoCta");
            result.put("msgError", msg);

            return result;
        }

        if (JS._equiv(tipoCta, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_tipoCta");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar flagCta
        msg = JS._campoNoVacio(flagCta, "Flag Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flagCta");
            result.put("msgError", msg);

            return result;
        }

        if (JS._equiv(flagCta, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flagCta");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar usoCta
        msg = JS._campoNoVacio(usoCta, "Uso Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_usoCta");
            result.put("msgError", msg);

            return result;
        }

        if (JS._equiv(usoCta, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_usoCta");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar indDel
        msg = JS._campoNoVacio(indDel, "indDel");
        if (!JS._vacio(msg)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", msg);

            return result;
        }

        if (JS._equiv(indDel, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar que no exista otro igual
        CuentaEntity found = buscarRegistroPorUnique(cuenta);
        if (found != null && !JS._equiv(found.getCuentaID(), JS.toInt(cuentaID))) {
            result.put("campoError", "general");
            result.put("msgError", "Cuenta ya fue registrada");

            return result;
        }

        // armar entity
        CuentaEntity registro = new CuentaEntity();

        registro.setCuenta(cuenta);
        registro.setCuentaID(JS.toInt(cuentaID));
        registro.setDenomina(denomina);
        registro.setFlagCta(flagCta);
        registro.setTipoCta(tipoCta);
        registro.setUsoCta(usoCta);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String cuenta = JS.toUpperBlank(request.getParameter("txt_cuenta"));
        String denomina = StringUtils.upperCase(request.getParameter("txt_denomina"));
        String tipoCta = JS.toBlank(request.getParameter("txt_tipoCta"));
        String flagCta = JS.toBlank(request.getParameter("txt_flagCta"));
        String usoCta = JS.toBlank(request.getParameter("txt_usoCta"));

        Map<String, Object> result = new HashMap<>();

        // validar cuenta
        String msg = JS._campoNoVacio(cuenta, "Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_cuenta");
            result.put("msgError", msg);

            return result;
        }

        // validar denomina
        msg = JS._campoNoVacio(denomina, "Denominación");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_denomina");
            result.put("msgError", msg);

            return result;
        }

        // validar tipoCta
        msg = JS._campoNoVacio(tipoCta, "Tipo Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_tipoCta");
            result.put("msgError", msg);

            return result;
        }

        if (JS._equiv(tipoCta, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_tipoCta");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar flagCta
        msg = JS._campoNoVacio(flagCta, "Flag Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flagCta");
            result.put("msgError", msg);

            return result;
        }

        if (JS._equiv(flagCta, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flagCta");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar usoCta
        msg = JS._campoNoVacio(usoCta, "Uso Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_usoCta");
            result.put("msgError", msg);

            return result;
        }

        if (JS._equiv(usoCta, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_usoCta");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar que no exista otro igual
        CuentaEntity found = buscarRegistroPorUnique(cuenta);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Cuenta ya fue registrada");

            return result;
        }

        // armar entity
        CuentaEntity registro = new CuentaEntity();

        registro.setCuenta(cuenta);
        registro.setDenomina(denomina);
        registro.setFlagCta(flagCta);
        registro.setTipoCta(tipoCta);
        registro.setUsoCta(usoCta);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verCuenta(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String cuentaID = JS.toBlank(request.getParameter("cuentaID"));
            if (!JS._numeroEntero(cuentaID)) throw new SimioException("RCC-VCA-244", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "cuenta-ver", modulo, plantilla);

            // delegar logica a servicios
            CuentaEntity registro = cuentaService.selectByID(JS.toInt(cuentaID));
            if (registro == null) throw new SimioException("RCC-VEC-245", "Cuenta no encontrada");

            cargarComboCatalogoSeleccione(request, "cboTipoCuenta", catalogo.CUENTA_TIPOCUENTA, registro.getTipoCta());
            cargarComboCatalogoSeleccione(request, "cboFlagCuenta", catalogo.CUENTA_FLAGCUENTA, registro.getFlagCta());
            cargarComboCatalogoSeleccione(request, "cboUsoCuenta", catalogo.CUENTA_USOCUENTA, registro.getUsoCta());
            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
