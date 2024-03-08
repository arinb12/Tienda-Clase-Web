package com.tienda.service.impl;

import com.tienda.dao.ArticuloDao;
import com.tienda.domain.Articulo;
import com.tienda.service.ArticuloService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements ArticuloService {
    
    @Autowired
    private ArticuloDao articuloDao;

    @Override
    @Transactional(readOnly=true)
    public List<Articulo> getArticulos(boolean stocks) {
        var lista=articuloDao.findAll();
        if (stocks) {
           lista.removeIf(e -> !e.isStock());
        }
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Articulo getArticulo(Articulo articulo) {
        return articuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Articulo articulo) {
        articuloDao.save(articulo);
    }

    @Override
    @Transactional
    public void delete(Articulo articulo) {
        articuloDao.delete(articulo);
    }
}