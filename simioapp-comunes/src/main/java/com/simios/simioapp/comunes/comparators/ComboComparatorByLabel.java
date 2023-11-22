package com.simios.simioapp.comunes.comparators;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.string.JSUtil;

import java.util.Comparator;

public class ComboComparatorByLabel implements Comparator<ListaItem> {

    private JSUtil JS = JSUtil.JSUtil;

    @Override
    public int compare(ListaItem o1, ListaItem o2) {

        if (o1 == null) {

            return o2 == null ? 0 : -1;

        } else {

            return o2 == null ? 1 : JS.toBlank(o1.getLabel()).compareTo(JS.toBlank(o2.getLabel()));
        }

    }
}
