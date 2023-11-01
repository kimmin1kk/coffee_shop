package com.dnlab.coffeeshop.supllier.service;

import com.dnlab.coffeeshop.product.domain.Ingredient;
import com.dnlab.coffeeshop.product.repository.IngredientRepository;
import com.dnlab.coffeeshop.supllier.common.SupplyAddForm;
import com.dnlab.coffeeshop.supllier.domain.Supply;
import com.dnlab.coffeeshop.supllier.domain.SupplyContent;
import com.dnlab.coffeeshop.supllier.repository.SupplierRepository;
import com.dnlab.coffeeshop.supllier.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepository;
    private final SupplierRepository supplierRepository;
    private final IngredientRepository ingredientRepository;

    /**
     * @PostMapping("/add-supply)시 호출되는 공급 메서드.
     * supply를 save 하지만 List<SupplyContent> 타입인
     * supplyContentList에 CascadeType.ALL을 지정해 두어
     * supply가 DB에 저장될 때 함께 저장되게 해둠
     * @param supplyAddForm
     */
    public void createSupply(SupplyAddForm supplyAddForm) {
        Supply supply = Supply.builder()
                .dueDate(supplyAddForm.getDueDate())
                .supplier(supplierRepository.findByName(supplyAddForm.supplierName))
                .build();

        addSupplyContentsToSupply(supply, supplyAddForm.getSupplyContentList());

        supplyRepository.save(supply);
    }

    /**
     * createSupply 메서드 호출 시 호출되는 메서드.
     * 메서드명에서 볼 수 있듯 공급항목을 공급에 넣어줌
     * 내부에서 사용되는 메서드이기에 private 접근 제한자를 둠
     * @param supply
     * @param supplyContentInfos
     */
    private void addSupplyContentsToSupply(Supply supply, List<SupplyAddForm.SupplyContentInfo> supplyContentInfos) {
        for (SupplyAddForm.SupplyContentInfo info : supplyContentInfos) {
            Ingredient ingredient = ingredientRepository.findByName(info.getIngredientName());
            SupplyContent supplyContent = SupplyContent.builder()
                    .ingredient(ingredient)
                    .supply(supply)
                    .price(info.getPrice())
                    .unit(info.getUnit())
                    .amount(info.getAmount())
                    .build();
            supply.getSupplyContentList().add(supplyContent);
        }
    }
    public List<Supply> getSupplyList() {
        return supplyRepository.findAll();
    }


}
