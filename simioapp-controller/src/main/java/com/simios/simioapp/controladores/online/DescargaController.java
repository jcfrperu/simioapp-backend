package com.simios.simioapp.controladores.online;

import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.ListaUtil;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.controladores.util.HttpUtil;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.dominio.entidades.UsuarioEntity;
import com.simios.simioapp.servicios.DescargadorService;
import com.simios.simioapp.servicios.InventarioService;
import org.apache.commons.collections.CollectionUtils;
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

public class DescargaController extends BaseController {

    private static final Logger log = Logger.getLogger(DescargaController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-descarga";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("descargadorService")
    private DescargadorService descargadorService;

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;

    public ModelAndView mostrarPanelDescarga(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            // para esta opcion primero debe generar token/credenciales
            Credencial credencial;
            String msgCredenciales = StringUtils.EMPTY;

            try {

                credencial = HttpUtil.checkCredencialesTokenQuiet(request);

            } catch (Exception sos) {

                credencial = null;
                msgCredenciales = sos.getMessage();
            }

            if (credencial == null) {
                throw new Exception("No tiene credenciales válidas. Primero debe generar un token. " + msgCredenciales);
            }

            // se envia el token y todas las demas credenciales
            model.put("token", credencial.getToken());
            model.put("usuario", credencial.getUsuario());
            model.put("entidad", credencial.getEntidad());
            model.put("inventario", credencial.getInventario());

            // buscando la descripcion de usuarioNombre
            model.put("usuarioNombre", StringUtils.EMPTY);

            UsuarioEntity usuarioFilter = new UsuarioEntity();
            usuarioFilter.setEntidadID(JS.toInt(credencial.getEntidad()));
            usuarioFilter.setUsuarioID(credencial.getUsuario());
            usuarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<UsuarioEntity> usuarios = usuarioService.select(usuarioFilter);

            if (CollectionUtils.isNotEmpty(usuarios)) {
                model.put("usuarioNombre", JS.toBlank(usuarios.get(0).getNombres()) + " " + JS.toBlank(usuarios.get(0).getApellidos()));
            }


            // buscando la descripcion de entidad
            model.put("entidadNombre", StringUtils.EMPTY);

            EntidadEntity entidadFilter = new EntidadEntity();
            entidadFilter.setEntidadID(JS.toInt(credencial.getEntidad()));
            entidadFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<EntidadEntity> entidades = entidadService.select(entidadFilter);

            if (CollectionUtils.isNotEmpty(entidades)) {
                model.put("entidadNombre", JS.toBlank(entidades.get(0).getEntidad()));
            }

            // buscando la descripcion de inventarioNombre
            model.put("inventarioNombre", StringUtils.EMPTY);

            InventarioEntity inventarioFilter = new InventarioEntity();
            inventarioFilter.setEntidadID(JS.toInt(credencial.getEntidad()));
            inventarioFilter.setInventarioID(JS.toInt(credencial.getInventario()));
            inventarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<InventarioEntity> inventarios = inventarioService.select(inventarioFilter);

            if (CollectionUtils.isNotEmpty(inventarios)) {
                model.put("inventarioNombre", JS.toBlank(inventarios.get(0).getNombre()));
            }

            setVistaTemplate(model, "panel-descarga", modulo, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-MPD-341", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }

    public ModelAndView descargarTabla(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String nombreTabla = JS.toBlank(request.getParameter("nombreTabla"));

            // chequear credenciales
            Credencial credencial = HttpUtil.checkCredencialesTokenQuiet(request);

            int entidadID = JS.toInt(credencial.getEntidad());
            int inventarioID = JS.toInt(credencial.getInventario());
            String usuario = JS.toBlank(credencial.getUsuario()); // se asume que el que descarga es el inventariador mismo

            HashMap<String, Object> model = new HashMap<>();

            List lista;

            // por seguridad, no se envia el nombre de la tabla, sino un sinonimo y
            // solo se especifica las tablas a descargar (no permite descargar cualquiera)
            if (StringUtils.equals(nombreTabla, "area")) {

                lista = descargadorService.buscarTablaArea(entidadID);

            } else if (StringUtils.equals(nombreTabla, "bien")) {

                lista = descargadorService.buscarTablaBien(entidadID);

            } else if (StringUtils.equals(nombreTabla, "catalogo")) {

                lista = descargadorService.buscarTablaCatalogo();

            } else if (StringUtils.equals(nombreTabla, "catalogoBien")) {

                lista = descargadorService.buscarTablaCatalogoBien();

            } else if (StringUtils.equals(nombreTabla, "clase")) {

                lista = descargadorService.buscarTablaClase();

            } else if (StringUtils.equals(nombreTabla, "cuenta")) {

                lista = descargadorService.buscarTablaCuenta();

            } else if (StringUtils.equals(nombreTabla, "dependencia")) {

                lista = descargadorService.buscarTablaDependencia();

            } else if (StringUtils.equals(nombreTabla, "empleado")) {

                lista = descargadorService.buscarTablaEmpleado(entidadID);

            } else if (StringUtils.equals(nombreTabla, "empleadoUbicacion")) {

                lista = descargadorService.buscarTablaEmpleadoUbicacion(entidadID);

            } else if (StringUtils.equals(nombreTabla, "entidad")) {

                lista = descargadorService.buscarTablaEntidad(entidadID);

            } else if (StringUtils.equals(nombreTabla, "grupo")) {

                lista = descargadorService.buscarTablaGrupo();

            } else if (StringUtils.equals(nombreTabla, "grupoClase")) {

                lista = descargadorService.buscarTablaGrupoClase();

            } else if (StringUtils.equals(nombreTabla, "locales")) {

                lista = descargadorService.buscarTablaLocales(entidadID);

            } else if (StringUtils.equals(nombreTabla, "oficina")) {

                lista = descargadorService.buscarTablaOficina(entidadID);

            } else if (StringUtils.equals(nombreTabla, "parametro")) {

                lista = descargadorService.buscarTablaParametro();

            } else if (StringUtils.equals(nombreTabla, "ubigeo")) {

                lista = descargadorService.buscarTablaUbigeo();

            } else if (StringUtils.equals(nombreTabla, "inventario")) {

                lista = descargadorService.buscarTablaInventario(entidadID, inventarioID);

            } else if (StringUtils.equals(nombreTabla, "inventarioBien")) {

                // solo los asignados al simio inventariador
                lista = descargadorService.buscarTablaInventarioBien(entidadID, inventarioID, usuario);

            } else if (StringUtils.equals(nombreTabla, "inventarioApertura")) {

                // en inventario apertura se le envia todos los bienes
                lista = descargadorService.buscarTablaInventarioApertura(entidadID, inventarioID);

            } else {

                throw new SimioException("ADC-DT-389", "TablaSync no soportada");
            }

            model.put("lista", ListaUtil.asegurarNotNull(lista));

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("ADC-DT-199", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

}
