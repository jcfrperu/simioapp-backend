package com.simios.simioapp.negocio.migracion;

import com.jcfr.utiles.DateTime;
import com.linuxense.javadbf.DBFReader;
import com.simios.simioapp.comunes.beans.ErrorLineaMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.JSONUtil;
import com.simios.simioapp.dominio.entidades.OficinaEntity;
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

@Component("migradorOficinaNegocio")
public class MigradorOficinaNegocio extends BaseMigradorNegocio {

    // campos principales en el DBF (que relacionan con los unique key en la BD)
    private static final int DBF_CODINST = 0;
    private static final int DBF_CODLOCAL = 1;
    private static final int DBF_CODAREA = 2;
    private static final int DBF_CODUBICACI = 3;

    @Transactional(rollbackFor = Throwable.class)
    public ResultadoMigracionBean migrarDBFOficinas(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) throws IOException {

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

                OficinaEntity oficinaInsert = new OficinaEntity();

                for (int i = 0; i < rowObjects.length; i++) {

                    final String valueObject = getStringValue(rowObjects[i]);

                    Integer entityID = null;
                    Integer localesID = null;

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

                            oficinaInsert.setEntidadID(entityID);
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

                            localesID = buscarLocalesIDByUnique(entityID, valueObject);
                            if (localesID == null) {
                                throw new Exception("Campos CODINST Y CODLOCAL del DBF no hacen match en la tabla locales de la BD -> " + entityID + ", " + valueObject);
                            }

                            oficinaInsert.setLocalesID(localesID);
                            break;
                        case DBF_CODAREA:
                            // SIGLAS -> area_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODAREA del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            localesID = buscarLocalesIDByUnique(entityID, getStringValue(rowObjects[DBF_CODLOCAL]));

                            Integer areaID = buscarAreaIDByUnique(valueObject, entityID, localesID);
                            if (areaID == null) {
                                throw new Exception("Campos CODINST, CODLOCAL Y CODAREA del DBF no hacen match en la tabla area de la BD -> " + entityID + ", " + localesID + ", " + valueObject);
                            }

                            oficinaInsert.setAreaID(areaID);
                            break;
                        case DBF_CODUBICACI:
                            // CODUBICACI -> abreviatura_oficina
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODUBICACI del DBF esta empty");
                            }

                            oficinaInsert.setAbreviaturaOficina(valueObject);

                            break;
                    }
                }

                // seteando campos agregados solo para el simio
                oficinaInsert.setNombreOficina(oficinaInsert.getAbreviaturaOficina()); // toma su abreviatura como nombre
                oficinaInsert.setPisoOficina(null);

                // validar que no se haya insertado previamente en la BD MySQL
                if (existeOficinaByUnique(oficinaInsert)) {
                    throw new Exception("Oficina ya fue insertada en BD -> " + JSONUtil.toJSON(oficinaInsert, false));
                }

                // seteando campos de adicionales de auditoria
                oficinaInsert.setIndDel(Constantes.REGISTRO_ACTIVO);
                oficinaInsert.setUsuAct(usuarioSession.getUsuarioSessionID());
                oficinaInsert.setUsuReg(usuarioSession.getUsuarioSessionID());
                oficinaInsert.setFechaAct(now);
                oficinaInsert.setFechaReg(now);

                // insertar en la BD MySQL
                oficinaDAO.insert(oficinaInsert);

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
        result.setNombreTabla("oficina");
        result.setNroFilasEncontradas(nroFila);
        result.setNroFilasMigradas(nroFilasMigradas);
        result.setNroFilasNoMigradas(nroFilasNoMigradas);
        result.setOcurrioError(nroFilasNoMigradas > 0);
        result.setMsgError(nroFilasNoMigradas > 0 ? "No se migraron varias filas. Por favor revisar" : StringUtils.EMPTY);

        return result;
    }

    private boolean existeOficinaByUnique(OficinaEntity entity) throws Exception {

        if (entity == null) new IllegalAccessException("entity must be not null");

        // buscamos por sus campos unique
        OficinaEntity filter = new OficinaEntity();

        filter.setAbreviaturaOficina(entity.getAbreviaturaOficina());
        filter.setEntidadID(entity.getEntidadID());
        filter.setLocalesID(entity.getLocalesID());
        filter.setAreaID(entity.getAreaID());

        List<OficinaEntity> results = oficinaDAO.select(filter);

        return CollectionUtils.isNotEmpty(results);
    }


}
