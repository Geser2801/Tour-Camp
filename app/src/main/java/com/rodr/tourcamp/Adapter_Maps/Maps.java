package com.rodr.tourcamp.Adapter_Maps;

public class Maps {

    // DATOS A VISUALIZAR EN EL RECYCLER...
    private int imgMaps;
    private String txtMaps;
    private String txtMapsDescripcion;

    // METODO CONSTRUCTOR...
    public Maps(int imgMaps, String txtMaps, String txtMapsDescripcion) {
        this.imgMaps = imgMaps;
        this.txtMaps = txtMaps;
        this.txtMapsDescripcion = txtMapsDescripcion;
    }

    // METODOS GETTERS Y SETTERS...
    public int getImgMaps() {
        return imgMaps;
    }

    public String getTxtMaps() {
        return txtMaps;
    }

    public String getTxtMapsDescripcion() {
        return txtMapsDescripcion;
    }

    public void setImgMaps(int imgMaps) {
        this.imgMaps = imgMaps;
    }

    public void setTxtMaps(String txtMaps) {
        this.txtMaps = txtMaps;
    }

    public void setTxtMapsDescripcion(String txtMapsDescripcion) {
        this.txtMapsDescripcion = txtMapsDescripcion;
    }
}
