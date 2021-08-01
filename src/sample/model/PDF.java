package sample.model;

import javafx.beans.property.*;

import java.util.ArrayList;

public class PDF {

    private final StringProperty descripcion = new SimpleStringProperty();
    private ArrayList<Semanas> semanas;

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public ArrayList<Semanas> getSemanas() {
        return semanas;
    }

    public void setSemanas(ArrayList<Semanas> semanas) {
        this.semanas = semanas;
    }

    public PDF(String descripcion, ArrayList<Semanas> semanas){
        this.descripcion.set(descripcion);
        this.semanas = semanas;
    }
}
