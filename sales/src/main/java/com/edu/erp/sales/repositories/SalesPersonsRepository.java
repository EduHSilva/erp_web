package com.edu.erp.sales.repositories;

import com.edu.erp.sales.enums.TypePerson;
import com.edu.erp.sales.models.SalesPersons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SalesPersonsRepository extends JpaRepository<SalesPersons, UUID> {
    Page<SalesPersons> findByDateDeletionIsNullAndTypeIn(Pageable pageable, List<TypePerson> type);

    boolean existsByEmail(String email);

    SalesPersons findByEmailAndType(String email, TypePerson typePerson);
}
