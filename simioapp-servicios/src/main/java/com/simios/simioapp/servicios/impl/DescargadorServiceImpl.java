package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dominio.entidades.*;
import com.simios.simioapp.dominio.utiles.EntityUtil;
import com.simios.simioapp.negocio.*;
import com.simios.simioapp.servicios.DescargadorService;
import com.simios.simioapp.servicios.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service("descargadorService")
public class DescargadorServiceImpl extends BaseService implements DescargadorService {

    private static final Logger log = Logger.getLogger(DescargadorServiceImpl.class.getName());

    @Autowired
    @Qualifier("areaNegocio")
    private AreaNegocio areaNegocio;

    @Autowired
    @Qualifier("bienNegocio")
    private BienNegocio bienNegocio;

    @Autowired
    @Qualifier("catalogoBienNegocio")
    private CatalogoBienNegocio catalogoBienNegocio;

    @Autowired
    @Qualifier("catalogoNegocio")
    private CatalogoNegocio catalogoNegocio;

    @Autowired
    @Qualifier("claseNegocio")
    private ClaseNegocio claseNegocio;

    @Autowired
    @Qualifier("cuentaNegocio")
    private CuentaNegocio cuentaNegocio;

    @Autowired
    @Qualifier("dependenciaNegocio")
    private DependenciaNegocio dependenciaNegocio;

    @Autowired
    @Qualifier("empleadoNegocio")
    private EmpleadoNegocio empleadoNegocio;

    @Autowired
    @Qualifier("empleadoUbicacionNegocio")
    private EmpleadoUbicacionNegocio empleadoUbicacionNegocio;

    @Autowired
    @Qualifier("entidadNegocio")
    private EntidadNegocio entidadNegocio;

    @Autowired
    @Qualifier("grupoClaseNegocio")
    private GrupoClaseNegocio grupoClaseNegocio;

    @Autowired
    @Qualifier("grupoNegocio")
    private GrupoNegocio grupoNegocio;

    @Autowired
    @Qualifier("localesNegocio")
    private LocalesNegocio localesNegocio;

    @Autowired
    @Qualifier("oficinaNegocio")
    private OficinaNegocio oficinaNegocio;

    @Autowired
    @Qualifier("parametroNegocio")
    private ParametroNegocio parametroNegocio;

    @Autowired
    @Qualifier("ubigeoNegocio")
    private UbigeoNegocio ubigeoNegocio;

    @Autowired
    @Qualifier("usuarioNegocio")
    private UsuarioNegocio usuarioNegocio;

    @Autowired
    @Qualifier("inventarioNegocio")
    private InventarioNegocio inventarioNegocio;

    @Autowired
    @Qualifier("inventarioBienNegocio")
    private InventarioBienNegocio inventarioBienNegocio;

    @Autowired
    @Qualifier("inventarioAperturaNegocio")
    private InventarioAperturaNegocio inventarioAperturaNegocio;

    @Override
    public List<CatalogoBienEntity> buscarTablaCatalogoBien() throws Exception {

        try {

            // buscar solo los activos
            CatalogoBienEntity filter = new CatalogoBienEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<CatalogoBienEntity> lista = catalogoBienNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTC-229", sos);
            log.severe(msgError);
            throw handleError(sos);
        }

    }

