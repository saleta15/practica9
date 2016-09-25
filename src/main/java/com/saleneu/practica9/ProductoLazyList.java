package com.saleneu.practica9;

import com.saleneu.practica9.entidades.Producto;
import com.saleneu.practica9.services.ProductoServices;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by saleta on 9/21/2016.
 */

public class ProductoLazyList extends LazyDataModel<Producto> {


    private List<Producto> productos;
    private Producto productoSeleccionado;
    public ProductoLazyList(List<Producto> productos){
        this.productos = productos;
        if(getRowCount() <= 0){
            setRowCount((int)ProductoServices.getInstancia().cantidadProductos());
        }
    }

    public ProductoLazyList(){

    }




    @Override
    public List<Producto> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

           boolean filtro = false;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    String filterProperty = it.next();
                    Object filterValue = filters.get(filterProperty);
                    productos = ProductoServices.getInstancia().paginarProductos(startingAt,maxPerPage,filterValue.toString());
                    setRowCount((int)ProductoServices.getInstancia().cantidadProductosFiltro(filterValue.toString()));
                      filtro = true;

                }

        }

        if(!filtro){

            setRowCount((int)ProductoServices.getInstancia().cantidadProductos());
            productos = ProductoServices.getInstancia().paginarProductos(startingAt,maxPerPage,"");
        }

        // set the total of productos
        if(getRowCount() <= 0){
            setRowCount((int)ProductoServices.getInstancia().cantidadProductos());
            int count = getRowCount();
        }

        // set the page dize
        setPageSize(maxPerPage);

        return productos;
    }

    @Override
    public Object getRowKey(Producto player) {
        return player.getId();
    }

    @Override
    public Producto getRowData(String playerId) {
        int id = Integer.parseInt(playerId);
        return ProductoServices.getInstancia().find(id);

    }
}
