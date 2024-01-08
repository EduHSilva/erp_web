package com.edu.erp.sales.services;

import com.edu.erp.sales.dto.SalesOrderDTO;
import com.edu.erp.sales.dto.SalesOrderItemDTO;
import com.edu.erp.sales.dto.SalesProductDTO;
import com.edu.erp.sales.models.SalesOrderItems;
import com.edu.erp.sales.models.SalesOrders;
import com.edu.erp.sales.models.SalesProducts;
import com.edu.erp.sales.repositories.SalesOrderItemsRepository;
import com.edu.erp.sales.repositories.SalesOrdersRepository;
import com.edu.erp.sales.repositories.SalesProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesOrdersService {
    private final SalesOrdersRepository ordersRepository;

    private final SalesProductsRepository productRepository;

    private final SalesOrderItemsRepository itemsRepository;


    public SalesOrdersService(SalesOrdersRepository ordersRepository, SalesProductsRepository productRepository, SalesOrderItemsRepository itemsRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
        this.itemsRepository = itemsRepository;
    }

    public Page<SalesOrders> findAll(Pageable pageable) {
        return ordersRepository.findByDateDeletionIsNull(pageable);
    }

    public SalesOrders save(SalesOrderDTO orderDTO) {
        SalesOrders order = new SalesOrders();
        BeanUtils.copyProperties(orderDTO, order);
        order.setDateCreated(new Date());
        return ordersRepository.save(order);
    }

    public boolean delete(UUID id) {
        if (ordersRepository.existsById(id)) {
            Optional<SalesOrders> product = ordersRepository.findById(id);
            if (product.isPresent()) {
                product.get().setDateDeletion(new Date());
                ordersRepository.save(product.get());
            }
            return true;
        }
        return false;
    }

    public SalesOrders update(UUID id, SalesOrderDTO dto) {
        if (ordersRepository.existsById(id)) {
            SalesOrders order = ordersRepository.getReferenceById(id);
            BeanUtils.copyProperties(dto, order);
            ordersRepository.save(order);
            return order;
        }
        return null;
    }

    @Transactional
    public SalesOrderItems addItem(SalesOrderItemDTO dto) {
        SalesOrderItems item = new SalesOrderItems();
        if (productRepository.existsById(dto.productID()) && ordersRepository.existsById(dto.orderID())) {
            item.setProduct(productRepository.getReferenceById(dto.productID()));
            item.setOrder(ordersRepository.getReferenceById(dto.orderID()));
            item.setQuantity(dto.quantity());
            item.setPriceUnit(dto.priceUnit());
            item.setSubTotal(dto.priceUnit() * dto.quantity());
            itemsRepository.save(item);
            return item;
        }
        return null;
    }

    @Transactional
    public boolean removeItem(UUID id) {
        if (itemsRepository.existsById(id)) {
            SalesOrderItems item = itemsRepository.getReferenceById(id);
            itemsRepository.delete(item);
            return true;
        }
        return false;
    }

    @Transactional
    public SalesOrderItems editItem(UUID id, SalesOrderItemDTO dto) {
        if (itemsRepository.existsById(id)) {
            SalesOrderItems item = itemsRepository.getReferenceById(id);
            item.setQuantity(dto.quantity());
            item.setSubTotal(dto.priceUnit() * dto.quantity());
            return item;
        }
        return null;
    }
}
