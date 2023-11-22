package com.simios.simioapp.negocio;

import com.jcfr.utiles.DateTime;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.BienDAO;
import com.simios.simioapp.dao.InventarioAperturaDAO;
import com.simios.simioapp.dao.InventarioBienDAO;
import com.simios.simioapp.dao.InventarioDAO;
import com.simios.simioapp.dominio.entidades.BienEntity;
import com.simios.simioapp.dominio.entidades.InventarioAperturaEntity;
import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.dominio.entidades.sync.InventarioBienSync;
import com.simios.simioapp.dominio.utiles.EntityUtil;
import com.simios.simioapp.negocio.base.BaseNegocio;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.negocio.utiles.SyncUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("inventarioNegocio")
public class InventarioNegocio extends BaseNegocio {

    @Autowired
    private InventarioDAO inventarioDAO;

    @Autowired
    private BienDAO bienDAO;

    @Autowired
    private InventarioBienDAO inventarioBienDAO;

    @Autowired
    private InventarioAperturaDAO inventarioAperturaDAO;

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("syncUtil")
    private SyncUtil syncUtil;

    public InventarioEntity selectByID(Integer inventarioID) throws Exception {
        InventarioEntity inventario = new InventarioEntity();

        inventario.setInventarioID(inventarioID);

        return inventarioDAO.selectByID(inventario);
    }

    public List<InventarioEntity> select(InventarioEntity filter) throws Exception {

        return inventarioDAO.select(filter);
    }

