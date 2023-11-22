package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.comparators.ComboComparatorByLabel;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.EmpleadoDAO;
import com.simios.simioapp.dominio.entidades.EmpleadoEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("empleadoNegocio")
public class EmpleadoNegocio extends BaseNegocio {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    public EmpleadoEntity selectByID(Integer empleadoID) throws Exception {
        EmpleadoEntity empleado = new EmpleadoEntity();

        empleado.setEmpleadoID(empleadoID);

        return empleadoDAO.selectByID(empleado);
    }

    public List<EmpleadoEntity> select(EmpleadoEntity filter) throws Exception {

        return empleadoDAO.select(filter);
    }

    public List<EmpleadoEntity> selectByMap(Map<String, Object> params) throws Exception {

        return empleadoDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return empleadoDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(EmpleadoEntity entity) throws Exception {

        empleadoDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(EmpleadoEntity entity) throws Exception {

        empleadoDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(EmpleadoEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        empleadoDAO.update(entity);
    }

    public List<ListaItem> selectEmpleadoCombo(EmpleadoEntity filter) throws Exception {

        List<ListaItem> itemsCombo = new ArrayList<>();

        List<EmpleadoEntity> empleados = empleadoDAO.select(filter);

        if (CollectionUtils.isNotEmpty(empleados)) {

            for (EmpleadoEntity empleado : empleados) {

                ListaItem item = new ListaItem();
                item.setId(String.valueOf(empleado.getEmpleadoID()));
                item.setLabel(StringUtils.upperCase(empleado.getApellidos()) + " " + StringUtils.upperCase(empleado.getNombres()));
                itemsCombo.add(item);
            }

            Collections.sort(itemsCombo, new ComboComparatorByLabel());
        }

        return itemsCombo;
    }

    public Map<Integer, EmpleadoEntity> selectKeyObject(EmpleadoEntity filter) throws Exception {

        List<EmpleadoEntity> entityList = empleadoDAO.select(filter);

        HashMap<Integer, EmpleadoEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (EmpleadoEntity entity : entityList) {
                keyObjectMap.put(entity.getEmpleadoID(), entity);
            }
        }

        return keyObjectMap;
    }
}
