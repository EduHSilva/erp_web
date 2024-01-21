package com.edu.erp.sales.services;

import com.edu.erp.sales.dto.SalesProductDTO;
import com.edu.erp.sales.models.SalesProducts;
import com.edu.erp.sales.repositories.SalesProductsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesProductsService {
    private final SalesProductsRepository repository;

    public SalesProductsService(SalesProductsRepository repository) {
        this.repository = repository;
    }

    public Page<SalesProducts> findAll(Pageable pageable) {
        return repository.findByDateDeletionIsNull(pageable);
    }

    public SalesProducts findOne(UUID id) {
        Optional<SalesProducts> product = repository.findById(id);
        return product.orElse(null);
    }

    public SalesProducts save(SalesProductDTO productDTO) {
        SalesProducts product = new SalesProducts();
        BeanUtils.copyProperties(productDTO, product);
        product.setDateCreated(new Date());
        return repository.save(product);
    }

    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            Optional<SalesProducts> product = repository.findById(id);
            if (product.isPresent()) {
                product.get().setDateDeletion(new Date());
                repository.save(product.get());
            }
            return true;
        }
        return false;
    }

    public SalesProducts update(UUID id, SalesProductDTO dto) {
        if (repository.existsById(id)) {
            Optional<SalesProducts> products = repository.findById(id);
            if (products.isPresent()) {
                SalesProducts product = products.get();
                BeanUtils.copyProperties(dto, product);
                repository.save(product);
                return product;
            }
        }
        return null;
    }
}
