package com.simios.simioapp.comunes.beans;

public class UploadParams {

    public static final int KB = 1024;
    public static final int MB = 1024 * KB;

    // tamanio maximo (en bytes) del archivo adjunto
    private long maxFileSize;

    // cantidad de bytes usados para guardar en RAM el archivo adjunto (si
    // supera se serializa en disco)
    private int maxMemSize;

    // ruta de la carpeta temporal donde se guardaran los archivos que superan
    // el maxMemSize
    private String rutaUploadTemp;

    // ruta de la carpeta donde se guardaran los archivos adjuntos
    // programaticamente
    private String rutaUploadFiles;

    public UploadParams(String rutaUploadTemp, String rutaUploadFiles) {
        this(2 * MB, 500 * KB, rutaUploadTemp, rutaUploadFiles);
    }

    public UploadParams(long maxFileSize, String rutaUploadTemp, String rutaUploadFiles) {
        this(maxFileSize, 500 * KB, rutaUploadTemp, rutaUploadFiles);
    }

    public UploadParams(long maxFileSize, int maxMemSize, String rutaUploadTemp, String rutaUploadFiles) {
        this.maxFileSize = maxFileSize;
        this.maxMemSize = maxMemSize;
        this.rutaUploadTemp = rutaUploadTemp;
        this.rutaUploadFiles = rutaUploadFiles;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public int getMaxMemSize() {
        return maxMemSize;
    }

    public void setMaxMemSize(int maxMemSize) {
        this.maxMemSize = maxMemSize;
    }

    public String getRutaUploadTemp() {
        return rutaUploadTemp;
    }

    public void setRutaUploadTemp(String rutaUploadTemp) {
        this.rutaUploadTemp = rutaUploadTemp;
    }

    public String getRutaUploadFiles() {
        return rutaUploadFiles;
    }

    public void setRutaUploadFiles(String rutaUploadFiles) {
        this.rutaUploadFiles = rutaUploadFiles;
    }

}
