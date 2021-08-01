package sample.Tablas;


public class CuentasCobrar {
    private String nombre;
    private String semana1;
    private String semana2;
    private String semana3;
    private String semana4;
    private String Final;

    public CuentasCobrar(String nombre, String semana1, String semana2, String semana3, String semana4, String Final) {
        this.nombre = nombre;
        this.semana1 = semana1;
        this.semana2 = semana2;
        this.semana3 = semana3;
        this.semana4 = semana4;
        this.Final = Final;
    }

    public CuentasCobrar() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSemana1() {
        return semana1;
    }

    public void setSemana1(String semana1) {
        this.semana1 = semana1;
    }

    public String getSemana2() {
        return semana2;
    }

    public void setSemana2(String semana2) {
        this.semana2 = semana2;
    }

    public String getSemana3() {
        return semana3;
    }

    public void setSemana3(String semana3) {
        this.semana3 = semana3;
    }

    public String getSemana4() {
        return semana4;
    }

    public void setSemana4(String semana4) {
        this.semana4 = semana4;
    }

    public String getFinal() {
        return Final;
    }

    public void setFinal(String aFinal) {
        Final = aFinal;
    }
}
