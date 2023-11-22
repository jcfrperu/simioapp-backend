package com.simios.simioapp.negocio.migracion;

import com.jcfr.utiles.DateTime;
import com.linuxense.javadbf.DBFReader;
import com.simios.simioapp.comunes.beans.ErrorLineaMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.JSONUtil;
import com.simios.simioapp.dao.EmpleadoUbicacionDAO;
import com.simios.simioapp.dominio.entidades.EmpleadoEntity;
import com.simios.simioapp.dominio.entidades.EmpleadoUbicacionEntity;
import com.simios.simioapp.negocio.base.BaseMigradorNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("migradorEmpleadoNegocio")
public class MigradorEmpleadoNegocio extends BaseMigradorNegocio {

    // campos principales en el DBF (que relacionan con los unique key en la BD)
    private static final int DBF_CODINST = 0;
    private static final int DBF_CODIGO = 1;
    private static final int DBF_CODLOCAL = 4;
    private static final int DBF_CODAREA = 5;

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    protected EmpleadoUbicacionDAO empleadoUbicacionDAO;

    @Transactional(rollbackFor=Throwable.class)
    public ResultadoMigracionBean migrarDBFEmpleados(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) throws IOException {

        Date now = DateTime.getNow().toDate();

        // lista de errores
        ArrayList<ErrorLineaMigracionBean> listaErrores = new ArrayList<>();

        // contadores de filas
        int nroFilasMigradas = 0;
        int nroFilasNoMigradas = 0;
        int nroFila = 0;

        // variables para la lectura de cada registro
        DBFReader reader = new DBFReader(inputStreamDBF);

        // campos de cada registro DBF
        Object[] rowObjects;
        while ((rowObjects = reader.nextRecord()) != null) {

            try {

                nroFila++;

                EmpleadoEntity empleadoInsert = new EmpleadoEntity();

                Integer entityID = null;
                Integer localesID = null;
                Integer areaID = null;

                for (int i = 0; i < rowObjects.length; i++) {

                    final String valueObject = getStringValue(rowObjects[i]);

                    switch (i) {
                        case DBF_CODINST:
                            // CODINST -> entidad_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODINST del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(valueObject);

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            if (entityID == null) {
                                throw new Exception("Campo CODINST del DBF no existe en la tabla entidad de la BD -> " + valueObject);
                            }

                            if (entityID.intValue() != entidadIDMigracion.intValue()) {
                                throw new Exception("Código de entidades no coinciden. EntidadID DBF: " + entityID + ", EntidadID Migracion: " + entidadIDMigracion.intValue());
                            }

                            empleadoInsert.setEntidadID(entityID);
                            break;
                        case DBF_CODIGO:
                            // CODIGO -> codigo
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODIGO del DBF esta empty");
                            }

                            empleadoInsert.setCodigo(valueObject);
                            break;
                        case 2:
                            // NOMBRES -> nombres
                            empleadoInsert.setNombres(valueObject);
                            break;
                        case 3:
                            // APELLIDOS -> apellidos
                            empleadoInsert.setApellidos(valueObject);
                            break;
                        case DBF_CODLOCAL:
                            // CODLOCAL -> locales_id

                            // NOTA; GUARDANDO EL LOCAL PARA INSERTAR EN LA TABLA EMPLEADO_UBICACION
                            localesID = null;
                            if (StringUtils.isNotBlank(valueObject)) {
                                entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                                // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                                if (forzarUsarEntidadDeMigracion) {
                                    entityID = entidadIDMigracion;
                                }

                                localesID = buscarLocalesIDByUnique(entityID, valueObject);
                            }
                            break;

                        case DBF_CODAREA:
                            // SIGLAS -> area_id

                            // NOTA; GUARDANDO EL LOCAL PARA INSERTAR EN LA TABLA EMPLEADO_UBICACION
                            areaID = null;
                            if (StringUtils.isNotBlank(valueObject)) {

                                // NOTA: los metodos buscarXXXIDByUnique ya estan validando que sus entradas no sean null
                                entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                                // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                                if (forzarUsarEntidadDeMigracion) {
                                    entityID = entidadIDMigracion;
                                }

                                localesID = buscarLocalesIDByUnique(entityID, getStringValue(rowObjects[DBF_CODLOCAL]));
                                areaID = buscarAreaIDByUnique(valueObject, entityID, localesID);
                            }

                            break;
                        case 6:
                            // DOCIDENT -> tipo_doc_identidad (se deja libre a que no exista este dato)
                            empleadoInsert.setTipoDocIdentidad(valueObject);
                            break;
                        case 7:
                            // NRODOC -> nro_doc_ident_personal (se deja libre a que no exista este dato)
                            empleadoInsert.setNumeroDocIdentPersonal(valueObject);
                            break;
                        case 8:
                            // MODCONT -> modalidad_contrato
                            empleadoInsert.setModalidadContrato(valueObject);
                            break;

                    }


                }

                // seteando campos agregados solo para el simio
                if (StringUtils.isNotBlank(empleadoInsert.getApellidos())) {

                    String apellidos = empleadoInsert.getApellidos();

                    // busca el primer espacio
                    int pos = apellidos.indexOf(" ");

                    if (pos > 0) {
                        // si hay espacio, separa: primera palabra + todo lo demas que venga despues
                        empleadoInsert.setApellidoPaterno(JS.toNullIf(StringUtils.substring(apellidos, 0, pos)));
                        empleadoInsert.setApellidoMaterno(JS.toNullIf(StringUtils.substring(apellidos, pos + 1)));
                    } else {
                        // si no hay espacio, lo pone todo en apellido paterno
                        empleadoInsert.setApellidoPaterno(apellidos);
                    }
                }


