
package sample.model;

import javafx.beans.property.*;

public class Semana {
    private String id;
    private final StringProperty descripcion = new SimpleStringProperty();
    private final IntegerProperty numeroSemana = new SimpleIntegerProperty();
    private final DoubleProperty monto = new SimpleDoubleProperty();
    private final StringProperty mes =new SimpleStringProperty();
    private final StringProperty tipo =new SimpleStringProperty();
    private Categoria categoria;

    public Semana(String id,String descripcion, Integer numerosemana, Double monto, String mes, String tipo, Categoria categoria){
        this.id = id;
        this.descripcion.set(descripcion);
        this.numeroSemana.set(numerosemana);
        this.monto.set(monto);
        this.mes.set(mes);
        this.tipo.set(tipo);
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public int getNumeroSemana() {
        return numeroSemana.get();
    }

    public IntegerProperty numeroSemanaProperty() {
        return numeroSemana;
    }

    public void setNumeroSemana(int numeroSemana) {
        this.numeroSemana.set(numeroSemana);
    }

    public double getMonto() {
        return monto.get();
    }

    public DoubleProperty montoProperty() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto.set(monto);
    }

    public String getMes() {
        return mes.get();
    }

    public StringProperty mesProperty() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes.set(mes);
    }

    public String getTipo() {
        return tipo.get();
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
