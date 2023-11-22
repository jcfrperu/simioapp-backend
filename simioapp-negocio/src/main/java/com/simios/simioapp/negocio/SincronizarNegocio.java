package com.simios.simioapp.negocio;

import com.jcfr.utiles.DateTime;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.InventarioBienDAO;
import com.simios.simioapp.dominio.entidades.BienEntity;
import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
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
import java.util.List;

@Component("sincronizarNegocio")
public class SincronizarNegocio extends BaseNegocio {

    @Autowired
    private InventarioBienDAO inventarioBienDAO;

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("syncUtil")
    private SyncUtil syncUtil;


    @Transactional(rollbackFor = Throwable.class)
    public void sincronizarMonoUsuario(List<InventarioBienSync> inventarioBienList, Credencial credencial) throws Exception {

        if (CollectionUtils.isEmpty(inventarioBienList)) {
            throw new IllegalArgumentException("No hay bienes para sincronizar");
        }

        if (credencial == null) {
            throw new IllegalArgumentException("credencial no puede ser null o vacía");
        }

        List<InventarioBienSync> bienesSync = filtrarBienesValidosParaSincronizar(inventarioBienList);

        if (CollectionUtils.isEmpty(bienesSync)) {
            throw new IllegalArgumentException("No hay bienes listos para sincronizar");
        }

        DateTime fechaNow = DateTime.getNow();

        // TODO/FIXME: POR LO PRONTO LOS MARCA TODOS COMO SUBIDO Y GUARDA TODOS LOS DEMAS VALORES EN FUNCION DE SI FALTA O SI HAY QUE ELIMINAR

        // buscar los bienes (se debe trae ACTIVOS E INACTIVOS)
        InventarioBienEntity inventarioBienFilter = new InventarioBienEntity();
        inventarioBienFilter.setEntidadID(Integer.parseInt(credencial.getEntidad()));
        inventarioBienFilter.setInventarioID(Integer.parseInt(credencial.getInventario()));
        List<InventarioBienEntity> inventarioBienes = inventarioBienDAO.select(inventarioBienFilter);

        // manejas los bienes offline marcados como sobrantes
        List<InventarioBienSync> bienesSobrantesSync = filtrarBienesSobrantesEnLaTomaSync(bienesSync);
        if (CollectionUtils.isNotEmpty(bienesSobrantesSync)) {

            for (InventarioBienSync bienSobranteSync : bienesSobrantesSync) {

                InventarioBienEntity inventarioBienToSobrante = new InventarioBienEntity();
                EntityUtil.copiarInventarioBien(bienSobranteSync, inventarioBienToSobrante);

                // marcar como subido, como sobrante, y activo
                inventarioBienToSobrante.setEstadoSubida(catalogo.ESTADO_SUBIDA_SUBIDO);
                inventarioBienToSobrante.setSobranteFaltante(catalogo.SOBRANTE_FALTANTE_SOBRANTE);
                inventarioBienToSobrante.setIndDel(Constantes.REGISTRO_ACTIVO);

                // si el bien no existe en la tabla bien, tons normal se inserta como activo, sino seria caso raro
                // TODO/FIXME: POSIBLEMENTE ESTA BUSQUEDA SEA CAMBIADA, O SE USE UNA ADICIONAL POR bienID
                InventarioBienEntity found = buscarInventarioBienEnListaDeInventarioBienes(inventarioBienToSobrante, inventarioBienes);
                if (found == null) {

                    // TODO/FIXME: AQUI POSIBLEMENTE SE TENGA QUE BUSCAR POR SU INVENTARIOBIENID SI LO TIENE

                    // campos auditoria
                    inventarioBienToSobrante.setFechaReg(fechaNow.toDate());
                    inventarioBienToSobrante.setFechaAct(fechaNow.toDate());
                    inventarioBienToSobrante.setUsuReg(credencial.getUsuario());
                    inventarioBienToSobrante.setUsuAct(credencial.getUsuario());

                    inventarioBienDAO.insert(inventarioBienToSobrante);

                } else {
                    // CASO RARO, pero igual se actualiza

                    // campos auditoria
                    inventarioBienToSobrante.setUsuReg(credencial.getUsuario());
                    inventarioBienToSobrante.setUsuAct(credencial.getUsuario());

                    // TODO/FIXME: validar si el inventarioBienID viene correcto o no
                    if (inventarioBienToSobrante.getInventarioBienID() != null) {
                        updateInventarioBien(inventarioBienToSobrante);
                    }

                }
            }
        }

        // manejas los bienes offline marcados como faltantes
        List<InventarioBienSync> bienesFaltantesSync = filtrarBienesFaltantesEnLaTomaSync(bienesSync);
        if (CollectionUtils.isNotEmpty(bienesFaltantesSync)) {

            for (InventarioBienSync bienFaltanteSync : bienesFaltantesSync) {

                // NOTA: se prefiere crear una nueva instancia de bien para eliminar en vez de usar la misma
                InventarioBienEntity inventarioBienToFaltante = new InventarioBienEntity();
                EntityUtil.copiarInventarioBien(bienFaltanteSync, inventarioBienToFaltante);

                // campos auditoria
                inventarioBienToFaltante.setFechaAct(fechaNow.toDate());
                inventarioBienToFaltante.setUsuAct(credencial.getUsuario());

                // marcar como subido, faltante y activo
                inventarioBienToFaltante.setEstadoSubida(catalogo.ESTADO_SUBIDA_SUBIDO);
                inventarioBienToFaltante.setSobranteFaltante(catalogo.SOBRANTE_FALTANTE_FALTANTE);
                inventarioBienToFaltante.setIndDel(Constantes.REGISTRO_ACTIVO);

                // si el bien existe en la tabla bien se actualiza como un delete, sino seria un caso raro
                InventarioBienEntity found = buscarInventarioBienEnListaDeInventarioBienes(inventarioBienToFaltante, inventarioBienes);
                if (found != null) {
                    // NOTA: aqui no hay problema porque tiene inventarioBienID
                    updateInventarioBien(inventarioBienToFaltante);
                }
            }
        }

        // ACTUALIZAR LOS BIENES MODIFICADOS EN LA TOMA DE INVENTARIO (sobrantes o traslados: en verdad,
        // como estamos en modo mono-inventariados, nunca se detectaran traslados en este punto)
        List<InventarioBienSync> bienesModificadosEnToma = filtrarBienesSobrantesOTrasladosEnLaTomaSync(bienesSync);
        if (CollectionUtils.isNotEmpty(bienesModificadosEnToma)) {

            for (InventarioBienSync bienModificadoEnTomaSync : bienesModificadosEnToma) {

                InventarioBienEntity inventarioBienToUpdate = new InventarioBienEntity();
                EntityUtil.copiarInventarioBien(bienModificadoEnTomaSync, inventarioBienToUpdate);

                // campos auditoria
                inventarioBienToUpdate.setFechaAct(fechaNow.toDate());
                inventarioBienToUpdate.setUsuAct(credencial.getUsuario());

                // marcar como subido
                // se fuerza que se copie el valor como traslado o cadena vacia (no null, porque null no hara que cambie el valor en el update)
                inventarioBienToUpdate.setEstadoSubida(catalogo.ESTADO_SUBIDA_SUBIDO);

                if (syncUtil.isSobrante(bienModificadoEnTomaSync)) {
                    inventarioBienToUpdate.setSobranteFaltante(catalogo.SOBRANTE_FALTANTE_FALTANTE);
                }

                if (StringUtils.isBlank(bienModificadoEnTomaSync.getSobranteFaltante())) {
                    inventarioBienToUpdate.setSobranteFaltante(StringUtils.EMPTY);
                }

                inventarioBienToUpdate.setIndDel(Constantes.REGISTRO_ACTIVO);

                // si el bien existe en la tabla se actualiza , sin oseria un caso raro
                InventarioBienEntity found = buscarInventarioBienEnListaDeInventarioBienes(inventarioBienToUpdate, inventarioBienes);
                if (found != null) {
                    // NOTA: aqui no hay problema porque tiene inventarioBienID
                    updateInventarioBien(inventarioBienToUpdate);
                }
            }
        }
    }

