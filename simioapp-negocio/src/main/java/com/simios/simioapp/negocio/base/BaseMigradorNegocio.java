package com.simios.simioapp.negocio.base;

import com.simios.simioapp.dao.*;
import com.simios.simioapp.dominio.entidades.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseMigradorNegocio extends BaseNegocio {

    @Autowired
    protected EntidadDAO entidadDAO;

    @Autowired
    protected AreaDAO areaDAO;

    @Autowired
    protected LocalesDAO localesDAO;

    @Autowired
    protected EmpleadoDAO empleadoDAO;

    @Autowired
    protected OficinaDAO oficinaDAO;

    @Autowired
    protected BienDAO bienDAO;

    @Autowired
    protected CatalogoBienDAO catalogoBienDAO;

    protected Integer buscarCatalogoBienIDByUnique(String codigoBien) throws Exception {

        if (StringUtils.isBlank(codigoBien)) {
            return null;
        }

        // buscamos por sus campos unique
        CatalogoBienEntity filter = new CatalogoBienEntity();

        filter.setCodigoBien(codigoBien);

        List<CatalogoBienEntity> results = catalogoBienDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0) == null ? null : results.get(0).getCatalogoBienID();
        }

        return null;
    }

    protected CatalogoBienEntity buscarCatalogoBienByID(Integer catalogoBienID) throws Exception {

        if (catalogoBienID == null) {
            return null;
        }

        // buscamos por sus campos unique
        CatalogoBienEntity filter = new CatalogoBienEntity();

        filter.setCatalogoBienID(catalogoBienID);

        List<CatalogoBienEntity> results = catalogoBienDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0);
        }

        return null;
    }

    protected Integer buscarEmpleadoIDByUnique(String codigo, Integer entidadID) throws Exception {

        if (StringUtils.isBlank(codigo) || entidadID == null) {
            return null;
        }


        // buscamos por sus campos unique
        EmpleadoEntity filter = new EmpleadoEntity();

        filter.setCodigo(codigo);
        filter.setEntidadID(entidadID);

        List<EmpleadoEntity> results = empleadoDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0) == null ? null : results.get(0).getEmpleadoID();
        }

        return null;
    }

    protected Integer buscarOficinaIDByUnique(String abreviaturaOficina, Integer entidadID, Integer localesID, Integer areaID) throws Exception {

        if (StringUtils.isBlank(abreviaturaOficina) || entidadID == null || localesID == null || areaID == null) {
            return null;
        }

        // buscamos por sus campos unique
        OficinaEntity filter = new OficinaEntity();

        filter.setAbreviaturaOficina(abreviaturaOficina);
        filter.setEntidadID(entidadID);
        filter.setLocalesID(localesID);
        filter.setAreaID(areaID);

        List<OficinaEntity> results = oficinaDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0) == null ? null : results.get(0).getOficinaID();
        }

        return null;
    }

    protected Integer buscarAreaIDByUnique(String abreviaturaArea, Integer entidadID, Integer localesID) throws Exception {

        if (StringUtils.isBlank(abreviaturaArea) || entidadID == null || localesID == null) {
            return null;
        }

        // buscamos por sus campos unique
        AreaEntity filter = new AreaEntity();

        filter.setAbreviaturaArea(abreviaturaArea);
        filter.setEntidadID(entidadID);
        filter.setLocalesID(localesID);

        List<AreaEntity> results = areaDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0) == null ? null : results.get(0).getAreaID();
        }

        return null;
    }

    protected Integer buscarLocalesIDByUnique(Integer entidadID, String codigo) throws Exception {

        if (entidadID == null || StringUtils.isBlank(codigo)) return null;

        // buscamos por sus campos unique
        LocalesEntity filter = new LocalesEntity();

        filter.setCodigo(codigo);
        filter.setEntidadID(entidadID);

        List<LocalesEntity> results = localesDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0) == null ? null : results.get(0).getLocalesID();
        }

        return null;
    }

    protected Integer buscarEntityIDByUnique(String codigoEntidad) throws Exception {

        if (StringUtils.isBlank(codigoEntidad)) return null;

        // buscamos por sus campos unique
        EntidadEntity filter = new EntidadEntity();

        filter.setCodigoEntidad(codigoEntidad);

        List<EntidadEntity> results = entidadDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0) == null ? null : results.get(0).getEntidadID();
        }

        return null;
    }

    protected Integer buscarEntityIDByRUC(String rucEntidad) throws Exception {

        if (StringUtils.isBlank(rucEntidad)) return null;

        // buscamos por sus campos unique
        EntidadEntity filter = new EntidadEntity();

        filter.setRucEntidad(rucEntidad);

        List<EntidadEntity> results = entidadDAO.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0) == null ? null : results.get(0).getEntidadID();
        }

        return null;
    }

    protected String getStringValue(Object value) {

        return value == null ? null : JS.toNullIf(String.valueOf(value));
    }


}
