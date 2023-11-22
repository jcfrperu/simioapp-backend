package com.simios.simioapp.comunes.utiles;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.string.JSUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ListaUtil {

    private static JSUtil JS = JSUtil.JSUtil;

    public static List asegurarNotNull(List lista) {

        if (lista == null) return new ArrayList();

        return lista;
    }

    public static List<ListaItem> filtrarComboID(List<ListaItem> fuente, Long... filtrosID) {

        List<ListaItem> result = new ArrayList<ListaItem>();

        if (CollectionUtils.isEmpty(fuente) || filtrosID == null || filtrosID.length == 0) {
            return result;
        }

        for (ListaItem itemFuente : fuente) {
            for (Long filtroID : filtrosID) {
                if (itemFuente == null && filtroID == null) {
                    continue;
                }

                if (StringUtils.equals(itemFuente.getId(), String.valueOf(filtroID))) {
                    result.add(itemFuente);
                }
            }
        }

        return result;
    }

    public static List<Integer> parseToNumberList(ArrayList<String> inventarioBienIDList) {

        ArrayList<Integer> resultList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(inventarioBienIDList)) {
            for (String id : inventarioBienIDList) {
                if (JS._numeroEntero(id)) {
                    resultList.add(JS.toInt(id));
                }
            }
        }

        return resultList;
    }

}
