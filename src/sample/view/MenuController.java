package sample.view;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import javafx.util.StringConverter;
import sample.Tablas.CuentasCobrar;
import sample.model.Categoria;
import sample.model.Conexion;
import sample.model.FlujoEfectivo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MenuController {

    @FXML
    private Button btnflujo1;

    @FXML
    private Button buttoncategoria;
    @FXML
    private ProgressIndicator indicador;

    @FXML
    private AnchorPane windowCategoria;

    @FXML
    private AnchorPane windowcategoriasub;
    @FXML
    private TextField inputcategoria;

    @FXML
    private TextField inputsubcategoria;

    @FXML
    private Pane comboClasificacion;

    @FXML
    private ListView<String> listaclasificacion;

    @FXML
    private Polygon combotriangulo;

    @FXML
    private AnchorPane windowflujo;

    @FXML
    private AnchorPane windowindicadores;

    @FXML
    private AnchorPane GenerarReporteAnchor;

    @FXML
    private ComboBox<?> comboMeses;

    @FXML
    private AnchorPane windowindicadorsub;

    @FXML
    private Label lblfinanzas111;

    @FXML
    private Button cerrarInformacion;

    @FXML
    private AnchorPane windowpdf;

    @FXML
    private TableView<?> tablacobrarpdf;

    @FXML
    private TableColumn<?, ?> colrazonsocialpdf;

    @FXML
    private TableColumn<?, ?> colsem1;

    @FXML
    private TableColumn<?, ?> colsem2;

    @FXML
    private TableColumn<?, ?> colsem3;

    @FXML
    private TableColumn<?, ?> colsem4;

    @FXML
    private TableColumn<?, ?> colsem5;


    @FXML
    private TableView<Categoria> tablacategoria;

    @FXML
    private TableColumn<Categoria, String> colclasificacion;

    @FXML
    private TableColumn<Categoria, String> colcategoria;

    @FXML
    private TableColumn<Categoria, String> colsubcategoria;

    @FXML
    private TableView<FlujoEfectivo> tablaflujo;

    @FXML
    private TableColumn<FlujoEfectivo, String> colfechaflujo;

    @FXML
    private TableColumn<FlujoEfectivo, String> coldescripcionflujo;

    @FXML
    private TableColumn<FlujoEfectivo, String> colcategoriaflujo;

    @FXML
    private TableColumn<FlujoEfectivo, String> colsubcategoriaflujo;

    @FXML
    private TextField inputdescripcionflujo;

    @FXML
    private TextField inputcantidadflujo;

    @FXML
    private RadioButton check1flujo;

    @FXML
    private RadioButton check2flujo;

    @FXML
    private TextField inputnsemanaflujo;
    @FXML
    private ComboBox<Categoria> combocategoria;

    @FXML private ComboBox comboBoxClasificacion;

    String selecioncombo = "";
    String selectcombocategoria ="";
    ObservableList<Categoria> itemcategoriacombo = FXCollections.observableArrayList();

    Boolean auxwindowcombo = false;
    public void openWindowCategoria(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(800));
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1);
        fadeTransition.setNode(windowCategoria);
        windowCategoria.setVisible(true);
        fadeTransition.play();
        uploadDataTableCategory();
    }

    @FXML
    public void regresarAlMenu(){
        windowCategoria.setVisible(false);
    }

    public void uploadDataTableCategory(){
        Conexion conexion = new Conexion();
        ObservableList<Categoria> item = conexion.getCategoria();
        itemcategoriacombo = item;
        tablacategoria.setItems(item);
        colcategoria.setCellValueFactory(celldata -> celldata.getValue().categoriaProperty());
        colclasificacion.setCellValueFactory(celldata -> celldata.getValue().clasificacionProperty());
        colsubcategoria.setCellValueFactory(celldata -> celldata.getValue().subcategoriaProperty());
    }



    //flujo
    public void openWindowFlujo(){
        initComboCategoria();
        windowflujo.setVisible(true);
        uploadDataTableFlujo();
        check1flujo.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1 == true){
                check2flujo.setSelected(false);
            }
        });
        check2flujo.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1 ==true){
                check1flujo.setSelected(false);
            }
        });

    }

    public void cerrarVentanaFlujo(){
        windowflujo.setVisible(false);
    }

    public void guardarDatosFlujo(){

        if (check1flujo.isSelected() == true){

            System.out.println("entro aui");
            guardarDatosFlujoDB("entrada");
        }else if (check2flujo.isSelected() == true){
            System.out.println("enotro aqui");

            guardarDatosFlujoDB("salida");

        }
    }
    public void initComboCategoria(){
        Conexion conexion = new Conexion();
        itemcategoriacombo = conexion.getCategoria();
        combocategoria.setItems(itemcategoriacombo);
        combocategoria.valueProperty().addListener((observableValue, categoria, t1) -> {
            selectcombocategoria = t1.getId();

        });
        combocategoria.setConverter(new StringConverter<Categoria>() {
            @Override
            public String toString(Categoria categoria) {
                return categoria.getCategoria();
            }

            @Override
            public Categoria fromString(String s) {
                return null;
            }
        });
    }
    public void guardarDatosFlujoDB(String tipo){
        if ( selectcombocategoria.length()>0 &&inputdescripcionflujo.getText().length()>0 && inputcantidadflujo.getText().length()>0&& inputnsemanaflujo.getText().length()>0){
            Conexion conexion = new Conexion();
          String response = conexion.postFlujo(obtenerFecha(),tipo,inputdescripcionflujo.getText(),Double.parseDouble(inputcantidadflujo.getText()),selectcombocategoria,Integer.parseInt(inputnsemanaflujo.getText()));
            System.out.println(response);
            uploadDataTableFlujo();
        }
    }
    public String obtenerFecha(){
        Date fecha = new Date();
        String strDateFormat = "dd/MM/yyyy";
        SimpleDateFormat objfecha = new SimpleDateFormat(strDateFormat);
        return objfecha.format(fecha);
    }
   public void uploadDataTableFlujo(){
        Conexion conexion = new Conexion();
        tablaflujo.setItems(conexion.getFlujoEfectivo());
        colfechaflujo.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().fechaProperty());
        coldescripcionflujo.setCellValueFactory(celldata -> celldata.getValue().descripcionProperty());
        colcategoriaflujo.setCellValueFactory(celldata -> celldata.getValue().getCategoria().categoriaProperty());
        colsubcategoriaflujo.setCellValueFactory(celldata -> celldata.getValue().getCategoria().subcategoriaProperty());
    }
    public void openWindowIndicadores(){
        windowindicadores.setVisible(true);

    }
    public void openWindowindicadorsub(){
        windowindicadorsub.setVisible(true);
    }
    public void cerrarWindowindicadorsub(){
        windowindicadorsub.setVisible(false);
    }

    public void openGenerarReporte(){
        GenerarReporteAnchor.setVisible(true);
    }
    public void cerrarGenerarReporte(){
        GenerarReporteAnchor.setVisible(false);
    }
    @FXML private AnchorPane tablaReporte;

    public void openGenerarReporteTabla(){
        GenerarReporteAnchor.setVisible(false);
        llenarTabla();
        tablaReporte.setVisible(true);

    }
    public void cerrarGenerarReporteTabla(){
        GenerarReporteAnchor.setVisible(true);
        tablaReporte.setVisible(false);
    }

    @FXML private TableView<CuentasCobrar> tablaCuentaCobrar;

    @FXML private TableColumn colNombre;

    @FXML private TableColumn sem1;

    @FXML private TableColumn sem2;

    @FXML private TableColumn sem3;

    @FXML private TableColumn sem4;

    @FXML private TableColumn colFinal;

    @FXML private TableView<CuentasCobrar> tablaCuentaPagar;

    @FXML private TableColumn colNombreP;

    @FXML private TableColumn sem1P;

    @FXML private TableColumn sem2P;

    @FXML private TableColumn sem3P;

    @FXML private TableColumn sem4P;

    @FXML private TableColumn colFinalP;

    @FXML private TableView<CuentasCobrar> tablaCuentaIngreso;

    @FXML private TableColumn colNombreI;

    @FXML private TableColumn sem1I;

    @FXML private TableColumn sem2I;

    @FXML private TableColumn sem3I;

    @FXML private TableColumn sem4I;

    @FXML private TableColumn colFinalI;

    @FXML private TableView<CuentasCobrar> tablaCuentaGastos;

    @FXML private TableColumn colNombreG;

    @FXML private TableColumn sem1G;

    @FXML private TableColumn sem2G;

    @FXML private TableColumn sem3G;

    @FXML private TableColumn sem4G;

    @FXML private TableColumn colFinalG;

    @FXML private TableView<CuentasCobrar> tablaCuentaBancos;

    @FXML private TableColumn colNombreB;

    @FXML private TableColumn sem1B;

    @FXML private TableColumn sem2B;

    @FXML private TableColumn sem3B;

    @FXML private TableColumn sem4B;

    @FXML private TableColumn colFinalB;

    private ObservableList<CuentasCobrar> cuentasCobrar;

    private ObservableList<CuentasCobrar> cuentasPagar;

    private ObservableList<CuentasCobrar> cuentasIngresos;

    private ObservableList<CuentasCobrar> cuentasGastos;

    private ObservableList<CuentasCobrar> cuentasBancos;


    public void llenarTabla(){
        cuentasCobrar = FXCollections.observableArrayList();
        llenaDatosCeuntasPorCobrar();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.sem1.setCellValueFactory(new PropertyValueFactory("semana1"));
        this.sem2.setCellValueFactory(new PropertyValueFactory("semana2"));
        this.sem3.setCellValueFactory(new PropertyValueFactory("semana3"));
        this.sem4.setCellValueFactory(new PropertyValueFactory("semana4"));
        this.colFinal.setCellValueFactory(new PropertyValueFactory("Final"));

        cuentasPagar = FXCollections.observableArrayList();
        llenaDatosCeuntasPorPagar();
        this.colNombreP.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.sem1P.setCellValueFactory(new PropertyValueFactory("semana1"));
        this.sem2P.setCellValueFactory(new PropertyValueFactory("semana2"));
        this.sem3P.setCellValueFactory(new PropertyValueFactory("semana3"));
        this.sem4P.setCellValueFactory(new PropertyValueFactory("semana4"));
        this.colFinalP.setCellValueFactory(new PropertyValueFactory("Final"));

        cuentasIngresos = FXCollections.observableArrayList();
        llenaDatosIngresos();
        this.colNombreI.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.sem1I.setCellValueFactory(new PropertyValueFactory("semana1"));
        this.sem2I.setCellValueFactory(new PropertyValueFactory("semana2"));
        this.sem3I.setCellValueFactory(new PropertyValueFactory("semana3"));
        this.sem4I.setCellValueFactory(new PropertyValueFactory("semana4"));
        this.colFinalI.setCellValueFactory(new PropertyValueFactory("Final"));

        cuentasGastos = FXCollections.observableArrayList();
        llenaDatosGastos();
        this.colNombreG.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.sem1G.setCellValueFactory(new PropertyValueFactory("semana1"));
        this.sem2G.setCellValueFactory(new PropertyValueFactory("semana2"));
        this.sem3G.setCellValueFactory(new PropertyValueFactory("semana3"));
        this.sem4G.setCellValueFactory(new PropertyValueFactory("semana4"));
        this.colFinalG.setCellValueFactory(new PropertyValueFactory("Final"));

        cuentasBancos = FXCollections.observableArrayList();
        llenaDatosBancos();
        this.colNombreB.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.sem1B.setCellValueFactory(new PropertyValueFactory("semana1"));
        this.sem2B.setCellValueFactory(new PropertyValueFactory("semana2"));
        this.sem3B.setCellValueFactory(new PropertyValueFactory("semana3"));
        this.sem4B.setCellValueFactory(new PropertyValueFactory("semana4"));
        this.colFinalB.setCellValueFactory(new PropertyValueFactory("Final"));

    }

    public void llenaDatosCeuntasPorCobrar(){
        for(int i=0; i<5; i++){
            CuentasCobrar cuentasC = new CuentasCobrar("venta "+i,"9000","3500","0","1200","1200");
            cuentasCobrar.add(cuentasC);
            tablaCuentaCobrar.setItems(cuentasCobrar);
        }
    }

    public void llenaDatosCeuntasPorPagar(){
        for(int i=0; i<5; i++){
            CuentasCobrar cuentasP = new CuentasCobrar("Prestamo "+i,"9000","3500","0","1200","1200");
            cuentasPagar.add(cuentasP);
            tablaCuentaPagar.setItems(cuentasPagar);
        }
    }

    public void llenaDatosIngresos(){
        for(int i=0; i<5; i++){
            CuentasCobrar cuentasI = new CuentasCobrar("Efectivo "+i,"9000","3500","0","1200","1200");
            cuentasIngresos.add(cuentasI);
            tablaCuentaIngreso.setItems(cuentasIngresos);
        }
    }

    public void llenaDatosGastos(){
        for(int i=0; i<5; i++){
            CuentasCobrar cuentasG = new CuentasCobrar("Operativos "+i,"9000","3500","0","1200","1200");
            cuentasGastos.add(cuentasG);
            tablaCuentaGastos.setItems(cuentasGastos);
        }
    }

    public void llenaDatosBancos(){
        for(int i=0; i<5; i++){
            CuentasCobrar cuentasB = new CuentasCobrar("Banco "+i,"9000","3500","0","1200","1200");
            cuentasBancos.add(cuentasB);
            tablaCuentaBancos.setItems(cuentasBancos);
        }
    }


    @FXML
    public void cerrarWindowIndicadores(){
        windowindicadores.setVisible(false);
    }
    public void openwindowsGuardarCategoria(){

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(600));
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(600));
        translateTransition.setFromY(-20);
        translateTransition.setToY(26);
        translateTransition.setNode(windowcategoriasub);
        fadeTransition.setNode(windowcategoriasub);
        windowcategoriasub.setVisible(true);
        initComboClasificacion();
        translateTransition.play();
        fadeTransition.play();

    }

    @FXML
    public void cerrarCategoriaSub(){
        windowcategoriasub.setVisible(false);
    }


    public void mensajeGuardado(){
        inputcategoria.setText("");
        inputsubcategoria.setText("");

        Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
        advertencia.setTitle("Registro exitoso");
        advertencia.setHeaderText("se registro la categoria");
        advertencia.show();
    }

    public void agregarCategoriaDB(){
        selecioncombo = (String)comboBoxClasificacion.getSelectionModel().getSelectedItem();
        System.out.println(selecioncombo + "a base");
            if(selecioncombo.length()> 0 && inputcategoria.getText().length()>0 && inputsubcategoria.getText().length()>0){
                Conexion conexion = new Conexion();
            String response = conexion.postCategoria(selecioncombo,inputcategoria.getText(),inputsubcategoria.getText());
                System.out.println(response);
                mensajeGuardado();
                uploadDataTableCategory();
            }
    }



    public void initComboClasificacion(){
        ObservableList<String> items = FXCollections.observableArrayList("GAO","Ingreso","Costo-Venta");
        comboBoxClasificacion.setItems(items);
        String t1 = (String)comboBoxClasificacion.getSelectionModel().getSelectedItem();
        System.out.println(t1);
        selecioncombo= t1;
    }







}
