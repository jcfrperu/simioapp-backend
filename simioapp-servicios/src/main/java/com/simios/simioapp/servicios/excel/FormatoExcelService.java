package com.simios.simioapp.servicios.excel;

public interface FormatoExcelService {

    byte[] generarFormatoExcelV6InventarioBien(int int_inventarioID) throws Exception;

    byte[] generarFormatoExcelV6InventarioApertura(int int_inventarioID) throws Exception;

    byte[] generarFormatoExcelV7InventarioBien(int int_inventarioID) throws Exception;

    byte[] generarFormatoExcelV7InventarioApertura(int int_inventarioID) throws Exception;
}
