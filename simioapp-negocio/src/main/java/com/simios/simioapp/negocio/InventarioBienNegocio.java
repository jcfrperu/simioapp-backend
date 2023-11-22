package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.InventarioBienDAO;
import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("inventarioBienNegocio")
public class InventarioBienNegocio extends BaseNegocio {

    @Autowired
    private InventarioBienDAO inventarioBienDAO;

    public InventarioBienEntity selectByID(Integer inventarioBienID) throws Exception {
        InventarioBienEntity inventarioBien = new InventarioBienEntity();

        inventarioBien.setInventarioBienID(inventarioBienID);

        return inventarioBienDAO.selectByID(inventarioBien);
    }

    public List<InventarioBienEntity> select(InventarioBienEntity filter) throws Exception {

        return inventarioBienDAO.select(filter);
    }

    public List<InventarioBienEntity> selectByMap(Map<String, Object> params) throws Exception {

        return inventarioBienDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return inventarioBienDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void insert(InventarioBienEntity entity) throws Exception {

        inventarioBienDAO.insert(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void update(InventarioBienEntity entity) throws Exception {

        inventarioBienDAO.update(entity);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(InventarioBienEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        inventarioBienDAO.update(entity);
    }

    private String buscarInventarioAsignado(Integer inventarioBienID) throws Exception {

        // buscar primero el inventarioBien para ver si su inventariador actual y ponerlo en inventariador anterior
        InventarioBienEntity inventariadorFilter = new InventarioBienEntity();
        inventariadorFilter.setInventarioBienID(inventarioBienID);

        List<InventarioBienEntity> inventariadorResult = inventarioBienDAO.select(inventariadorFilter);

        if (CollectionUtils.isNotEmpty(inventariadorResult)) {

            return StringUtils.trimToNull(inventariadorResult.get(0).getInventariador());
        }

        return null;
    }

    private String buscarInventarioAsignadoAnterior(Integer inventarioBienID) throws Exception {

        // buscar primero el inventarioBien para ver si su inventariador actual y ponerlo en inventariador anterior
        InventarioBienEntity inventariadorFilter = new InventarioBienEntity();
        inventariadorFilter.setInventarioBienID(inventarioBienID);

        List<InventarioBienEntity> inventariadorResult = inventarioBienDAO.select(inventariadorFilter);

        if (CollectionUtils.isNotEmpty(inventariadorResult)) {

            return StringUtils.trimToNull(inventariadorResult.get(0).getInventariadorAnterior());
        }

        return null;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void asignarBienes(List<Integer> inventarioBienIDList, String usuarioID) throws Exception {

        if (CollectionUtils.isNotEmpty(inventarioBienIDList)) {

            for (Integer inventarioBienID : inventarioBienIDList) {

                String inventariadorActual = buscarInventarioAsignado(inventarioBienID);

                // solo actualiza si son diferentes el inventariador actual con el nuevo valor
                if (!StringUtils.equals(inventariadorActual, usuarioID)) {

                    // inventario a actualizar
                    InventarioBienEntity inventarioBienUpdate = new InventarioBienEntity();

                    inventarioBienUpdate.setInventarioBienID(inventarioBienID);
                    inventarioBienUpdate.setInventariador(usuarioID);

                    if (StringUtils.isNotBlank(usuarioID) && StringUtils.isBlank(inventariadorActual)) {

                        // si se esta asignando un nuevo inventariador por primera vez (ie en BD esta en blanco),
                        // tons deberia mantener el inventariador anterior con el valor que estaba, no hay necesidad de blanquearlo
                        inventarioBienUpdate.setInventariadorAnterior(buscarInventarioAsignadoAnterior(inventarioBienID));

                    } else {
                        inventarioBienUpdate.setInventariadorAnterior(inventariadorActual);
                    }

                    inventarioBienDAO.updateAsignacion(inventarioBienUpdate);
                }
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void desasignarBienes(List<Integer> inventarioBienIDList) throws Exception {

        if (CollectionUtils.isNotEmpty(inventarioBienIDList)) {

            for (Integer inventarioBienID : inventarioBienIDList) {

                String inventariadorActual = buscarInventarioAsignado(inventarioBienID);

                // solo actualiza si el inventariador tenia valor (osea si se esta borrando)
                if (StringUtils.isNotBlank(inventariadorActual)) {

                    InventarioBienEntity inventarioBienUpdate = new InventarioBienEntity();

                    inventarioBienUpdate.setInventarioBienID(inventarioBienID);
                    inventarioBienUpdate.setInventariador(null); // nullear, desasignar
                    inventarioBienUpdate.setInventariadorAnterior(inventariadorActual);

                    inventarioBienDAO.updateAsignacion(inventarioBienUpdate);
                }
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void guardarSobranteFaltante(List<Integer> inventarioBienIDList, String sobranteFaltante) throws Exception {

        if (CollectionUtils.isNotEmpty(inventarioBienIDList)) {

            for (Integer inventarioBienID : inventarioBienIDList) {

                InventarioBienEntity inventarioBienUpdate = new InventarioBienEntity();

                inventarioBienUpdate.setInventarioBienID(inventarioBienID);
                inventarioBienUpdate.setSobranteFaltante(sobranteFaltante);

                inventarioBienDAO.updateSobranteFaltante(inventarioBienUpdate);
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void guardarEstadoSubida(List<Integer> inventarioBienIDList, String sobranteFaltante) throws Exception {

        if (CollectionUtils.isNotEmpty(inventarioBienIDList)) {

            for (Integer inventarioBienID : inventarioBienIDList) {

                InventarioBienEntity inventarioBienUpdate = new InventarioBienEntity();

                inventarioBienUpdate.setInventarioBienID(inventarioBienID);
                inventarioBienUpdate.setEstadoSubida(sobranteFaltante);

                inventarioBienDAO.updateEstadoSubida(inventarioBienUpdate);
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void guardarBajaDisposicion(List<Integer> inventarioBienIDList, String resolucionBaja, String fechaResolucionBaja, String causalBaja, String numDocSBN,
                                       String resolucionDisposicion, String fechaResolucionDisposicion, String actoDisposicion, String donatorio) throws Exception {

        if (CollectionUtils.isNotEmpty(inventarioBienIDList)) {

            for (Integer inventarioBienID : inventarioBienIDList) {

                InventarioBienEntity inventarioBienUpdate = new InventarioBienEntity();

                inventarioBienUpdate.setInventarioBienID(inventarioBienID);
                inventarioBienUpdate.setNumeroResolucionBaja(resolucionBaja);
                inventarioBienUpdate.setFechaBaja(null);//fechaResolucionBaja
                inventarioBienUpdate.setCausalBaja(causalBaja);
                inventarioBienUpdate.setDocBajaSbn(numDocSBN);

                inventarioBienUpdate.setResolDisp(resolucionDisposicion);
                inventarioBienUpdate.setFechaDisposicion(null);//fechaResolucionDisposicion
                inventarioBienUpdate.setActoDisposicionBien(actoDisposicion);
                inventarioBienUpdate.setEntidadBeneficiadaActoDisposicion(donatorio);


                inventarioBienDAO.updateBajaDisposicion(inventarioBienUpdate);
            }
        }
    }

}