    private void updateInventarioBien(InventarioBienEntity inventarioBien) throws Exception {
        if ( inventarioBien != null ) {
            // si viene con inventarioID lo usa para actualizar
            if ( inventarioBien.getInventarioBienID() != null ) {
                // actualizar por inventarioBienID (no actualiza el codigo patrimonial y la entidadID que venga en inventarioBien)
                inventarioBienDAO.updateNullByInventarioBienID(inventarioBien);
            } else if ( StringUtils.isNotBlank(inventarioBien.getCodigoPatrimonial()) && inventarioBien.getEntidadID() != null ) {
                // actualizar por inventarioBienID (no actualiza el inventarioBien a null que viene en inventarioBien)
                inventarioBienDAO.updateNullByInventarioBienID(inventarioBien);
            }
        }
    }

    private List<InventarioBienSync> filtrarBienesSobrantesEnLaTomaSync(List<InventarioBienSync> inventarioBienList) {

        // son todos los inventario_bien con el campo bien_id como null, estado_sobrante = S y indel NO BORRADOS (estado_subido = S implicito)
        // NOTA IMPORTANTE: los casos contrarios se estan validando en el controller

        List<InventarioBienSync> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(inventarioBienList)) {
            for (InventarioBienSync inventarioBien : inventarioBienList) {

                // solo deja pasar los registros activos
                if (inventarioBien == null || !syncUtil.isActivo(inventarioBien)) continue;

//                // TODO/FIXME: puede que este getBienID() null no vaya
//                if (inventarioBien.getBienID() == null && syncUtil.isSobrante(inventarioBien)) {
//                    result.add(inventarioBien);
//                }

                if (syncUtil.isSobrante(inventarioBien)) {
                    result.add(inventarioBien);
                }
            }
        }