                // validar que no se haya insertado previamente en la BD MySQL
                if (existeEmpleadoByUnique(empleadoInsert)) {
                    throw new Exception("Empleado ya fue insertado en BD -> " + JSONUtil.toJSON(empleadoInsert, false));
                }

                // seteando campos de adicionales de auditoria
                empleadoInsert.setIndDel(Constantes.REGISTRO_ACTIVO);
                empleadoInsert.setUsuAct(usuarioSession.getUsuarioSessionID());
                empleadoInsert.setUsuReg(usuarioSession.getUsuarioSessionID());
                empleadoInsert.setFechaAct(now);
                empleadoInsert.setFechaReg(now);

                // insertar en la BD MySQL
                empleadoDAO.insert(empleadoInsert);

                // insertar su ubicacion si esta ya no ha sido insertada (empleado no llega a nivel de oficina por eso se envia null)
                if (!yaFueRegistradaUbicacion(empleadoInsert.getEmpleadoID(), entityID, localesID, areaID, null)) {
                    insertarEmpleadoUbicacion(empleadoInsert.getEmpleadoID(), entityID, localesID, areaID, usuarioSession.getUsuarioSessionID(), now);
                }

                nroFilasMigradas++;

            } catch (Exception sos) {

                nroFilasNoMigradas++;
                listaErrores.add(new ErrorLineaMigracionBean(String.valueOf(nroFila), sos.getMessage()));
            }

        }

        // By now, we have itereated through all of the rows
        inputStreamDBF.close();

        // resultado de la migracion
        ResultadoMigracionBean result = new ResultadoMigracionBean();

        result.setListaErrores(listaErrores);
        result.setNombreTabla("empleado");
        result.setNroFilasEncontradas(nroFila);
        result.setNroFilasMigradas(nroFilasMigradas);
        result.setNroFilasNoMigradas(nroFilasNoMigradas);
        result.setOcurrioError(nroFilasNoMigradas > 0);
        result.setMsgError(nroFilasNoMigradas > 0 ? "No se migraron varias filas. Por favor revisar" : StringUtils.EMPTY);

        return result;
    }

    private void insertarEmpleadoUbicacion(Integer empleadoID, Integer entityID, Integer localesID, Integer areaID, String usuario, Date now) throws Exception {
        EmpleadoUbicacionEntity empleadoUbicacionEntity = new EmpleadoUbicacionEntity();

        empleadoUbicacionEntity.setEmpleadoID(empleadoID);
        empleadoUbicacionEntity.setEntidadID(entityID);
        empleadoUbicacionEntity.setLocalesID(localesID);
        empleadoUbicacionEntity.setAreaID(areaID);
        empleadoUbicacionEntity.setOficinaID(null);
        empleadoUbicacionEntity.setTipoAsociacion(catalogo.UBICACION_EMPLEADO_ASOCIACION_NINGUNA);


        // campos de auditoria
        empleadoUbicacionEntity.setIndDel(Constantes.REGISTRO_ACTIVO);
        empleadoUbicacionEntity.setUsuAct(usuario);
        empleadoUbicacionEntity.setUsuReg(usuario);
        empleadoUbicacionEntity.setFechaAct(now);
        empleadoUbicacionEntity.setFechaReg(now);

        empleadoUbicacionDAO.insert(empleadoUbicacionEntity);
    }

    private boolean yaFueRegistradaUbicacion(Integer empleadoId, Integer entidadId, Integer localesId, Integer areaId, Integer oficinaId) throws Exception {

        EmpleadoUbicacionEntity filtro = new EmpleadoUbicacionEntity();

        // empleadoId y entidadId son not null
        filtro.setEmpleadoID(empleadoId);
        filtro.setEntidadID(entidadId);

        // localesId, areaId y oficinaId son opcionales
        filtro.setLocalesID(localesId);
        filtro.setAreaID(areaId);
        filtro.setOficinaID(oficinaId);

        List<EmpleadoUbicacionEntity> ubicaciones = empleadoUbicacionDAO.select(filtro);

        if (CollectionUtils.isNotEmpty(ubicaciones)) {
            for (EmpleadoUbicacionEntity ubicacion : ubicaciones) {

                // si son "iguales" en cada uno de los campos (considerando nulabilidad)
                if (equalsNulls(ubicacion.getEmpleadoID(), empleadoId) &&
                        equalsNulls(ubicacion.getEntidadID(), entidadId) &&
                        equalsNulls(ubicacion.getLocalesID(), localesId) &&
                        equalsNulls(ubicacion.getAreaID(), areaId) &&
                        equalsNulls(ubicacion.getOficinaID(), oficinaId)) {

                    return true;
                }
            }

        }

        return false;
    }

    private boolean equalsNulls(Integer valor01, Integer valor02) {
        if (valor01 == null) {
            return valor02 == null ? true : valor02.equals(valor01);
        }
        return valor02 == null ? false : valor02.equals(valor01);
    }

    private boolean existeEmpleadoByUnique(EmpleadoEntity entity) throws Exception {

        if (entity == null) new IllegalAccessException("entity must be not null");

        // buscamos por sus campos unique
        EmpleadoEntity filter = new EmpleadoEntity();

        filter.setCodigo(entity.getCodigo());
        filter.setEntidadID(entity.getEntidadID());

        List<EmpleadoEntity> results = empleadoDAO.select(filter);

        return CollectionUtils.isNotEmpty(results);
    }

}
