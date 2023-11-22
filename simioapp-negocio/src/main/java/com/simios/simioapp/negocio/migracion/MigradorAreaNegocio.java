package com.simios.simioapp.negocio.migracion;

import com.jcfr.utiles.DateTime;
import com.linuxense.javadbf.DBFReader;
import com.simios.simioapp.comunes.beans.ErrorLineaMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.JSONUtil;
import com.simios.simioapp.dominio.entidades.AreaEntity;
import com.simios.simioapp.negocio.base.BaseMigradorNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("migradorAreaNegocio")
public class MigradorAreaNegocio extends BaseMigradorNegocio {

    // campos principales en el DBF (que relacionan con los unique key en la BD)
    private static final int DBF_CODINST = 0;
    private static final int DBF_CODLOCAL = 1;
    private static final int DBF_SIGLAS = 2;

    @Transactional(rollbackFor=Throwable.class)
    public ResultadoMigracionBean migrarDBFAreas(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) throws IOException {

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

                AreaEntity areaInsert = new AreaEntity();

                for (int i = 0; i < rowObjects.length; i++) {

                    final String valueObject = getStringValue(rowObjects[i]);

                    Integer entityID = null;

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

                            areaInsert.setEntidadID(entityID);
                            break;
                        case DBF_CODLOCAL:
                            // CODLOCAL -> locales_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODLOCAL del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            Integer localesID = buscarLocalesIDByUnique(entityID, valueObject);

                            if (localesID == null) {
                                throw new Exception("Campos CODINST Y CODLOCAL del DBF no hacen match en la tabla locales de la BD -> " + entityID + ", " + valueObject);
                            }

                            areaInsert.setLocalesID(localesID);
                            break;
                        case DBF_SIGLAS:
                            // SIGLAS -> abreviatura_area
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo SIGLAS del DBF esta empty");
                            }

                            areaInsert.setAbreviaturaArea(valueObject);
                            break;
                        case 3:
                            // areas -> nombre_area
                            areaInsert.setNombreArea(valueObject);
                            break;
                    }

                }

                // validar que no se haya insertado previamente en la BD MySQL
                if (existeAreaByUnique(areaInsert)) {
                    throw new Exception("Área ya fue insertada en BD -> " + JSONUtil.toJSON(areaInsert, false));
                }

                // seteando campos de adicionales de auditoria
                areaInsert.setIndDel(Constantes.REGISTRO_ACTIVO);
                areaInsert.setUsuAct(usuarioSession.getUsuarioSessionID());
                areaInsert.setUsuReg(usuarioSession.getUsuarioSessionID());
                areaInsert.setFechaAct(now);
                areaInsert.setFechaReg(now);

                // insertar en la BD MySQL
                areaDAO.insert(areaInsert);

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
        result.setNombreTabla("area");
        result.setNroFilasEncontradas(nroFila);
        result.setNroFilasMigradas(nroFilasMigradas);
        result.setNroFilasNoMigradas(nroFilasNoMigradas);
        result.setOcurrioError(nroFilasNoMigradas > 0);
        result.setMsgError(nroFilasNoMigradas > 0 ? "No se migraron varias filas. Por favor revisar" : StringUtils.EMPTY);

        return result;
    }

    private boolean existeAreaByUnique(AreaEntity entity) throws Exception {

        if (entity == null) new IllegalAccessException("entity must be not null");

        // buscamos por sus campos unique
        AreaEntity filter = new AreaEntity();

        filter.setAbreviaturaArea(entity.getAbreviaturaArea());
        filter.setEntidadID(entity.getEntidadID());
        filter.setLocalesID(entity.getLocalesID());

        List<AreaEntity> results = areaDAO.select(filter);

        return CollectionUtils.isNotEmpty(results);
    }
}
