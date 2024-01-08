package com.policia.zona7.service;

import com.policia.zona7.model.DistritoModel;
import com.policia.zona7.repository.IDistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistritoService {
    @Autowired
    private IDistritoRepository iDistritoRepository;

   }

