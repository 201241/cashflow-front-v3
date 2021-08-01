package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categoria {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty clasificacion = new SimpleStringProperty();
    private final StringProperty categoria = new SimpleStringProperty();
    private final StringProperty subcategoria = new SimpleStringProperty();

    public Categoria(String id, String clasificacion, String categoria, String subcategoria)
    {
        this.id.set(id);
        this.clasificacion.set(clasificacion);
        this.categoria.set(categoria);
        this.subcategoria.set(subcategoria);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getClasificacion() {
        return clasificacion.get();
    }

    public StringProperty clasificacionProperty() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion.set(clasificacion);
    }

    public String getCategoria() {
        return categoria.get();
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public String getSubcategoria() {
        return subcategoria.get();
    }

    public StringProperty subcategoriaProperty() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria.set(subcategoria);
    }
}