    @Override
    public List<AreaEntity> buscarTablaArea(int entidadID) throws Exception {
        try {

            // buscar solo los activos
            AreaEntity filter = new AreaEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<AreaEntity> lista = areaNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTA-665", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<BienEntity> buscarTablaBien(int entidadID) throws Exception {
        try {

            // buscar solo los activos
            BienEntity filter = new BienEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<BienEntity> lista = bienNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTB-873", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<CatalogoEntity> buscarTablaCatalogo() throws Exception {
        try {

            // buscar solo los activos
            CatalogoEntity filter = new CatalogoEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<CatalogoEntity> lista = catalogoNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTC-331", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ClaseEntity> buscarTablaClase() throws Exception {
        try {

            // buscar solo los activos
            ClaseEntity filter = new ClaseEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<ClaseEntity> lista = claseNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTC-470", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<CuentaEntity> buscarTablaCuenta() throws Exception {
        try {

            // buscar solo los activos
            CuentaEntity filter = new CuentaEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<CuentaEntity> lista = cuentaNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTC-339", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<DependenciaEntity> buscarTablaDependencia() throws Exception {
        try {

            // buscar solo los activos
            DependenciaEntity filter = new DependenciaEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<DependenciaEntity> lista = dependenciaNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTD-372", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<EmpleadoEntity> buscarTablaEmpleado(int entidadID) throws Exception {
        try {

            // buscar solo los activos
            EmpleadoEntity filter = new EmpleadoEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<EmpleadoEntity> lista = empleadoNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTE-387", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<EmpleadoUbicacionEntity> buscarTablaEmpleadoUbicacion(int entidadID) throws Exception {

        try {

            // buscar solo los activos
            EmpleadoUbicacionEntity filter = new EmpleadoUbicacionEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<EmpleadoUbicacionEntity> lista = empleadoUbicacionNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTE-199", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<EntidadEntity> buscarTablaEntidad(int entidadID) throws Exception {
        try {

            // buscar solo los activos
            EntidadEntity filter = new EntidadEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<EntidadEntity> lista = entidadNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTE-266", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<GrupoEntity> buscarTablaGrupo() throws Exception {
        try {

            // buscar solo los activos
            GrupoEntity filter = new GrupoEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<GrupoEntity> lista = grupoNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTG-387", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<GrupoClaseEntity> buscarTablaGrupoClase() throws Exception {
        try {

            // buscar solo los activos
            GrupoClaseEntity filter = new GrupoClaseEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<GrupoClaseEntity> lista = grupoClaseNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTG-387", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<LocalesEntity> buscarTablaLocales(int entidadID) throws Exception {
        try {

            // buscar solo los activos
            LocalesEntity filter = new LocalesEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<LocalesEntity> lista = localesNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTL-872", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<OficinaEntity> buscarTablaOficina(int entidadID) throws Exception {
        try {

            // buscar solo los activos
            OficinaEntity filter = new OficinaEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<OficinaEntity> lista = oficinaNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTO-246", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ParametroEntity> buscarTablaParametro() throws Exception {
        try {

            // buscar solo los activos
            ParametroEntity filter = new ParametroEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<ParametroEntity> lista = parametroNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTP-378", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<UbigeoEntity> buscarTablaUbigeo() throws Exception {
        try {

            // buscar solo los activos
            UbigeoEntity filter = new UbigeoEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<UbigeoEntity> lista = ubigeoNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTU-125", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<UsuarioEntity> buscarUsuario(int entidadID) throws Exception {
        try {

            // buscar solo los activos
            UsuarioEntity filter = new UsuarioEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);

            List<UsuarioEntity> lista = usuarioNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTU-534", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<InventarioEntity> buscarTablaInventario(int entidadID, int inventarioID) throws Exception {
        try {

            // buscar solo los activos
            InventarioEntity filter = new InventarioEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);
            filter.setInventarioID(inventarioID); // solo el inventario que le corresponde

            List<InventarioEntity> lista = inventarioNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTI-887", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<InventarioBienEntity> buscarTablaInventarioBien(int entidadID, int inventarioID, String inventariador) throws Exception {
        try {

            // buscar solo los activos
            InventarioBienEntity filter = new InventarioBienEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);
            filter.setInventarioID(inventarioID); // solo del inventario que le corresponde
            filter.setInventariador(inventariador); // solo los bienes que se le han asignado a inventariadorID

            List<InventarioBienEntity> lista = inventarioBienNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTI-775", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<InventarioBienEntity> buscarTablaInventarioBien(int entidadID, int inventarioID) throws Exception {
        try {

            // buscar solo los activos
            InventarioBienEntity filter = new InventarioBienEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);
            filter.setInventarioID(inventarioID); // solo del inventario que le corresponde

            List<InventarioBienEntity> lista = inventarioBienNegocio.select(filter);

            return lista;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTI-775", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<InventarioAperturaEntity> buscarTablaInventarioApertura(int entidadID, int inventarioID, String inventariador) throws Exception {
        try {

            // buscar solo los activos
            InventarioAperturaEntity filter = new InventarioAperturaEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);
            filter.setInventarioID(inventarioID); // solo del inventario que le corresponde

            List<InventarioAperturaEntity> listaApertura = inventarioAperturaNegocio.select(filter);

            if (CollectionUtils.isNotEmpty(listaApertura)) {

                // buscar la lista de inventario_bien, los bienes asignados a ese inventariador para solo retornar esos bienes
                List<InventarioBienEntity> listaInventarioBien = buscarTablaInventarioBien(entidadID, inventarioID, inventariador);

                if (CollectionUtils.isNotEmpty(listaInventarioBien)) {

                    List<InventarioAperturaEntity> listaFinal = new ArrayList<>(listaInventarioBien.size());

                    for (InventarioAperturaEntity itemApertura : listaApertura) {
                        if (itemApertura == null) continue;

                        if (existeEnListaPorBienID(itemApertura, listaInventarioBien)) {
                            listaFinal.add(itemApertura);
                        }
                    }

                    // actualizar lista filtrada
                    listaApertura = listaFinal;
                }
            }

            return listaApertura;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTI-334", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<InventarioAperturaEntity> buscarTablaInventarioApertura(int entidadID, int inventarioID) throws Exception {
        try {

            // buscar solo los activos
            InventarioAperturaEntity filter = new InventarioAperturaEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setEntidadID(entidadID);
            filter.setInventarioID(inventarioID); // solo del inventario que le corresponde

            List<InventarioAperturaEntity> listaApertura = inventarioAperturaNegocio.select(filter);

            if (CollectionUtils.isNotEmpty(listaApertura)) {

                // buscar la lista de inventario_bien, los bienes asignados a ese inventariador para solo retornar esos bienes
                List<InventarioBienEntity> listaInventarioBien = buscarTablaInventarioBien(entidadID, inventarioID);

                if (CollectionUtils.isNotEmpty(listaInventarioBien)) {

                    List<InventarioAperturaEntity> listaFinal = new ArrayList<>(listaInventarioBien.size());

                    for (InventarioAperturaEntity itemApertura : listaApertura) {
                        if (itemApertura == null) continue;

                        if (existeEnListaPorBienID(itemApertura, listaInventarioBien)) {
                            listaFinal.add(itemApertura);
                        }
                    }

                    // actualizar lista filtrada
                    listaApertura = listaFinal;
                }
            }

            return listaApertura;

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-BTI-334", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    private boolean existeEnListaPorBienID(InventarioAperturaEntity inventarioApertura, List<InventarioBienEntity> listaInventarioBien) {

        if (inventarioApertura == null || CollectionUtils.isEmpty(listaInventarioBien)) return false;

        for (InventarioBienEntity inventarioBien : listaInventarioBien) {

            if (inventarioBien == null) continue;

            // si se refiere al mismo bienID (NOTA: revisar si necesita comparar tambien contra codigoPatrimonial)
            if (EntityUtil.tienenMismoBienID(inventarioBien, inventarioApertura)) {
                return true;
            }
        }

        return false;
    }

}
