package com.edu.erp.sales.services;

import com.edu.erp.sales.dto.SalesPersonDTO;
import com.edu.erp.sales.enums.Status;
import com.edu.erp.sales.enums.TypePerson;
import com.edu.erp.sales.models.SalesPersons;
import com.edu.erp.sales.repositories.SalesPersonsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesPersonService {
    private final SalesPersonsRepository repository;

    public SalesPersonService(SalesPersonsRepository repository) {
        this.repository = repository;
    }

    public Page<SalesPersons> findClients(Pageable pageable) {
        List<TypePerson> clientsTypes = new ArrayList<>();
        clientsTypes.add(TypePerson.CLIENT_PF);
        clientsTypes.add(TypePerson.CLIENT_PJ);
        return repository.findByDateDeletionIsNullAndTypeIn(pageable, clientsTypes);
    }

    public Page<SalesPersons> findSellers(Pageable pageable) {
        List<TypePerson> sellerType = new ArrayList<>();
        sellerType.add(TypePerson.SELLER);
        return repository.findByDateDeletionIsNullAndTypeIn(pageable, sellerType);
    }

    public SalesPersons save(SalesPersons person) {
        if (!repository.existsByEmail(person.getEmail())) {
            person.setDateCreated(new Date());
            return repository.save(person);
        } else return null;
    }

    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            Optional<SalesPersons> person = repository.findById(id);
            if (person.isPresent()) {
                person.get().setDateDeletion(new Date());
                repository.save(person.get());
            }
            return true;
        }
        return false;
    }

    public SalesPersons update(UUID id, SalesPersonDTO dto) {
        if (repository.existsById(id)) {
            Optional<SalesPersons> person = repository.findById(id);
            if (person.isPresent()) {
                SalesPersons persons = person.get();
                BeanUtils.copyProperties(dto, persons);
                repository.save(persons);
                return persons;
            }
        }
        return null;
    }

    public SalesPersons getOne(UUID id) {
        Optional<SalesPersons> person = repository.findById(id);
        return person.orElse(null);
    }
}