    public List<InventarioEntity> selectByMap(Map<String, Object> params) throws Exception {

        return inventarioDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return inventarioDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void insert(InventarioEntity entity) throws Exception {

        inventarioDAO.insert(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void update(InventarioEntity entity) throws Exception {

        inventarioDAO.update(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(InventarioEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        inventarioDAO.update(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateFechasAperturaCierre(InventarioEntity entity) throws Exception {

        inventarioDAO.updateFechasAperturaCierre(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void aperturarInventario(InventarioEntity entity, UsuarioSession usuarioSession) throws Exception {

        if (entity == null) throw new IllegalArgumentException("entity cannot be null");
        if (usuarioSession == null) throw new IllegalArgumentException("usuarioSession cannot be null");
        if (usuarioSession.getUsuarioSessionID() == null) {
            throw new IllegalArgumentException("usuarioSession.usuarioSessionID cannot be null");
        }

        inventarioDAO.insert(entity);

        BienEntity filter = new BienEntity();
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        List<BienEntity> bienes = bienDAO.select(filter);

        // no se puede aperturar si no hay bienes
        if (CollectionUtils.isEmpty(bienes)) {
            throw new Exception("Deben existir bienes para aperturar un inventario");
        }

        Date now = DateTime.getNow().toDate();

        // solo se pasan los bienes no borrados
        for (BienEntity bien : bienes) {

            // InventarioBienEntity inventarioBien = mapper.map(bien, InventarioBienEntity.class);

            // guardar una copia en inventario_bien
            InventarioBienEntity inventarioBien = new InventarioBienEntity();

            EntityUtil.copiarBien(bien, inventarioBien);

            inventarioBien.setInventarioID(entity.getInventarioID());
            inventarioBien.setEstadoSubida(catalogo.ESTADO_SUBIDA_FALTA_SUBIR);
            inventarioBien.setSobranteFaltante(null);
            inventarioBien.setInventariador(null);
            inventarioBien.setInventariadorAnterior(null);

            // otros campos que en inventario_bien son not null (no revisado por default)
            inventarioBien.setRevisadoToma(catalogo.REVISADO_TOMA_FALTA_INVENTARIAR);

            // campos auditoria
            inventarioBien.setFechaReg(now);
            inventarioBien.setFechaAct(now);
            inventarioBien.setUsuReg(usuarioSession.getUsuarioSessionID());
            inventarioBien.setUsuAct(usuarioSession.getUsuarioSessionID());

            inventarioBienDAO.insert(inventarioBien);

            // guardar una copia en inventario_apertura  crearInventarioAperturaDesde
            InventarioAperturaEntity inventarioApertura = new InventarioAperturaEntity();

            EntityUtil.copiarBien(bien, inventarioApertura);

            inventarioApertura.setInventarioID(entity.getInventarioID());
            inventarioApertura.setEstadoSubida(catalogo.ESTADO_SUBIDA_FALTA_SUBIR);
            inventarioApertura.setSobranteFaltante(null);
            inventarioApertura.setInventariador(null);
            inventarioApertura.setInventariadorAnterior(null);

            // campos auditoria
            inventarioApertura.setFechaReg(now);
            inventarioApertura.setFechaAct(now);
            inventarioApertura.setUsuReg(usuarioSession.getUsuarioSessionID());
            inventarioApertura.setUsuAct(usuarioSession.getUsuarioSessionID());

            // otros campos que en inventario_bien son not null (no revisado por default)
            inventarioApertura.setRevisadoToma(catalogo.REVISADO_TOMA_FALTA_INVENTARIAR);

            inventarioAperturaDAO.insert(inventarioApertura);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void cerrarInventario(InventarioEntity entity) throws Exception {

        // NOTA: En el cierre de inventario la tabla inventario_apertura no se toca, queda como una imagen antes de hacer el inventario (backup antes de la catastrofe)
        //       La idea es pasar todo lo inventariado (tabla inventario_bien) a la lista de bienes (tabla bien), ya sea insertar nuevos, eliminar y actualizar datos de los que ya existian.
        if (entity == null) throw new IllegalArgumentException("entity cannot be null");
        if (entity.getInventarioID() == null) throw new IllegalArgumentException("entity.inventarioID cannot be null");

        // actualizar tabla inventario
        inventarioDAO.update(entity);

        // actualizar tabla bien
        // (tabla inventario_apertura no se toca, queda como imagen de la tabla bien al aperturar inventario)

        // buscar los bienes (se debe trae ACTIVOS E INACTIVOS)
        BienEntity bienFilter = new BienEntity();
        bienFilter.setEntidadID(entity.getEntidadID());
        List<BienEntity> bienes = bienDAO.select(bienFilter);

        // buscar los bienes de la toma de inventario
        InventarioBienEntity filter = new InventarioBienEntity();
        filter.setEntidadID(entity.getEntidadID());
        filter.setInventarioID(entity.getInventarioID());
        filter.setIndDel(Constantes.REGISTRO_ACTIVO); // NOTA: IMPORTANTE, solo los activos se pasan a actualizar a la tabla bien
        List<InventarioBienEntity> inventarioBienList = inventarioBienDAO.select(filter);

        // INSERTAR BIENES ENCONTRADOS EN LA TOMA DE INVENTARIO (Y QUE NO ESTABAN EN LA TABLA BIEN)
        List<InventarioBienEntity> bienesNuevosEnToma = filtrarBienesNuevosEncontradosEnLaToma(inventarioBienList);
        if (CollectionUtils.isNotEmpty(bienesNuevosEnToma)) {

            for (InventarioBienEntity bienNuevoEnToma : bienesNuevosEnToma) {

                BienEntity bienToInsert = new BienEntity();
                EntityUtil.copiarBien(bienNuevoEnToma, bienToInsert);

                // campos auditoria
                bienToInsert.setFechaReg(entity.getFechaAct());
                bienToInsert.setFechaAct(entity.getFechaAct());
                bienToInsert.setUsuReg(entity.getUsuAct());
                bienToInsert.setUsuAct(entity.getUsuAct());

                // si el bien no existe en la tabla bien, tons normal se inserta como activo, sino seria caso raro
                boolean existe = buscarBienEnListaDeBienes(bienToInsert, bienes) != null;
                if (!existe) {
                    bienDAO.insert(bienToInsert);
                }
            }
        }

        // ELIMINAR BIENES NO ENCONTRADOS EN LA TOMA DE INVENTARIO (Y QUE ORIGINALMENTE SI ESTABAN EN LA TABLA BIEN)
        List<InventarioBienEntity> bienesEliminadosEnToma = filtrarBienesEliminadosEnLaToma(inventarioBienList);
        if (CollectionUtils.isNotEmpty(bienesEliminadosEnToma)) {

            for (InventarioBienEntity bienEliminadoEnToma : bienesEliminadosEnToma) {

                // NOTA: se prefiere crear una nueva instancia de bien para eliminar en vez de usar la misma
                BienEntity bienToDelete = new BienEntity();
                EntityUtil.copiarBien(bienEliminadoEnToma, bienToDelete);

                // eliminar
                bienToDelete.setIndDel(Constantes.REGISTRO_INACTIVO);

                // campos auditoria
                bienToDelete.setFechaAct(entity.getFechaAct());
                bienToDelete.setUsuAct(entity.getUsuAct());

                // si el bien existe en la tabla bien se actualiza como un delete, sino seria un caso raro
                boolean existe = buscarBienEnListaDeBienes(bienToDelete, bienes) != null;
                if (existe) {
                    bienDAO.update(bienToDelete);
                }
            }
        }

        // ACTUALIZAR LOS BIENES MODIFICADOS EN LA TOMA DE INVENTARIO
        List<InventarioBienEntity> bienesModificadosEnToma = filtrarBienesModificadosEnLaToma(inventarioBienList);
        if (CollectionUtils.isNotEmpty(bienesModificadosEnToma)) {

            for (InventarioBienEntity bienModificadoEnToma : bienesModificadosEnToma) {

                BienEntity bienToUpdate = new BienEntity();
                EntityUtil.copiarBien(bienModificadoEnToma, bienToUpdate);

                // campos auditoria
                bienToUpdate.setFechaAct(entity.getFechaAct());
                bienToUpdate.setUsuAct(entity.getUsuAct());

                // si el bien existe en la tabla se actualiza , sin oseria un caso raro
                boolean existe = buscarBienEnListaDeBienes(bienToUpdate, bienes) != null;
                if (existe) {
                    bienDAO.update(bienToUpdate);
                }
            }
        }

        // NOTA IMPORTANTE: el caso extraño de los bienes que estan en la tabla bien y por algun motivo no estan en
        //                  la tabla inventario_bien no se esta analizando, porque en el cierre de inventario de valida.
    }

    private List<InventarioBienEntity> filtrarBienesNuevosEncontradosEnLaToma(List<InventarioBienEntity> inventarioBienList) {

        // son todos los inventario_bien con el campo bien_id como null, estado_sobrante = S y indel NO BORRADOS (estado_subido = S implicito)
        // NOTA IMPORTANTE: los casos contrarios se estan validando en el controller

        List<InventarioBienEntity> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(inventarioBienList)) {
            for (InventarioBienEntity inventarioBien : inventarioBienList) {

                // solo deja pasar los registros activos
                if (inventarioBien == null || !syncUtil.isActivo(inventarioBien)) continue;

                if (inventarioBien.getBienID() == null && syncUtil.isSobrante(inventarioBien)) {
                    result.add(inventarioBien);
                }
            }
        }

        return result;
    }

    private List<InventarioBienEntity> filtrarBienesEliminadosEnLaToma(List<InventarioBienEntity> inventarioBienList) {

        // son todos los inventario_bien con el campo bien_id != null, estado_sobrante = F y indel NO BORRADOS (estado_subido = S implicito)
        // NOTA IMPORTANTE: los casos contrarios se estan validando en el controller

        List<InventarioBienEntity> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(inventarioBienList)) {
            for (InventarioBienEntity inventarioBien : inventarioBienList) {

                // solo deja pasar los registros activos
                if (inventarioBien == null || !syncUtil.isActivo(inventarioBien)) continue;

                if (inventarioBien.getBienID() != null && syncUtil.isFaltaSubir(inventarioBien)) {
                    result.add(inventarioBien);
                }
            }
        }

        return result;
    }

    private List<InventarioBienEntity> filtrarBienesModificadosEnLaToma(List<InventarioBienEntity> inventarioBienList) {

        // son todos los inventario_bien con el campo bien_id != null, estado_sobrante como null o T y indel NO BORRADOS (estado_subido = S implicito)
        // NOTA IMPORTANTE: los casos contrarios se estan validando en el controller

        List<InventarioBienEntity> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(inventarioBienList)) {
            for (InventarioBienEntity inventarioBien : inventarioBienList) {

                // solo deja pasar los registros activos
                if (inventarioBien == null || !syncUtil.isActivo(inventarioBien)) continue;

                boolean vacio = StringUtils.isBlank(inventarioBien.getSobranteFaltante());
                boolean traslado = syncUtil.isTraslado(inventarioBien.getSobranteFaltante());

                if (inventarioBien.getBienID() != null && (vacio || traslado)) {
                    result.add(inventarioBien);
                }
            }
        }

        return result;
    }

    private BienEntity buscarBienEnListaDeBienes(BienEntity bienBuscar, List<BienEntity> bienList) {

        // busca por su codigo patrimonial (si bienList trae activos o inactivos depende de quien lo llama)
        if (CollectionUtils.isNotEmpty(bienList)) {
            for (BienEntity bien : bienList) {
                if (bien != null && EntityUtil.tienenMismoCodigoPatrimonialUnique(bienBuscar, bien)) {
                    return bien;
                }
            }
        }

        return null;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deleteConBienes(InventarioEntity entity) throws Exception {

        // borra la tabla inventario, y todos los bienes en inventario_bien e inventario_apertura

        if (entity == null) throw new IllegalArgumentException("entity cannot be null");
        if (entity.getInventarioID() == null) throw new IllegalArgumentException("entity.inventarioID cannot be null");

        // borra la tabla inventario
        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        inventarioDAO.update(entity);

        // buscar sus inventario_bien para borrarlos
        InventarioBienEntity inventarioBienFilter = new InventarioBienEntity();
        inventarioBienFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
        inventarioBienFilter.setInventarioID(entity.getInventarioID());
        List<InventarioBienEntity> inventarioBienes = inventarioBienDAO.select(inventarioBienFilter);

        if (CollectionUtils.isNotEmpty(inventarioBienes)) {
            for (InventarioBienEntity item : inventarioBienes) {
                item.setIndDel(Constantes.REGISTRO_INACTIVO);
                inventarioBienDAO.update(item);
            }
        }

        // buscar sus inventario_apertura para borrarlos
        InventarioAperturaEntity inventarioAperturaFilter = new InventarioAperturaEntity();
        inventarioAperturaFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
        inventarioAperturaFilter.setInventarioID(entity.getInventarioID());
        List<InventarioAperturaEntity> inventarioAperturaBienes = inventarioAperturaDAO.select(inventarioAperturaFilter);

        if (CollectionUtils.isNotEmpty(inventarioAperturaBienes)) {
            for (InventarioAperturaEntity item : inventarioAperturaBienes) {
                item.setIndDel(Constantes.REGISTRO_INACTIVO);
                inventarioAperturaDAO.update(item);
            }
        }

    }
}
