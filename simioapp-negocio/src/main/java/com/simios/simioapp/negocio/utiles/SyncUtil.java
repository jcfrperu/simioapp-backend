package com.simios.simioapp.negocio.utiles;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("syncUtil")
public class SyncUtil {

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    // SUBIDO, FALTA SUBIR -> ESTADO_SUBIDA
    public boolean isEstadoSubidaOK(String estadoSubida) {
        return isSubido(estadoSubida) || isFaltaSubir(estadoSubida);
    }

    public boolean isEstadoSubidaOK(InventarioBienEntity inventarioBien) {
        return isSubido(inventarioBien) || isFaltaSubir(inventarioBien);
    }

    public boolean isSubido(String estadoSubida) {
        return StringUtils.trimToEmpty(estadoSubida).equals(catalogo.ESTADO_SUBIDA_SUBIDO);
    }

    public boolean isSubido(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isSubido(inventarioBien.getEstadoSubida());
    }

    public boolean isFaltaSubir(String estadoSubida) {
        return StringUtils.trimToEmpty(estadoSubida).equals(catalogo.ESTADO_SUBIDA_FALTA_SUBIR);
    }

    public boolean isFaltaSubir(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isFaltaSubir(inventarioBien.getEstadoSubida());
    }

    // INVENTARIADO, FALTA INVENTARIAR -> REVISADO_TOMA
    public boolean isRevisadoTomaOK(String revisadoToma) {
        return isInventariado(revisadoToma) || isFaltaInventariar(revisadoToma);
    }

    public boolean isRevisadoTomaOK(InventarioBienEntity inventarioBien) {
        return isInventariado(inventarioBien) || isFaltaInventariar(inventarioBien);
    }

    public boolean isInventariado(String revisadoToma) {
        return StringUtils.trimToEmpty(revisadoToma).equals(catalogo.REVISADO_TOMA_INVENTARIADO);
    }

    public boolean isInventariado(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isInventariado(inventarioBien.getRevisadoToma());
    }

    public boolean isFaltaInventariar(String revisadoToma) {
        return StringUtils.trimToEmpty(revisadoToma).equals(catalogo.REVISADO_TOMA_FALTA_INVENTARIAR);
    }

    public boolean isFaltaInventariar(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isFaltaInventariar(inventarioBien.getRevisadoToma());
    }

    // SOBRANTE, FALTANTE, TRALASDO -> SOBRANTE_FALTANTE
    public boolean isSobranteFaltanteOK(String sobranteFaltante) {
        return isSobrante(sobranteFaltante) || isFaltante(sobranteFaltante) || isTraslado(sobranteFaltante);
    }

    public boolean isSobranteFaltanteOK(InventarioBienEntity inventarioBien) {
        return isSobrante(inventarioBien) || isFaltante(inventarioBien) || isTraslado(inventarioBien) || StringUtils.isBlank(inventarioBien.getSobranteFaltante());
    }

    public boolean isSobrante(String sobranteFaltante) {
        return StringUtils.trimToEmpty(sobranteFaltante).equals(catalogo.SOBRANTE_FALTANTE_SOBRANTE);
    }

    public boolean isSobrante(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isSobrante(inventarioBien.getSobranteFaltante());
    }

    public boolean isFaltante(String sobranteFaltante) {
        return StringUtils.trimToEmpty(sobranteFaltante).equals(catalogo.SOBRANTE_FALTANTE_FALTANTE);
    }

    public boolean isFaltante(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isFaltante(inventarioBien.getSobranteFaltante());
    }

    public boolean isTraslado(String sobranteFaltante) {
        return StringUtils.trimToEmpty(sobranteFaltante).equals(catalogo.SOBRANTE_FALTANTE_TRASLADO);
    }

    public boolean isTraslado(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isTraslado(inventarioBien.getSobranteFaltante());
    }

    public boolean isActivo(String indDel) {
        return StringUtils.trimToEmpty(indDel).equals(Constantes.REGISTRO_ACTIVO);
    }

    public boolean isActivo(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isActivo(inventarioBien.getIndDel());
    }

    public boolean isInactivo(String indDel) {
        return StringUtils.trimToEmpty(indDel).equals(Constantes.REGISTRO_INACTIVO);
    }

    public boolean isInactivo(InventarioBienEntity inventarioBien) {
        return inventarioBien != null && isInactivo(inventarioBien.getIndDel());
    }

    public boolean isActivoOK(String indDel) {
        return isActivo(indDel) || isInactivo(indDel);
    }

    public boolean isActivoOK(InventarioBienEntity inventarioBien) {
        return isActivo(inventarioBien) || isInactivo(inventarioBien);
    }
}
