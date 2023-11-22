package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.jcfr.utiles.web.ComboWeb;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.*;
import com.simios.simioapp.servicios.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RegistroEmpleadoUbicacionController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroEmpleadoUbicacionController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-empleadoubicacion";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("empleadoUbicacionService")
    private EmpleadoUbicacionService empleadoUbicacionService;

    @Autowired
    @Qualifier("localesService")
    private LocalesService localesService;

    @Autowired
    @Qualifier("areaService")
    private AreaService areaService;

    @Autowired
    @Qualifier("oficinaService")
    private OficinaService oficinaService;

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    public ModelAndView buscarEmpleadoUbicacion(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String empleadoUbicacionID = JS.toUpperBlank(request.getParameter("int_empleadoUbicacionID"));
            String cbo_empleado = JS.toUpperBlank(request.getParameter("cbo_empleado"));
            String entidadID = JS.toUpperBlank(request.getParameter("cboEntidad"));
            String cbo_area = JS.toUpperBlank(request.getParameter("cbo_area"));
            String cbo_local = JS.toUpperBlank(request.getParameter("cbo_local"));
            String cbo_oficina = JS.toUpperBlank(request.getParameter("cbo_oficina"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));
            String txt_tipoAsociacion = JS.toUpperBlank(request.getParameter("txt_tipoAsociacion"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "empleadoubicacion-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (JS._numeroEntero(empleadoUbicacionID)) params.put("empleadoUbicacionID", JS.toInt(empleadoUbicacionID));
            if (JS._numeroEntero(cbo_empleado)) params.put("empleadoID", JS.toInt(cbo_empleado));
            if (JS._numeroEntero(entidadID)) params.put("entidadID", JS.toInt(entidadID));
            if (JS._numeroEntero(cbo_local)) params.put("localesID", JS.toInt(cbo_local));
            if (JS._numeroEntero(cbo_area)) params.put("areaID", JS.toInt(cbo_area));
            if (JS._numeroEntero(cbo_oficina)) params.put("oficinaID", JS.toInt(cbo_oficina));
            if (!JS._vacio(txt_tipoAsociacion)) params.put("tipoAsociacion", txt_tipoAsociacion);
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaEmpleadoUbicacion = empleadoUbicacionService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaEmpleadoUbicacion);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-BCA-500", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private EmpleadoUbicacionEntity buscarRegistroPorUnique(Integer empleadoId, Integer entidadId, Integer localesId, Integer areaId, Integer oficinaId) throws Exception {

        EmpleadoUbicacionEntity filtro = new EmpleadoUbicacionEntity();

        // empleadoId y entidadId son not null
        filtro.setEmpleadoID(empleadoId);
        filtro.setEntidadID(entidadId);

        // localesId, areaId y oficinaId son opcionales
        filtro.setLocalesID(localesId);
        filtro.setAreaID(areaId);
        filtro.setOficinaID(oficinaId);

        List<EmpleadoUbicacionEntity> ubicaciones = empleadoUbicacionService.select(filtro);

        if (CollectionUtils.isNotEmpty(ubicaciones)) {
            for (EmpleadoUbicacionEntity ubicacion : ubicaciones) {

                // si son "iguales" en cada uno de los campos (considerando nulabilidad)
                if (equalsNulls(ubicacion.getEmpleadoID(), empleadoId) &&
                        equalsNulls(ubicacion.getEntidadID(), entidadId) &&
                        equalsNulls(ubicacion.getLocalesID(), localesId) &&
                        equalsNulls(ubicacion.getAreaID(), areaId) &&
                        equalsNulls(ubicacion.getOficinaID(), oficinaId)) {

                    return ubicacion;
                }
            }

        }

        return null;
    }

    private boolean equalsNulls(Integer valor01, Integer valor02) {
        if (valor01 == null) {
            return valor02 == null ? true : valor02.equals(valor01);
        }
        return valor02 == null ? false : valor02.equals(valor01);
    }

    public ModelAndView eliminarEmpleadoUbicacion(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String empleadoUbicacionID = JS.toBlank(request.getParameter("empleadoUbicacionID"));
            if (!JS._numeroEntero(empleadoUbicacionID)) {
                throw new SimioException("REC-ECA-501", "Argumento ilegal del request");
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EmpleadoUbicacionEntity entity = new EmpleadoUbicacionEntity();

            entity.setEmpleadoUbicacionID(JS.toInt(empleadoUbicacionID));

            empleadoUbicacionService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-ELI-425", dataJSON, sos);
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
                throw new SimioException("REC-GED-316", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EmpleadoUbicacionEntity registro = (EmpleadoUbicacionEntity) result.get("registro");

            empleadoUbicacionService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-GUA-317", dataJSON, sos);
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
                throw new SimioException("REC-GNU-318", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EmpleadoUbicacionEntity registro = (EmpleadoUbicacionEntity) result.get("registro");

            empleadoUbicacionService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getEmpleadoUbicacionID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-GNU-319", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

    }

    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "empleadoubicacion-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
            cargarComboCatalogoTodos(request, "cboTipoAsociacion", catalogo.UBICACION_EMPLEADO_ASOCIACION);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            // cargar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboTodos(request, "cboLocal", itemsLocal);

            ComboWeb comboWebTodos = new ComboWeb();
            comboWebTodos.addItemSelIni(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL);

            // cargar combo areas
            request.setAttribute("cboArea", comboWebTodos);

            // cargar combo oficinas
            request.setAttribute("cboOficina", comboWebTodos);

            // cargar combo empleados
            EmpleadoEntity filter = new EmpleadoEntity();
            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsEmpleados = empleadoService.selectEmpleadoCombo(filter);
            cargarComboTodos(request, "cboEmpleado", itemsEmpleados);

            List<Map<String, Object>> listaEmpleadoUbicacion = empleadoUbicacionService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaEmpleadoUbicacion);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String empleadoUbicacionID = JS.toBlank(request.getParameter("empleadoUbicacionID"));
            if (!JS._numeroEntero(empleadoUbicacionID)) {
                throw new SimioException("REC-MED-320", "Argumento ilegal del request");
            }


            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "empleadoubicacion-editar", modulo, plantilla);

            // delegar logica a servicios
            EmpleadoUbicacionEntity registro = empleadoUbicacionService.selectByID(JS.toInt(empleadoUbicacionID));
            if (registro == null) throw new SimioException("REC-MED-321", "Ubicaci&oacute;n de empleado no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
            cargarComboCatalogoSeleccione(request, "cboTipoAsociacion", catalogo.UBICACION_EMPLEADO_ASOCIACION, registro.getTipoAsociacion());

            ComboWeb comboWebTodos = new ComboWeb();
            comboWebTodos.addItemSelIni(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            // cargar combo local
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboTodos(request, "cboLocal", itemsLocal, registro.getLocalesID());

            // cargar combo area
            // si ubicacion no tiene local (sino escogio local al guardar), el combo area debe aparecer con un solo elemento (TODOS)
            if (registro.getLocalesID() == null) {
                request.setAttribute("cboArea", comboWebTodos);
            } else {
                AreaEntity filtroArea = new AreaEntity();
                filtroArea.setIndDel(Constantes.REGISTRO_ACTIVO);
                filtroArea.setEntidadID(registro.getEntidadID());
                filtroArea.setLocalesID(registro.getLocalesID());
                List<ListaItem> itemsArea = areaService.selectAreaCombo(filtroArea);
                cargarComboTodos(request, "cboArea", itemsArea, registro.getAreaID());
            }

            // cargar combo oficinas
            if (registro.getLocalesID() == null) {
                request.setAttribute("cboOficina", comboWebTodos);
            } else {
                OficinaEntity filtroOficina = new OficinaEntity();
                filtroOficina.setIndDel(Constantes.REGISTRO_ACTIVO);
                filtroOficina.setEntidadID(registro.getEntidadID());
                filtroOficina.setLocalesID(registro.getLocalesID());
                filtroOficina.setAreaID(registro.getAreaID());
                List<ListaItem> itemsOficina = oficinaService.selectOficinaCombo(filtroOficina);
                cargarComboTodos(request, "cboOficina", itemsOficina, registro.getOficinaID());
            }


            // cargar combo empleados
            EmpleadoEntity filter = new EmpleadoEntity();
            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsEmpleados = empleadoService.selectEmpleadoCombo(filter);
            cargarComboSeleccione(request, "cboEmpleado", itemsEmpleados, registro.getEmpleadoID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MED-212", sos);
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
            setVistaTemplate(model, "empleadoubicacion-nuevo", modulo, plantilla);

            cargarComboCatalogoSeleccione(request, "cboTipoAsociacion", catalogo.UBICACION_EMPLEADO_ASOCIACION, catalogo.UBICACION_EMPLEADO_ASOCIACION_NINGUNA);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            // cargar combo local
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboTodos(request, "cboLocal", itemsLocal);

            ComboWeb comboWebTodos = new ComboWeb();
            comboWebTodos.addItemSelIni(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL);

            // cargar combo area
            request.setAttribute("cboArea", comboWebTodos);

            // cargar combo oficinas
            request.setAttribute("cboOficina", comboWebTodos);

            // cargar combo empleados
            EmpleadoEntity filter = new EmpleadoEntity();
            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsEmpleados = empleadoService.selectEmpleadoCombo(filter);
            cargarComboSeleccione(request, "cboEmpleado", itemsEmpleados);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String int_empleadoUbicacionID = JS.toUpperBlank(request.getParameter("int_empleadoUbicacionID"));
            String cboEmpleado = JS.toUpperBlank(request.getParameter("cboEmpleado"));
            String cboEntidad = JS.toBlank(request.getParameter("cboEntidad"));
            String cbo_local = JS.toUpperBlank(request.getParameter("cbo_local"));
            String cbo_area = JS.toUpperBlank(request.getParameter("cbo_area"));
            String cbo_oficina = JS.toUpperBlank(request.getParameter("cbo_oficina"));

            String msg;
            Map<String, Object> result = new HashMap<>();

            // validacion para int_empleadoUbicacionID
            if (!JS._vacio(int_empleadoUbicacionID)) {

                msg = JS._numeroEnteroNoNegativoString(int_empleadoUbicacionID, "Código");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_empleadoUbicacionID");
                    result.put("msgError", msg);

                    throw new SimioException("REU-VBU-322", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para cboEmpleado
            if (!JS._vacio(cboEmpleado)) {

                msg = JS._numeroEnteroNoNegativoString(cboEmpleado, "Empleado");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cboEmpleado");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para cboEntidad
            if (!JS._vacio(cboEntidad)) {

                msg = JS._numeroEnteroNoNegativoString(cboEntidad, "Entidad");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cboEntidad");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para cbo_local
            if (!JS._vacio(cbo_local)) {

                msg = JS._numeroEnteroNoNegativoString(cbo_local, "Local");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cbo_local");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para cbo_area
            if (!JS._vacio(cbo_area)) {

                msg = JS._numeroEnteroNoNegativoString(cbo_area, "Área");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cbo_area");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para cbo_oficina
            if (!JS._vacio(cbo_oficina)) {

                msg = JS._numeroEnteroNoNegativoString(cbo_oficina, "Oficina");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cbo_oficina");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }


            HashMap<String, Object> model = new HashMap<String, Object>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-VAB-323", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String int_empleadoUbicacionID = JS.toUpperBlank(request.getParameter("int_empleadoUbicacionID"));
        String cbo_empleado = JS.toUpperBlank(request.getParameter("cbo_empleado"));
        String cboEntidad = JS.toUpperBlank(request.getParameter("cboEntidad"));
        String cbo_area = JS.toUpperBlank(request.getParameter("cbo_area"));
        String cbo_local = JS.toUpperBlank(request.getParameter("cbo_local"));
        String cbo_oficina = JS.toUpperBlank(request.getParameter("cbo_oficina"));
        String txt_tipoAsociacion = JS.toUpperBlank(request.getParameter("txt_tipoAsociacion"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar para int_empleadoUbicacionID
        if (JS._vacio(int_empleadoUbicacionID) || JS._equiv(int_empleadoUbicacionID, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "int_empleadoUbicacionID");
            result.put("msgError", "Código no puede ser vacío");
            return result;
        }

        if (JS._vacio(cbo_empleado) || JS._equiv(cbo_empleado, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cbo_empleado");
            result.put("msgError", "Por favor seleccione un empleado");
            return result;
        }

        if (JS._vacio(cboEntidad) || JS._equiv(cboEntidad, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cboEntidad");
            result.put("msgError", "Por favor seleccione elemento");
            return result;
        }

        // locales, area y oficina son opcionales, pueden venir en blanco

        if (JS._vacio(txt_tipoAsociacion)) {
            result.put("campoError", "txt_tipoAsociacion");
            result.put("msgError", "Tipo de asociación no puede ser vacía");
            return result;
        }

        // validar combo indDel
        if (JS._equiv(indDel, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar que no exista otro igual
        EmpleadoUbicacionEntity found = buscarRegistroPorUnique(JS.toInt(cbo_empleado), JS.toInt(cboEntidad),
                JS._numeroEntero(cbo_local) ? JS.toInt(cbo_local) : null,
                JS._numeroEntero(cbo_area) ? JS.toInt(cbo_area) : null,
                JS._numeroEntero(cbo_oficina) ? JS.toInt(cbo_oficina) : null);

        if (found != null && !JS._equiv(found.getEmpleadoUbicacionID(), JS.toInt(int_empleadoUbicacionID))) {
            result.put("campoError", "general");
            result.put("msgError", "Ubicaci&oacute;n del empleado ya fue registrada");

            return result;
        }

        // armar entity
        EmpleadoUbicacionEntity registro = new EmpleadoUbicacionEntity();

        registro.setEmpleadoUbicacionID(JS.toInt(int_empleadoUbicacionID));
        registro.setEmpleadoID(JS.toInt(cbo_empleado));
        registro.setEntidadID(JS.toInt(cboEntidad));
        if (JS._numeroEntero(cbo_local)) registro.setLocalesID(JS.toInt(cbo_local));
        if (JS._numeroEntero(cbo_area)) registro.setAreaID(JS.toInt(cbo_area));
        if (JS._numeroEntero(cbo_oficina)) registro.setOficinaID(JS.toInt(cbo_oficina));
        registro.setTipoAsociacion(JS.toNullIf(txt_tipoAsociacion));
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String cbo_empleado = JS.toUpperBlank(request.getParameter("cbo_empleado"));
        String cboEntidad = JS.toUpperBlank(request.getParameter("cboEntidad"));
        String cbo_area = JS.toUpperBlank(request.getParameter("cbo_area"));
        String cbo_local = JS.toUpperBlank(request.getParameter("cbo_local"));
        String cbo_oficina = JS.toUpperBlank(request.getParameter("cbo_oficina"));
        String txt_tipoAsociacion = JS.toUpperBlank(request.getParameter("txt_tipoAsociacion"));

        Map<String, Object> result = new HashMap<>();

        if (JS._vacio(cbo_empleado) || JS._equiv(cbo_empleado, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cbo_empleado");
            result.put("msgError", "Por favor seleccione un empleado");
            return result;
        }

        if (JS._vacio(cboEntidad) || JS._equiv(cboEntidad, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cboEntidad");
            result.put("msgError", "Por favor seleccione elemento");
            return result;
        }

        // locales, area y oficina son opcionales, pueden venir en blanco

        if (JS._vacio(txt_tipoAsociacion) || JS._equiv(txt_tipoAsociacion, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "txt_tipoAsociacion");
            result.put("msgError", "Tipo de asociación no puede ser vacía");
            return result;
        }

        // validar que no exista otro igual
        EmpleadoUbicacionEntity found = buscarRegistroPorUnique(JS.toInt(cbo_empleado), JS.toInt(cboEntidad),
                JS._numeroEntero(cbo_local) ? JS.toInt(cbo_local) : null,
                JS._numeroEntero(cbo_area) ? JS.toInt(cbo_area) : null,
                JS._numeroEntero(cbo_oficina) ? JS.toInt(cbo_oficina) : null);

        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Ubicaci&oacute;n del empleado ya fue registrada");

            return result;
        }

        // armar entity
        EmpleadoUbicacionEntity registro = new EmpleadoUbicacionEntity();

        registro.setEntidadID(JS.toInt(cboEntidad));
        registro.setEmpleadoID(JS.toInt(cbo_empleado));
        if (JS._numeroEntero(cbo_local)) registro.setLocalesID(JS.toInt(cbo_local));
        if (JS._numeroEntero(cbo_area)) registro.setAreaID(JS.toInt(cbo_area));
        if (JS._numeroEntero(cbo_oficina)) registro.setOficinaID(JS.toInt(cbo_oficina));
        registro.setTipoAsociacion(JS.toNullIf(txt_tipoAsociacion));
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verEmpleadoUbicacion(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String empleadoUbicacionID = JS.toBlank(request.getParameter("empleadoUbicacionID"));
            if (!JS._numeroEntero(empleadoUbicacionID)) {
                throw new SimioException("REC-VCA-324", "Argumento ilegal del request");
            }

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "empleadoubicacion-ver", modulo, plantilla);

            // delegar logica a servicios
            EmpleadoUbicacionEntity registro = empleadoUbicacionService.selectByID(JS.toInt(empleadoUbicacionID));
            if (registro == null) throw new SimioException("REC-VEC-325", "Ubicaci&oacute;n de empleado no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
            cargarComboCatalogoSeleccione(request, "cboTipoAsociacion", catalogo.UBICACION_EMPLEADO_ASOCIACION, registro.getTipoAsociacion());

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            // cargar combo local
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboTodos(request, "cboLocal", itemsLocal, registro.getLocalesID());

            // cargar combo area
            AreaEntity filtroArea = new AreaEntity();
            filtroArea.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroArea.setEntidadID(registro.getEntidadID());
            filtroArea.setLocalesID(registro.getLocalesID());
            List<ListaItem> itemsArea = areaService.selectAreaCombo(filtroArea);
            cargarComboTodos(request, "cboArea", itemsArea, registro.getAreaID());

            // cargar combo oficinas
            OficinaEntity filtroOficina = new OficinaEntity();
            filtroOficina.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroOficina.setEntidadID(registro.getEntidadID());
            filtroOficina.setLocalesID(registro.getLocalesID());
            filtroOficina.setAreaID(registro.getAreaID());
            List<ListaItem> itemsOficina = oficinaService.selectOficinaCombo(filtroOficina);
            cargarComboTodos(request, "cboOficina", itemsOficina, registro.getOficinaID());

            // cargar combo empleados
            EmpleadoEntity filter = new EmpleadoEntity();
            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsEmpleados = empleadoService.selectEmpleadoCombo(filter);
            cargarComboSeleccione(request, "cboEmpleado", itemsEmpleados, registro.getEmpleadoID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView cargarAreas(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String entidadID = JS.toBlank(request.getParameter("cboEntidad"));
            String localesID = JS.toBlank(request.getParameter("cbo_local"));
            String formulario = JS.toBlank(request.getParameter("formulario"));

            List<ListaItem> items = new ArrayList<>();

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            if (JS._numeroEntero(entidadID) && JS._numeroEntero(localesID)) {

                AreaEntity filtroArea = new AreaEntity();

                filtroArea.setIndDel(Constantes.REGISTRO_ACTIVO);
                filtroArea.setEntidadID(JS.toInt(entidadID));
                filtroArea.setLocalesID(JS.toInt(localesID));

                items = areaService.selectAreaCombo(filtroArea);
            }

            if (formulario.equalsIgnoreCase("NUEVO") || formulario.equalsIgnoreCase("EDITAR")) {
                // para nuevo y editar, el area es opcional
                items.add(0, new ListaItem(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL));

            } else {

                items.add(0, new ListaItem(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL));
            }

            model.put("items", items);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RDC-GNU-143", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }


    public ModelAndView cargarOficinas(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String entidadID = JS.toBlank(request.getParameter("cboEntidad"));
            String localesID = JS.toBlank(request.getParameter("cbo_local"));
            String areaID = JS.toBlank(request.getParameter("cbo_area"));
            String formulario = JS.toBlank(request.getParameter("formulario"));

            List<ListaItem> items = new ArrayList<>();

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            if (JS._numeroEntero(entidadID) && JS._numeroEntero(localesID) && JS._numeroEntero(areaID)) {

                OficinaEntity filtroOficina = new OficinaEntity();

                filtroOficina.setIndDel(Constantes.REGISTRO_ACTIVO);
                filtroOficina.setEntidadID(JS.toInt(entidadID));
                filtroOficina.setLocalesID(JS.toInt(localesID));
                filtroOficina.setAreaID(JS.toInt(areaID));

                items = oficinaService.selectOficinaCombo(filtroOficina);
            }

            if (formulario.equalsIgnoreCase("NUEVO") || formulario.equalsIgnoreCase("EDITAR")) {

                // para nuevo y editar, la oficina es opcional
                items.add(0, new ListaItem(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL));

            } else {

                items.add(0, new ListaItem(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL));
            }

            model.put("items", items);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RDC-GNU-143", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

}
