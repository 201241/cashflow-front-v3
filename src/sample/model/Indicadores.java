package sample.model;



import javafx.beans.property.*;

public class Indicadores {

    private final StringProperty tipoCuenta = new SimpleStringProperty();
    private final IntegerProperty noSemana = new SimpleIntegerProperty();
    private final StringProperty razonSocial = new SimpleStringProperty();
    private final StringProperty idIndicador = new SimpleStringProperty();
    private final DoubleProperty monto = new SimpleDoubleProperty();
    private String fecha;
    private final StringProperty mes = new SimpleStringProperty();

    Indicadores(String tipoCuenta, Integer noSemana, String razonSocial, Double monto, String idIndicador,String fecha)
    {
        this.tipoCuenta.set(tipoCuenta);
        this.noSemana.set(noSemana);
        this.razonSocial.set(razonSocial);
        this.monto.set(monto);
        this.idIndicador.set(idIndicador);
        this.fecha = fecha;
    }

    Indicadores(String tipoCuenta, Integer noSemana, String razonSocial, Double monto, String idIndicador,String fecha, String mes)
    {
        this.tipoCuenta.set(tipoCuenta);
        this.noSemana.set(noSemana);
        this.razonSocial.set(razonSocial);
        this.monto.set(monto);
        this.idIndicador.set(idIndicador);
        this.fecha = fecha;
        this.mes.set(mes);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoCuenta() {
        return tipoCuenta.get();
    }

    public StringProperty tipoCuentaProperty() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta.set(tipoCuenta);
    }

    public Integer getNoSemana() {
        return noSemana.get();
    }

    public IntegerProperty noSemanaProperty() {
        return noSemana;
    }

    public void setNoSemana(Integer noSemana) {
        this.noSemana.set(noSemana);
    }

    public String getRazonSocial() {
        return razonSocial.get();
    }

    public StringProperty razonSocialProperty() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial.set(razonSocial);
    }

    public String getIdIndicador() {
        return idIndicador.get();
    }

    public StringProperty idIndicadorProperty() {
        return idIndicador;
    }

    public void setIdIndicador(String idIndicador) {
        this.idIndicador.set(idIndicador);
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

    public void setNoSemana(int noSemana) {
        this.noSemana.set(noSemana);
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
}
