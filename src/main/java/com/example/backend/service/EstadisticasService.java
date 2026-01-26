package com.example.backend.service;

import com.example.backend.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticasService {

    @Autowired
    private ProductosRepository productoRepository;


    public List<Object[]> top5PrendasPopulares() {
        List<Object[]> top = productoRepository.findTop5PrendasPopulares();
        return top.size() > 5 ? top.subList(0, 5) : top;
    }
}