        return result;
    }

    private List<InventarioBienSync> filtrarBienesFaltantesEnLaTomaSync(List<InventarioBienSync> inventarioBienList) {

        // son todos los inventario_bien con el campo bien_id != null, estado_sobrante = F y indel NO BORRADOS (estado_subido = S implicito)
        // NOTA IMPORTANTE: los casos contrarios se estan validando en el controller

        List<InventarioBienSync> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(inventarioBienList)) {
            for (InventarioBienSync inventarioBien : inventarioBienList) {

                // solo deja pasar los registros activos
                if (inventarioBien == null || !syncUtil.isActivo(inventarioBien)) continue;

                // NOTA: asegura la existencia de bienID e inventarioBienID
                if (inventarioBien.getBienID() != null && inventarioBien.getInventarioBienID() != null && syncUtil.isFaltaSubir(inventarioBien)) {
                    result.add(inventarioBien);
                }

            }
        }

        return result;
    }

    private List<InventarioBienSync> filtrarBienesSobrantesOTrasladosEnLaTomaSync(List<InventarioBienSync> inventarioBienList) {

        // son todos los inventario_bien con el campo bien_id != null, estado_sobrante como null o T y indel NO BORRADOS (estado_subido = S implicito)
        // NOTA IMPORTANTE: los casos contrarios se estan validando en el controller

        List<InventarioBienSync> result = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(inventarioBienList)) {
            for (InventarioBienSync inventarioBien : inventarioBienList) {

                // solo deja pasar los registros activos
                if (inventarioBien == null || !syncUtil.isActivo(inventarioBien)) continue;

                boolean vacio = StringUtils.isBlank(inventarioBien.getSobranteFaltante());
                boolean traslado = syncUtil.isTraslado(inventarioBien.getSobranteFaltante());

                // NOTA: asegura la existencia de bienID e inventarioBienID
                if (inventarioBien.getBienID() != null && inventarioBien.getInventarioBienID() != null && (vacio || traslado)) {
                    result.add(inventarioBien);
                }
            }
        }

        return result;
    }


    private InventarioBienEntity buscarInventarioBienEnListaDeInventarioBienes(BienEntity bienBuscar, List<InventarioBienEntity> inventarioBienList) {

        // busca por su codigo patrimonial (si bienList trae activos o inactivos depende de quien lo llama)
        if (CollectionUtils.isNotEmpty(inventarioBienList)) {
            for (InventarioBienEntity bien : inventarioBienList) {
                if (bien != null && EntityUtil.tienenMismoCodigoPatrimonialUnique(bienBuscar, bien)) {
                    return bien;
                }
            }
        }

        return null;
    }

    private List<InventarioBienSync> filtrarBienesValidosParaSincronizar(List<InventarioBienSync> inventarioBienLista) throws Exception {

        if (inventarioBienLista == null) throw new IllegalArgumentException("bienes cannot be null");

        List<InventarioBienSync> result = new ArrayList<>();

        for (InventarioBienSync inventarioBien : inventarioBienLista) {

            if (inventarioBien == null) continue;

            // TODO/FIXME: DESDE EL OFFLINE DEBE VENIR ACTIVO O INACTIVO, ESTA QUE VIENE NULL. REVISAR PORQUE
            // POR LO PRONTO SE LE FUERZA A ACTIVO
            if ( inventarioBien != null ) {
                inventarioBien.setIndDel(Constantes.REGISTRO_ACTIVO);
            }

            // TODO/FIXME: por lo pronto SOLO CONSIDERA LOS QUE FALTAN SUBIR Y LOS QUE YA HAN SIDO INVENTARIADOS
            if (syncUtil.isFaltaSubir(inventarioBien) && syncUtil.isInventariado(inventarioBien) && syncUtil.isRevisadoTomaOK(inventarioBien)) {
                result.add(inventarioBien);
            }
        }

        return result;
    }
}
