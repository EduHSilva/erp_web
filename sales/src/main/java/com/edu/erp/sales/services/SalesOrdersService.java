package com.edu.erp.sales.services;

import com.edu.erp.sales.dto.SalesOrderDTO;
import com.edu.erp.sales.dto.SalesOrderItemDTO;
import com.edu.erp.sales.dto.SalesProductDTO;
import com.edu.erp.sales.models.SalesOrderItems;
import com.edu.erp.sales.models.SalesOrders;
import com.edu.erp.sales.models.SalesPersons;
import com.edu.erp.sales.models.SalesProducts;
import com.edu.erp.sales.repositories.SalesOrderItemsRepository;
import com.edu.erp.sales.repositories.SalesOrdersRepository;
import com.edu.erp.sales.repositories.SalesProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Transactional
    public SalesOrders save(SalesOrderDTO orderDTO) {
        SalesOrders order = new SalesOrders();
        BeanUtils.copyProperties(orderDTO, order);
        order.setDateCreated(new Date());

        SalesOrders orderSaved = ordersRepository.save(order);
        for (SalesOrderItemDTO item : orderDTO.items()) {
            addItem(item, orderSaved);
        }

        return orderSaved;
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

    public SalesOrders findOne(UUID id) {
        Optional<SalesOrders> order = ordersRepository.findById(id);
        return order.orElse(null);
    }

    @Transactional
    public SalesOrderItems addItem(SalesOrderItemDTO dto) {
        return addItem(dto, null);
    }

    @Transactional
    public SalesOrderItems addItem(SalesOrderItemDTO dto, SalesOrders order) {
        SalesOrderItems item = new SalesOrderItems();
        UUID orderID = order == null ? dto.orderID() : order.getId();
        if (productRepository.existsById(dto.productID()) && ordersRepository.existsById(orderID)) {
            item.setProduct(productRepository.getReferenceById(dto.productID()));
            item.setOrder(ordersRepository.getReferenceById(orderID));
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
