package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class Conexion {

    private String _url = "http://localhost:3005/";
    private String path;

    public Conexion(){

    }

    public JSONArray conexionAPIGET(String uri){
        StringBuilder response = new StringBuilder();
        JSONArray jsonArray = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linea;
            while ((linea = bufferresponse.readLine()) != null){
                response.append(linea);
            }
            bufferresponse.close();
            JSONObject jsonObject = new JSONObject(response.toString());
             jsonArray = new JSONArray();
             jsonArray.put(jsonObject);
            System.out.println(jsonArray);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public ObservableList<Categoria> getCategoria(){
        ObservableList<Categoria> itemsCategoria = FXCollections.observableArrayList();
        path="categoria/getCategorias";
        String enlace = _url+path;

        JSONArray jsonArray= conexionAPIGET(enlace);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        if (jsonObject.getString("find").equals("true")){
            for (int j = 0; j <jsonObject.getJSONArray("body").length() ; j++) {
                JSONObject jsonObject1 = jsonObject.getJSONArray("body").getJSONObject(j);
                String idcate = jsonObject1.getString("idCategoria");
                String clasificacion = jsonObject1.getString("clasificacion");
                String cate = jsonObject1.getString("categoria");
                String subCategoria = jsonObject1.getString("subCategoria");
                Categoria categoria = new Categoria(idcate,clasificacion,cate,subCategoria);
                itemsCategoria.add(categoria);
            }
        }

        return itemsCategoria;

    }

    public ObservableList<FlujoEfectivo> getFlujoEfectivo(){
        ObservableList<FlujoEfectivo> itemflujo = FXCollections.observableArrayList();
        path = "flujoEfectivo/getAllFlujoEfectivo";
        String enlace = _url+path;
     JSONArray jsonArray = conexionAPIGET(enlace);
     JSONObject jsonObject1 = jsonArray.getJSONObject(0);
     if(jsonObject1.getString("find").equals("true")){
         for (int i = 0; i < jsonObject1.getJSONArray("body").length() ; i++) {
             JSONObject jsonObject = jsonObject1.getJSONArray("body").getJSONObject(i);
             System.out.println(jsonObject);
             String id = jsonObject.getString("idFlujoEfectivo");
             String fecha = jsonObject.getString("fecha");
             String tipoFlujo = jsonObject.getString("tipo");
             Double cantidad = jsonObject.getJSONObject("Semana").getDouble("monto");
             String idcate = jsonObject.getJSONObject("categorium").getString("idCategoria");
             String clasificacion = jsonObject.getJSONObject("categorium").getString("clasificacion");
             String categoriaitem = jsonObject.getJSONObject("categorium").getString("categoria");
             String subCategoria = jsonObject.getJSONObject("categorium").getString("subCategoria");
             Integer nSemana = jsonObject.getJSONObject("Semana").getInt("numeroSemana");
             String descripcion = jsonObject.getJSONObject("Semana").getString("descripcion");
             Categoria categoria = new Categoria(idcate,clasificacion,categoriaitem,subCategoria);
             FlujoEfectivo flujoEfectivo = new FlujoEfectivo(fecha,tipoFlujo,descripcion,cantidad,categoria,id,nSemana);
             itemflujo.add(flujoEfectivo);
         }
     }

        return itemflujo;
    }

    public ObservableList<Indicadores> getIndicadores(){
        ObservableList<Indicadores> itemIndicador = FXCollections.observableArrayList();
        path = "indicadoresDinero/getIndicadores";
        String enlace = _url+path;
        JSONArray jsonArray = conexionAPIGET(enlace);
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        if (jsonObject1.getString("find").equals("true")){
            for (int i = 0; i < jsonObject1.getJSONArray("body").length() ; i++){
                JSONObject jsonObject = jsonObject1.getJSONArray("body").getJSONObject(i);
                String idIndicador = jsonObject.getString("idIndicadoresDinero");
                String tipoIndicador = jsonObject.getString("tipoIndicador");
                int numeroSemana = jsonObject.getInt("numeroSemana");
                String razonSocial = jsonObject.getString("razonSocial");
                Double monto = jsonObject.getDouble("monto");
                String fecha = jsonObject.getString("fecha");
                String mes = sacarmes(fecha);
                Indicadores indidcador = new Indicadores(tipoIndicador, numeroSemana, razonSocial, monto, idIndicador, fecha, mes);
                itemIndicador.add(indidcador);
            }
        }

        return itemIndicador;
    }

    public ObservableList<Semana> generarPdf(){
        ObservableList<Semana> itemsemna = FXCollections.observableArrayList();
        path="getSemanaPDF/getpdf";
        String enlace = _url+path;
        JSONArray jsonArray = conexionAPIGET(enlace);
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        if (jsonObject1.getString("find").equals("true")){
            for (int i = 0; i < jsonObject1.getJSONArray("body").length() ; i++){
                JSONObject jsonObject = jsonObject1.getJSONArray("body").getJSONObject(i);
                String id = jsonObject.getString("id");
                String descripcion = jsonObject.getString("descripcion");
                Integer numeroSemana = jsonObject.getInt("numeroSemana");
                Double monto = jsonObject.getDouble("monto");
                String mes = jsonObject.getString("mes");
                String tipo = jsonObject.getString("tipo");
                //Semana sem = new Semana(id,descripcion,numeroSemana,monto,mes,tipo);
                //itemsemna.add(sem);
            }
        }

        return itemsemna;

    }

    public String postCategoria(String clasificacion, String categoria, String subcategoria)  {
        path = "categoria/categoriaAdd";
        String enlace = _url+path;
        JSONObject jsonCategoria = new JSONObject();
        jsonCategoria.put("clasificacion", clasificacion);
        jsonCategoria.put("categoria",categoria);
        jsonCategoria.put("subCategoria",subcategoria);
       return conexionAPIPOST(enlace, jsonCategoria);
    }

    public String postFlujo(String fecha, String tipoFlujo, String descripcion, Double cantidad, String idcategoria, Integer numeroSemana){
        path = "flujoEfectivo/addFlujoEfectivo";

        String enlace = _url+path;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mes", sacarmes(fecha));
        jsonObject.put("tipoFlujo",tipoFlujo);
        jsonObject.put("fecha", fecha);
        jsonObject.put("descripcion",descripcion);
        jsonObject.put("cantidad",cantidad);
        jsonObject.put("numeroSemana",numeroSemana);
        jsonObject.put("idCategoria",idcategoria);

        return conexionAPIPOST(enlace,jsonObject);
    }
    public void postIndicadores(String tipo, int numSemana, String razonSocial, Double monto, String fecha){
        path = "indicadoresDinero/addIndicadores";
        String enlace = _url+path;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mes", sacarmes(fecha));
        jsonObject.put("tipoIndicador", tipo);
        jsonObject.put("numeroSemana", numSemana);
        jsonObject.put("razonSocial", razonSocial);
        jsonObject.put("monto", monto);
        jsonObject.put("fecha", fecha);

        conexionAPIPOST(enlace, jsonObject);
    }

    public String sacarmes(String fecha){
        String[] arreglofecha = fecha.split("/");
        String mes = "enero";
        switch (arreglofecha[1]){
            case "01" : {mes = "enero";}
            break;
            case "02" : {mes = "febrero";}break;
            case "03" : {mes = "marzo";}break;
            case "04" : {mes = "abril";}break;
            case "05" : {mes = "mayo";}break;
            case "06" : {mes = "junio";}break;
            case "07" : {mes = "julio";}break;
            case "08" : {mes = "agosto";}break;
            case "09" : {mes = "septiembre";}break;
            case "10" : {mes = "octubre";}break;
            case "11" : {mes = "noviembre";}break;
            case "12" : {mes = "diciembre";}break;
        }
        return mes;
    }

    public String conexionAPIPOST(String uri, JSONObject jsonObject){
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(uri);
            byte[] bytecategoria = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.getOutputStream().write(bytecategoria);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linea;
            while ((linea = bufferedReader.readLine()) != null){
                response.append(linea);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public String establecerConexion(String id,String clasificacion, String categoria, String subcategoria)
    {
        System.out.println("holaaanmebe");
        path = "categoria/updateCategoria";
        String enlace = _url+path;
        JSONObject jsonCategoria = new JSONObject();
        jsonCategoria.put("idCategoria", id);
        jsonCategoria.put("clasificacion", clasificacion);
        jsonCategoria.put("categoria",categoria);
        jsonCategoria.put("subCategoria",subcategoria);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(enlace);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte[] x = jsonCategoria.toString().getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonCategoria.toString());
            out.close();
            connection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String linea;
//            while ((linea = bufferedReader.readLine()) != null){
//                stringBuilder.append(linea);
//            }
            System.out.println("holaaaa");
            System.out.println(stringBuilder.toString());

        }catch (Exception e){
            System.out.println(e);
        }
        return stringBuilder.toString();

//        try {
//            URL url = new URL("http://localhost:3005/categoria/updateCategoria");
//            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//            httpCon.setDoOutput(true);
//            httpCon.setRequestMethod("PUT");
//            byte[]x = jsonCategoria.toString().getBytes("UTF-8");
//            OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
//            out.write(String.valueOf(jsonCategoria));
//            out.close();
//            httpCon.getInputStream();
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }

    }

}
