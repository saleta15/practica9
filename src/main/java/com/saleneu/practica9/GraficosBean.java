package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.entidades.Usuario;
import com.saleneu.practica9.entidades.Venta;
import com.saleneu.practica9.services.ProductoServices;
import com.saleneu.practica9.services.VentaServices;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell_2 on 9/26/2016.
 */
@ManagedBean
@ViewScoped
public class GraficosBean {



    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private BarChartModel barModel;

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    @PostConstruct
    public void init() {
        createPieModel1();
        createBarModel();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        List<Object[]> datos = VentaServices.getInstancia().totalesVentas();

        for(Object[] o: datos){
            pieModel1.set(o[0].toString(),(long)o[1]);
        }

        pieModel1.setTitle("Productos más vendidos");
        pieModel1.setLegendPosition("w");
    }

    private void createBarModel() {
        try {
            barModel = initBarModel();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        barModel.setTitle("Ventas por dia");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Fechas");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Vendido");

    }

    private BarChartModel initBarModel() throws ParseException {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Ventas");



        List<Object[]> datos = VentaServices.getInstancia().ventasPorDia();

        for(Object[] o: datos){

            Date date = (Date)o[0];

            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            String papazon = formateador.format(date);
            boys.set(papazon,(double)o[1]);
        }

        model.addSeries(boys);

        return model;
    }









}
