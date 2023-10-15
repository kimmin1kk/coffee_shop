package com.dnlab.coffeeshop.supllier.service;

import com.dnlab.coffeeshop.supllier.common.SupplierAddForm;
import com.dnlab.coffeeshop.supllier.domain.Supplier;
import com.dnlab.coffeeshop.supllier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Transactional
    public void addSupplier(SupplierAddForm supplierAddForm) {
        var supplier = supplierAddForm.addSupplier();
        supplierRepository.save(supplier);
    }

    public List<Supplier> getSupplierList() {
        return supplierRepository.findAll();
    }


}
