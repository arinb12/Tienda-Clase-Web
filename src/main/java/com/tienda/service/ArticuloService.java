package com.tienda.service;

import com.tienda.domain.Articulo;
import java.util.List;

public interface ArticuloService {
    
    // Se obtiene un listado de articulos en un List
    public List<Articulo> getArticulos(boolean stocks);
    
    // Se obtiene un Articulo, a partir del id de un articulo
    public Articulo getArticulo(Articulo articulo);
    
    // Se inserta un nuevo articulo si el id del articulo esta vacío
    // Se actualiza un articulo si el id del articulo NO esta vacío
    public void save(Articulo articulo);
    
    // Se elimina el articulo que tiene el id pasado por parámetro
    public void delete(Articulo articulo);
 
}