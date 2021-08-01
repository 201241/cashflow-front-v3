package sample.model;

import javafx.beans.property.*;

public class Semanas {
    private final IntegerProperty numeroSemana = new SimpleIntegerProperty();
    private final DoubleProperty monto = new SimpleDoubleProperty();
    private final StringProperty mes =new SimpleStringProperty();
    private final StringProperty tipo =new SimpleStringProperty();

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

    public Semanas(Integer numerosemana, Double monto, String mes, String tipo){
        this.numeroSemana.set(numerosemana);
        this.monto.set(monto);
        this.mes.set(mes);
        this.tipo.set(tipo);
    }
}
