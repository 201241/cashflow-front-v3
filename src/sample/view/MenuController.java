package sample.view;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import javafx.util.StringConverter;
import sample.Tablas.CuentasCobrar;
import sample.model.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuController implements Initializable
{

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
    private ComboBox<String> comboMeses;

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

    @FXML
    private TextField textoMontoCuenta;

    @FXML
    private TextField textoRazon;

    @FXML
    private RadioButton radioPagar;

    @FXML
    private RadioButton radioCobrar;

    @FXML
    private TextField textoSmaCuentas;

    @FXML
    private TextField textoMontoBanco;

    @FXML
    private TextField textoDescripcion;

    @FXML
    private TextField textoSmaBanco;

    @FXML private Label s1Utilidad;

    @FXML private Label s2Utilidad;

    @FXML private Label s3Utilidad;

    @FXML private Label s4Utilidad;

    @FXML private Label s5Utilidad;

    @FXML private Label sfUtilidad;

    @FXML private Label renta1;

    @FXML private Label renta2;

    @FXML private Label renta3;

    @FXML private Label renta4;

    @FXML private Label renta5;

    @FXML private Label rentaF;

    String selecioncombo = "";
    String selectcombocategoria ="";
    ObservableList<Categoria> itemcategoriacombo = FXCollections.observableArrayList();
    String meses [] = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
    double dif1, dif2, dif3, dif4, dif5, diff;
    String rentaS1 = "0.0", rentaS2 = "0.0", rentaS3 = "0.0", rentaS4 = "0.0", rentaS5 = "0.0", rentaSF = "0.0";

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

    @FXML
    public void salir(){
        System.exit(0);
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
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i=0; i<12; i++){
            items.add(meses[i]);
        }
        comboMeses.setItems(items);
        GenerarReporteAnchor.setVisible(true);

    }
    public void cerrarGenerarReporte(){
        GenerarReporteAnchor.setVisible(false);
    }
    @FXML private AnchorPane tablaReporte;

    public void openGenerarReporteTabla(){
        if(comboMeses.getSelectionModel().getSelectedItem() != null){
            GenerarReporteAnchor.setVisible(false);
            llenarTabla();
            tablaReporte.setVisible(true);
        }else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Error");
            advertencia.setHeaderText("Selecciones un mes");
            advertencia.show();
        }

    }
    public void cerrarGenerarReporteTabla(){
        String rentabilidad [] = {rentaS1, rentaS2, rentaS3, rentaS4, rentaS5, rentaSF};
        Double diferencia [] = {dif1, dif2, dif3, dif4, dif5, diff};
        GenerarReporteAnchor.setVisible(true);
        tablaReporte.setVisible(false);
        tablaCuentaBancos.getItems().clear();
        tablaCuentaGastos.getItems().clear();
        tablaCuentaIngreso.getItems().clear();
        tablaCuentaPagar.getItems().clear();
        tablaCuentaCobrar.getItems().clear();
        for (int i=0; i<5; i++){
            diferencia[i] = 0.0;
            rentabilidad[i] = "0.0";
        }
        rentaS2 = "0.0";
        renta1.setText("0%"); renta2.setText("0%"); renta3.setText("0%"); renta4.setText("0%"); renta5.setText("0%"); rentaF.setText("0%");
        s1Utilidad.setText("0.0"); s2Utilidad.setText("0.0"); s3Utilidad.setText("0.0"); s4Utilidad.setText("0.0"); s5Utilidad.setText("0.0"); sfUtilidad.setText("0.0");

    }

    @FXML
    public void guardarBanco(){
        Conexion conexion = new Conexion();
        if (textoSmaBanco.getText().length()>0 && textoDescripcion.getText().length()>0 && textoMontoBanco.getText().length()>0){
            int numSemana = Integer.parseInt(textoSmaBanco.getText());
            String descripcion = textoDescripcion.getText();
            Double monto = Double.parseDouble( textoMontoBanco.getText());
            String tipo = "banco";
             conexion.postIndicadores(tipo, numSemana, descripcion, monto, obtenerFecha());
            textoSmaBanco.selectAll();
            textoDescripcion.selectAll();
            textoMontoBanco.selectAll();
            Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
            advertencia.setTitle("Realizado");
            advertencia.setHeaderText("Indicador guardado");
            advertencia.show();
        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Faltan datos");
            advertencia.setHeaderText("Llene todos los campos");
            advertencia.show();
        }
    }

    @FXML
        public void guardarCuenta(){
        Conexion conexion = new Conexion();
        if(textoSmaCuentas.getText().length()>0 && textoRazon.getText().length()>0 && textoMontoCuenta.getText().length()>0 && (radioPagar.isSelected() == true || radioCobrar.isSelected() == true)){
            int numSemana = Integer.parseInt(textoSmaCuentas.getText());
            String razon = textoRazon.getText();
            Double monto = Double.parseDouble(textoMontoCuenta.getText());
            String tipo = "";
            if (radioPagar.isSelected() == true){
                tipo = "pagar";
                radioPagar.setSelected(false);
                bloquarRadioCobrar();
            } else{
                tipo = "cobrar";
                radioCobrar.setSelected(false);
                bloquarRadioPagar();
            }

            conexion.postIndicadores(tipo, numSemana, razon, monto, obtenerFecha());
            textoSmaCuentas.selectAll();
            textoRazon.selectAll();
            textoMontoCuenta.selectAll();
            Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
            advertencia.setTitle("Realizado");
            advertencia.setHeaderText("Indicador guardado");
            advertencia.show();
        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Faltan datos");
            advertencia.setHeaderText("Llene todos los campos");
            advertencia.show();
        }
    }

    @FXML
    public void bloquarRadioCobrar(){
        if (radioPagar.isSelected() == false){
            radioCobrar.setDisable(false);
        } else {
            radioCobrar.setDisable(true);
        }
    }

    @FXML
    public void bloquarRadioPagar(){
        if (radioCobrar.isSelected() == false){
            radioPagar.setDisable(false);
        } else {
            radioPagar.setDisable(true);
        }
    }

    @FXML private TableView<CuentasCobrar> tablaCuentaCobrar;

    @FXML private TableColumn colNombre;

    @FXML private TableColumn sem1;

    @FXML private TableColumn sem2;

    @FXML private TableColumn sem3;

    @FXML private TableColumn sem4;

    @FXML private TableColumn colFinal;

    @FXML private TableColumn finalCobrar;

    @FXML private TableView<CuentasCobrar> tablaCuentaPagar;

    @FXML private TableColumn colNombreP;

    @FXML private TableColumn sem1P;

    @FXML private TableColumn sem2P;

    @FXML private TableColumn sem3P;

    @FXML private TableColumn sem4P;

    @FXML private TableColumn colFinalP;

    @FXML private TableColumn finalPagar;

    @FXML private TableView<CuentasCobrar> tablaCuentaIngreso;

    @FXML private TableColumn colNombreI;

    @FXML private TableColumn sem1I;

    @FXML private TableColumn sem2I;

    @FXML private TableColumn sem3I;

    @FXML private TableColumn sem4I;

    @FXML private TableColumn colFinalI;

    @FXML private TableColumn finalIngresos;

    @FXML private TableView<CuentasCobrar> tablaCuentaGastos;

    @FXML private TableColumn colNombreG;

    @FXML private TableColumn sem1G;

    @FXML private TableColumn sem2G;

    @FXML private TableColumn sem3G;

    @FXML private TableColumn sem4G;

    @FXML private TableColumn colFinalG;

    @FXML private TableColumn finalGastos;

    @FXML private TableView<CuentasCobrar> tablaCuentaBancos;

    @FXML private TableColumn colNombreB;

    @FXML private TableColumn sem1B;

    @FXML private TableColumn sem2B;

    @FXML private TableColumn sem3B;

    @FXML private TableColumn sem4B;

    @FXML private TableColumn colFinalB;

    @FXML private TableColumn finalBanco;

    private ObservableList<CuentasCobrar> cuentasCobrar;

    private ObservableList<CuentasCobrar> cuentasPagar;

    private ObservableList<CuentasCobrar> cuentasIngresos;

    private ObservableList<CuentasCobrar> cuentasGastos;

    private ObservableList<CuentasCobrar> cuentasBancos;


    public void llenarTabla(){
        System.out.println(comboMeses.getSelectionModel(). getSelectedItem());
        Conexion conexion = new Conexion();
        Boolean axu = false, aux2 = false;
        ObservableList <Semana> listaReporte = conexion.generarPdf();
        ObservableList <Indicadores> listaIndicadores = conexion.getIndicadores();
        String mes = comboMeses.getSelectionModel(). getSelectedItem();

        for (int i=0; i<listaIndicadores.size(); i++){
            if(listaIndicadores.get(i).getMes().equals(mes)){
                aux2 = true;
            }
        }

        for (int i=0; i<listaReporte.size(); i++){
            if(listaReporte.get(i).getMes().equals(mes)) {
                axu = true;
            }
        }

        if (axu || aux2){
            cuentasCobrar = FXCollections.observableArrayList();
            llenaDatosCeuntasPorCobrar(listaIndicadores, mes);
            this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.sem1.setCellValueFactory(new PropertyValueFactory("semana1"));
            this.sem2.setCellValueFactory(new PropertyValueFactory("semana2"));
            this.sem3.setCellValueFactory(new PropertyValueFactory("semana3"));
            this.sem4.setCellValueFactory(new PropertyValueFactory("semana4"));
            this.colFinal.setCellValueFactory(new PropertyValueFactory("semana5"));
            this.finalCobrar.setCellValueFactory(new PropertyValueFactory("Final"));

            cuentasPagar = FXCollections.observableArrayList();
            llenaDatosCeuntasPorPagar(listaIndicadores, mes);
            this.colNombreP.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.sem1P.setCellValueFactory(new PropertyValueFactory("semana1"));
            this.sem2P.setCellValueFactory(new PropertyValueFactory("semana2"));
            this.sem3P.setCellValueFactory(new PropertyValueFactory("semana3"));
            this.sem4P.setCellValueFactory(new PropertyValueFactory("semana4"));
            this.colFinalP.setCellValueFactory(new PropertyValueFactory("semana5"));
            this.finalPagar.setCellValueFactory(new PropertyValueFactory("Final"));

            cuentasIngresos = FXCollections.observableArrayList();
            llenaDatosIngresos(listaReporte, mes);
            this.colNombreI.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.sem1I.setCellValueFactory(new PropertyValueFactory("semana1"));
            this.sem2I.setCellValueFactory(new PropertyValueFactory("semana2"));
            this.sem3I.setCellValueFactory(new PropertyValueFactory("semana3"));
            this.sem4I.setCellValueFactory(new PropertyValueFactory("semana4"));
            this.colFinalI.setCellValueFactory(new PropertyValueFactory("semana5"));
            this.finalIngresos.setCellValueFactory(new PropertyValueFactory("Final"));

            cuentasGastos = FXCollections.observableArrayList();
            llenaDatosGastos(listaReporte, mes);
            this.colNombreG.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.sem1G.setCellValueFactory(new PropertyValueFactory("semana1"));
            this.sem2G.setCellValueFactory(new PropertyValueFactory("semana2"));
            this.sem3G.setCellValueFactory(new PropertyValueFactory("semana3"));
            this.sem4G.setCellValueFactory(new PropertyValueFactory("semana4"));
            this.colFinalG.setCellValueFactory(new PropertyValueFactory("semana5"));
            this.finalGastos.setCellValueFactory(new PropertyValueFactory("Final"));

            cuentasBancos = FXCollections.observableArrayList();
            llenaDatosBancos(listaIndicadores, mes);
            this.colNombreB.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.sem1B.setCellValueFactory(new PropertyValueFactory("semana1"));
            this.sem2B.setCellValueFactory(new PropertyValueFactory("semana2"));
            this.sem3B.setCellValueFactory(new PropertyValueFactory("semana3"));
            this.sem4B.setCellValueFactory(new PropertyValueFactory("semana4"));
            this.colFinalB.setCellValueFactory(new PropertyValueFactory("semana5"));
            this.finalBanco.setCellValueFactory(new PropertyValueFactory("Final"));

            Double aux = 0.0;
            long a = 0;
            aux = dif1/Double.parseDouble(rentaS1)*100;
            a = Math.round(aux);
            if (a<1 || Double.parseDouble(rentaS1)<1){
                a = 0;
            }
            renta1.setText(a+"%");
            aux = dif2/Double.parseDouble(rentaS2)*100;
            a = Math.round(aux);
            if (a<1 || Double.parseDouble(rentaS2)<1){
                a = 0;
            }
            renta2.setText(a+"%");
            aux = dif3/Double.parseDouble(rentaS3)*100;
            a = Math.round(aux);
            if (a<1 || Double.parseDouble(rentaS3)<1){
                a = 0;
            }
            renta3.setText(a+"%");
            aux = dif4/Double.parseDouble(rentaS4)*100;
            a = Math.round(aux);
            if (a<1 || Double.parseDouble(rentaS4)<1){
                a = 0;
            }
            renta4.setText(a+"%");
            aux = dif5/Double.parseDouble(rentaS5)*100;
            a = Math.round(aux);
            if (a<1 || Double.parseDouble(rentaS5)<1){
                a = 0;
            }
            renta5.setText(a+"%");
            aux = diff/Double.parseDouble(rentaSF)*100;
            a = Math.round(aux);
            if (a<1 || Double.parseDouble(rentaSF)<1){
                a = 0;
            }
            rentaF.setText(a+"%");

        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Sin registros");
            advertencia.setHeaderText("No hay registros del mes de: " + mes);
            advertencia.show();
        }

    }

    public void llenaDatosCeuntasPorCobrar(ObservableList<Indicadores> lista, String mes){

        Boolean axu = false;
        ArrayList <Indicadores> listaCobrar = new ArrayList<>();
        for (int i=0; i<lista.size(); i++){
            if(lista.get(i).getTipoCuenta().equals("cobrar") && lista.get(i).getMes().equals(mes)) {
                listaCobrar.add(lista.get(i));
                axu = true;
            }
        }

        if(axu){
            Double monto1 = 0.0, monto2 =0.0, monto3 =0.0, monto4 =0.0, monto5 =0.0;
            ArrayList <String> auxList = new ArrayList<>();
            for (int i=0; i<= listaCobrar.size(); i++){
                String sema1 = "0.0", sema2 = "0.0", sema3 = "0.0", sema4 = "0.0", sema5 = "0.0", nameAux;
                int auxNum=0;
                Boolean aux2 = false;
                if( i< listaCobrar.size()){
                    nameAux = listaCobrar.get(i).getRazonSocial();
                    for (int j=0; j<listaCobrar.size(); j++){
                        if(listaCobrar.get(j).getRazonSocial().equals(nameAux)){
                            switch (listaCobrar.get(j).getNoSemana()){
                                case 1: sema1 = String.valueOf(listaCobrar.get(j).getMonto()); break;
                                case 2: sema2 = String.valueOf(listaCobrar.get(j).getMonto()); break;
                                case 3: sema3 = String.valueOf(listaCobrar.get(j).getMonto()); break;
                                case 4: sema4 = String.valueOf(listaCobrar.get(j).getMonto()); break;
                                case 5: sema5 = String.valueOf(listaCobrar.get(j).getMonto()); break;
                            }
                            auxNum = auxNum + 1;
                        }
                    }
                    switch (listaCobrar.get(i).getNoSemana()){
                        case 1: monto1 = monto1 + listaCobrar.get(i).getMonto(); break;
                        case 2: monto2 = monto2 + listaCobrar.get(i).getMonto(); break;
                        case 3: monto3 = monto3 + listaCobrar.get(i).getMonto(); break;
                        case 4: monto4 = monto4 + listaCobrar.get(i).getMonto(); break;
                        case 5: monto5 = monto5 + listaCobrar.get(i).getMonto(); break;
                    }
                    for (int h=0; h<auxList.size(); h++){
                        if (nameAux.equals(auxList.get(h))){
                            aux2 = true;
                        }
                    }
                    if(!aux2){
                        CuentasCobrar cuentasC = new CuentasCobrar(nameAux, sema1, sema2, sema3, sema4, sema5, sema5);
                        cuentasCobrar.add(cuentasC);
                        tablaCuentaCobrar.setItems(cuentasCobrar);
                        auxList.add(nameAux);
                    }
                } else {
                    CuentasCobrar cuentasC = new CuentasCobrar("Total: ", String.valueOf(monto1),  String.valueOf(monto2),  String.valueOf(monto3),  String.valueOf(monto4),  String.valueOf(monto5), String.valueOf(monto5));
                    cuentasCobrar.add(cuentasC);
                    tablaCuentaCobrar.setItems(cuentasCobrar);
                }
            }
        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Sin registros");
            advertencia.setHeaderText("No hay registros de cuentas por Cobrar");
            advertencia.show();
        }
    }

    public void llenaDatosCeuntasPorPagar(ObservableList<Indicadores> lista, String mes){
        Boolean axu = false;
        ArrayList <Indicadores> listaPagar = new ArrayList<>();
        for (int i=0; i<lista.size(); i++){
            if(lista.get(i).getTipoCuenta().equals("pagar") && lista.get(i).getMes().equals(mes)) {
                listaPagar.add(lista.get(i));
                axu = true;
            }
        }

        if(axu){
            Double monto1 = 0.0, monto2 =0.0, monto3 =0.0, monto4 =0.0, monto5 =0.0;
            ArrayList <String> auxList = new ArrayList<>();
            for (int i=0; i<= listaPagar.size(); i++){
                String sema1 = "0.0", sema2 = "0.0", sema3 = "0.0", sema4 = "0.0", sema5 = "0.0", nameAux;
                int auxNum=0;
                Boolean aux2 = false;
                if( i< listaPagar.size()){
                    nameAux = listaPagar.get(i).getRazonSocial();
                    for (int j=0; j<listaPagar.size(); j++){
                        if(listaPagar.get(j).getRazonSocial().equals(nameAux)){
                            switch (listaPagar.get(j).getNoSemana()){
                                case 1: sema1 = String.valueOf(listaPagar.get(j).getMonto()); break;
                                case 2: sema2 = String.valueOf(listaPagar.get(j).getMonto()); break;
                                case 3: sema3 = String.valueOf(listaPagar.get(j).getMonto()); break;
                                case 4: sema4 = String.valueOf(listaPagar.get(j).getMonto()); break;
                                case 5: sema5 = String.valueOf(listaPagar.get(j).getMonto()); break;
                            }
                            auxNum = auxNum + 1;
                        }
                    }
                    switch (listaPagar.get(i).getNoSemana()){
                        case 1: monto1 = monto1 + listaPagar.get(i).getMonto(); break;
                        case 2: monto2 = monto2 + listaPagar.get(i).getMonto(); break;
                        case 3: monto3 = monto3 + listaPagar.get(i).getMonto(); break;
                        case 4: monto4 = monto4 + listaPagar.get(i).getMonto(); break;
                        case 5: monto5 = monto5 + listaPagar.get(i).getMonto(); break;
                    }
                    for (int h=0; h<auxList.size(); h++){
                        if (nameAux.equals(auxList.get(h))){
                            aux2 = true;
                        }
                    }
                    if(!aux2){
                        CuentasCobrar cuentasP = new CuentasCobrar(nameAux, sema1, sema2, sema3, sema4, sema5, sema5);
                        cuentasPagar.add(cuentasP);
                        tablaCuentaPagar.setItems(cuentasPagar);
                        auxList.add(nameAux);
                    }
                } else {
                    CuentasCobrar cuentasP = new CuentasCobrar("Total: ", String.valueOf(monto1),  String.valueOf(monto2),  String.valueOf(monto3),  String.valueOf(monto4),  String.valueOf(monto5), String.valueOf(monto5));
                    cuentasPagar.add(cuentasP);
                    tablaCuentaPagar.setItems(cuentasPagar);
                }
            }
        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Sin registros");
            advertencia.setHeaderText("No hay registros de cuentas por pagar");
            advertencia.show();
        }
    }

    public void llenaDatosIngresos(ObservableList<Semana> lista, String mes){
        Boolean axu = false;
        ArrayList <Semana> listaEntrada = new ArrayList<>();
        for (int i=0; i<lista.size(); i++){
            if(lista.get(i).getTipo().equals("entrada") && lista.get(i).getMes().equals(mes)) {
                listaEntrada.add(lista.get(i));
                axu = true;
            }
        }

        if(axu){
            Double monto1 = 0.0, monto2 =0.0, monto3 =0.0, monto4 =0.0, monto5 =0.0, Final = 0.0;;
            ArrayList <String> auxList = new ArrayList<>();
            for (int i=0; i<= listaEntrada.size(); i++){
                String sema1t = "0.0", sema2t = "0.0", sema3t = "0.0", sema4t = "0.0", sema5t = "0.0", nameAux;
                Double Final2 = 0.0, sema1 = 0.0, sema2 = 0.0, sema3 = 0.0, sema4 = 0.0, sema5 = 0.0;
                Boolean aux2 = false;
                if( i< listaEntrada.size()){
                    nameAux = listaEntrada.get(i).getCategoria().getCategoria();
                    for (int j=0; j<listaEntrada.size(); j++){
                        if(listaEntrada.get(j).getCategoria().getCategoria().equals(nameAux)){
                            switch (listaEntrada.get(j).getNumeroSemana()){
                                case 1: sema1 = sema1 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 2: sema2 = sema2 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 3: sema3 = sema3 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 4: sema4 = sema4 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 5: sema5 = sema5 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                            }
                            Final2 = Final2 + listaEntrada.get(j).getMonto();
                        }
                    }
                    switch (listaEntrada.get(i).getNumeroSemana()){
                        case 1: monto1 = monto1 + listaEntrada.get(i).getMonto(); break;
                        case 2: monto2 = monto2 + listaEntrada.get(i).getMonto(); break;
                        case 3: monto3 = monto3 + listaEntrada.get(i).getMonto(); break;
                        case 4: monto4 = monto4 + listaEntrada.get(i).getMonto(); break;
                        case 5: monto5 = monto5 + listaEntrada.get(i).getMonto(); break;
                    }
                    for (int h=0; h<auxList.size(); h++){
                        if (nameAux.equals(auxList.get(h))){
                            aux2 = true;
                        }
                    }
                    if(!aux2){
                        CuentasCobrar cuentasI = new CuentasCobrar(nameAux, String.valueOf(Math.round((sema1)*100.0)/100.0), String.valueOf(Math.round((sema2)*100.0)/100.0), String.valueOf(Math.round((sema3)*100.0)/100.0), String.valueOf(Math.round((sema4)*100.0)/100.0), String.valueOf(Math.round((sema5)*100.0)/100.0), String.valueOf(Math.round((Final2)*100.0)/100.0));
                        Final = Math.round((Final + Final2)*100.0)/100.0;
                        cuentasIngresos.add(cuentasI);
                        tablaCuentaIngreso.setItems(cuentasIngresos);
                        auxList.add(nameAux);
                    }
                } else {
                    CuentasCobrar cuentasI = new CuentasCobrar("Total: ", String.valueOf(monto1),  String.valueOf(monto2),  String.valueOf(monto3),  String.valueOf(monto4),  String.valueOf(monto5), String.valueOf(Final));
                    dif1=monto1; dif2=monto2; dif3=monto3; dif4=monto4; dif5=monto5; diff=monto5;
                    cuentasIngresos.add(cuentasI);
                    tablaCuentaIngreso.setItems(cuentasIngresos);
                }
            }
        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Sin registros");
            advertencia.setHeaderText("No hay registros de flujo de entradas");
            advertencia.show();
        }

    }

    public void llenaDatosGastos(ObservableList<Semana> lista, String mes){

        Boolean axu = false;
        ArrayList <Semana> listaEntrada = new ArrayList<>();
        for (int i=0; i<lista.size(); i++){
            if(lista.get(i).getTipo().equals("salida") && lista.get(i).getMes().equals(mes)) {
                listaEntrada.add(lista.get(i));
                axu = true;
            }
        }

        if(axu){
            Double monto1 = 0.0, monto2 =0.0, monto3 =0.0, monto4 =0.0, monto5 =0.0, Final = 0.0;
            ArrayList <String> auxList = new ArrayList<>();
            for (int i=0; i<= listaEntrada.size(); i++){
                String sema1t = "0.0", sema2t = "0.0", sema3t = "0.0", sema4t = "0.0", sema5t = "0.0", nameAux;
                Double Final2 = 0.0, sema1 = 0.0, sema2 = 0.0, sema3 = 0.0, sema4 = 0.0, sema5 = 0.0;
                Boolean aux2 = false;
                if( i< listaEntrada.size()){
                    nameAux = listaEntrada.get(i).getCategoria().getCategoria();
                    for (int j=0; j<listaEntrada.size(); j++){
                        if(listaEntrada.get(j).getCategoria().getCategoria().equals(nameAux)){
                            switch (listaEntrada.get(j).getNumeroSemana()){
                                case 1: sema1 = sema1 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 2: sema2 = sema2 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 3: sema3 = sema3 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 4: sema4 = sema4 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                                case 5: sema5 = sema5 + listaEntrada.get(j).getMonto(); /*String.valueOf(listaEntrada.get(j).getMonto());*/ break;
                            }
                            Final2 = Final2 + listaEntrada.get(j).getMonto();
                        }
                    }
                    switch (listaEntrada.get(i).getNumeroSemana()){
                        case 1: monto1 = monto1 + listaEntrada.get(i).getMonto(); break;
                        case 2: monto2 = monto2 + listaEntrada.get(i).getMonto(); break;
                        case 3: monto3 = monto3 + listaEntrada.get(i).getMonto(); break;
                        case 4: monto4 = monto4 + listaEntrada.get(i).getMonto(); break;
                        case 5: monto5 = monto5 + listaEntrada.get(i).getMonto(); break;
                    }
                    for (int h=0; h<auxList.size(); h++){
                        if (nameAux.equals(auxList.get(h))){
                            aux2 = true;
                        }
                    }
                    if(!aux2){
                        CuentasCobrar cuentasS = new CuentasCobrar(nameAux, String.valueOf(Math.round((sema1)*100.0)/100.0), String.valueOf(Math.round((sema2)*100.0)/100.0), String.valueOf(Math.round((sema3)*100.0)/100.0), String.valueOf(Math.round((sema4)*100.0)/100.0), String.valueOf(Math.round((sema5)*100.0)/100.0), String.valueOf(Math.round((Final2)*100.0)/100.0));
                        Final = Math.round((Final + Final2)*100.0)/100.0;
                        cuentasGastos.add(cuentasS);
                        tablaCuentaGastos.setItems(cuentasGastos);
                        auxList.add(nameAux);
                    }
                } else {
                    CuentasCobrar cuentasS = new CuentasCobrar("Total: ", String.valueOf(monto1),  String.valueOf(monto2),  String.valueOf(monto3),  String.valueOf(monto4),  String.valueOf(monto5), String.valueOf(Final));
                    rentaS1 = String.valueOf(dif1); rentaS2 = String.valueOf(dif2); rentaS3 = String.valueOf(dif3); rentaS4 = String.valueOf(dif4); rentaS5 = String.valueOf(dif5); rentaSF = String.valueOf(diff);
                    Double aux1 = dif1, aux22 = dif2, aux3 = dif3, aux4 = dif4, aux5 = dif5, auxF = diff;
                    dif1= Math.round((dif1-monto1)*100.0)/100.0;
                    //if(dif1 == aux1){ dif1 = 0.0; }

                    dif2=  Math.round((dif2-monto2)*100.0)/100.0;
                    //if(dif2 == aux22){ dif2 = 0.0; }

                    dif3=  Math.round((dif3-monto3)*100.0)/100.0;
                    //if(dif3 == aux3){ dif3 = 0.0; }

                    dif4=  Math.round((dif4-monto4)*100.0)/100.0;
                    //if(dif4 == aux4){ dif4 = 0.0; }

                    dif5=  Math.round((dif5-monto5)*100.0)/100.0;
                    //if(dif5 == aux5){ dif5 = 0.0; }

                    diff= Math.round((dif5-monto5)*100.0)/100.0;
                    //if(diff == auxF){ diff = 0.0; }

                    s1Utilidad.setText(String.valueOf(dif1));
                    s2Utilidad.setText(String.valueOf(dif2));
                    s3Utilidad.setText(String.valueOf(dif3));
                    s4Utilidad.setText(String.valueOf(dif4));
                    s5Utilidad.setText(String.valueOf(dif5));
                    sfUtilidad.setText(String.valueOf(diff));
                    cuentasGastos.add(cuentasS);
                    tablaCuentaGastos.setItems(cuentasGastos);
                }
            }
        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Sin registros");
            advertencia.setHeaderText("No hay registros de flujo de salida");
            advertencia.show();
        }
    }

    public void llenaDatosBancos(ObservableList<Indicadores> lista, String mes){
        Boolean axu = false;
        ArrayList <Indicadores> listaBanco = new ArrayList<>();
        for (int i=0; i<lista.size(); i++){
            if(lista.get(i).getTipoCuenta().equals("banco") && lista.get(i).getMes().equals(mes)) {
                listaBanco.add(lista.get(i));
                axu = true;
            }
        }

        if(axu){
            Double monto1 = 0.0, monto2 =0.0, monto3 =0.0, monto4 =0.0, monto5 =0.0;
            ArrayList <String> auxList = new ArrayList<>();
            for (int i=0; i<= listaBanco.size(); i++){
                String sema1 = "0.0", sema2 = "0.0", sema3 = "0.0", sema4 = "0.0", sema5 = "0.0", nameAux;
                int auxNum=0;
                Boolean aux2 = false;
                if( i< listaBanco.size()){
                    nameAux = listaBanco.get(i).getRazonSocial();
                    for (int j=0; j<listaBanco.size(); j++){
                        if(listaBanco.get(j).getRazonSocial().equals(nameAux)){
                            switch (listaBanco.get(j).getNoSemana()){
                                case 1: sema1 = String.valueOf(listaBanco.get(j).getMonto()); break;
                                case 2: sema2 = String.valueOf(listaBanco.get(j).getMonto()); break;
                                case 3: sema3 = String.valueOf(listaBanco.get(j).getMonto()); break;
                                case 4: sema4 = String.valueOf(listaBanco.get(j).getMonto()); break;
                                case 5: sema5 = String.valueOf(listaBanco.get(j).getMonto()); break;
                            }
                            auxNum = auxNum + 1;
                        }
                    }
                    switch (listaBanco.get(i).getNoSemana()){
                        case 1: monto1 = monto1 + listaBanco.get(i).getMonto(); break;
                        case 2: monto2 = monto2 + listaBanco.get(i).getMonto(); break;
                        case 3: monto3 = monto3 + listaBanco.get(i).getMonto(); break;
                        case 4: monto4 = monto4 + listaBanco.get(i).getMonto(); break;
                        case 5: monto5 = monto5 + listaBanco.get(i).getMonto(); break;
                    }
                    for (int h=0; h<auxList.size(); h++){
                        if (nameAux.equals(auxList.get(h))){
                            aux2 = true;
                        }
                    }
                    if(!aux2){
                        CuentasCobrar cuentasB = new CuentasCobrar(nameAux, sema1, sema2, sema3, sema4, sema5, sema5);
                        cuentasBancos.add(cuentasB);
                        tablaCuentaBancos.setItems(cuentasBancos);
                        auxList.add(nameAux);
                    }
                } else {
                    CuentasCobrar cuentasB = new CuentasCobrar("Total: ", String.valueOf(monto1),  String.valueOf(monto2),  String.valueOf(monto3),  String.valueOf(monto4),  String.valueOf(monto5), String.valueOf(monto5));
                    cuentasBancos.add(cuentasB);
                    tablaCuentaBancos.setItems(cuentasBancos);
                }
            }
        } else {
            Alert advertencia = new Alert(Alert.AlertType.WARNING);
            advertencia.setTitle("Sin registros");
            advertencia.setHeaderText("No hay registros de Bancos");
            advertencia.show();
        }
    }


    @FXML
    public void cerrarWindowIndicadores(){
        windowindicadores.setVisible(false);
    }
    public void openwindowsGuardarCategoria(){
        guardarEdicion.setVisible(false);
        guardarRegistro.setDisable(false);
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


    @FXML
    private Button guardarEdicion;

    public void editar()
    {
        windowcategoriasub.setVisible(true);
        guardarRegistro.setDisable(true);
        guardarEdicion.setVisible(true);
    }

    String variableID;
    public void guardarEdit()
    {
        Conexion con = new Conexion();
        System.out.println("1n "+variableID + inputcategoria.getText() + inputsubcategoria.getText() + comboBoxClasificacion.getSelectionModel().getSelectedItem().toString());
        con.establecerConexion(variableID, inputcategoria.getText(), inputsubcategoria.getText(),comboBoxClasificacion.getSelectionModel().getSelectedItem().toString());

    }






    public void gestionarEvenntos()
    {
        tablacategoria.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Categoria>() {
            @Override
            public void changed(ObservableValue<? extends Categoria> observableValue, Categoria anterior, Categoria actual) {
                if (actual!=null)
                {
                    variableID = actual.getId();
                    inputcategoria.setText(actual.getCategoria());
                    inputsubcategoria.setText(actual.getSubcategoria());
                    comboBoxClasificacion.setPromptText(actual.getClasificacion());
                    comboBoxClasificacion.setValue(actual.getClasificacion());
                    initComboClasificacion();
                }
            }
        });


    }
    @FXML
    private Button guardarRegistro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gestionarEvenntos();
    }
}
