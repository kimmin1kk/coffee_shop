package com.dnlab.coffeeshop.supllier.service;

import com.dnlab.coffeeshop.supllier.common.SupplierAddForm;
import com.dnlab.coffeeshop.supllier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Transactional
    public void addSupplier(SupplierAddForm supplierAddForm) {
        var supplier = supplierAddForm.addSupplier();
        supplierRepository.save(supplier);
    }


}
