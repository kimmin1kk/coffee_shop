package com.dnlab.coffeeshop.supllier.service;

import com.dnlab.coffeeshop.product.domain.Ingredient;
import com.dnlab.coffeeshop.product.repository.IngredientRepository;
import com.dnlab.coffeeshop.supllier.common.SupplyAddForm;
import com.dnlab.coffeeshop.supllier.domain.Supplier;
import com.dnlab.coffeeshop.supllier.domain.Supply;
import com.dnlab.coffeeshop.supllier.domain.SupplyContent;
import com.dnlab.coffeeshop.supllier.repository.SupplierRepository;
import com.dnlab.coffeeshop.supllier.repository.SupplyContentRepository;
import com.dnlab.coffeeshop.supllier.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepository;
    private final SupplyContentRepository supplyContentRepository;
    private final SupplierRepository supplierRepository;
    private final IngredientRepository ingredientRepository;

    /**
     * @param supplyAddForm
     * @PostMapping("/add-supply)시 호출되는 공급 메서드.
     * supply를 save 하지만 List<SupplyContent> 타입인
     * supplyContentList에 CascadeType.ALL을 지정해 두어
     * supply가 DB에 저장될 때 함께 저장되게 해둠
     */
    @Transactional
    public void createSupply(SupplyAddForm supplyAddForm) {
        Supplier supplier = supplierRepository.findByName(supplyAddForm.supplierName);
        int totalPrice = getSupplyTotalPrice(supplyAddForm.getSupplyContentList());
        Supply supply = supplyAddForm.toEntity(supplier, totalPrice);
        addSupplyContentsToSupply(supply, supplyAddForm.getSupplyContentList());
        supplyRepository.save(supply);
    }

    /**
     * createSupply 메서드 호출 시 호출되는 메서드.
     * 메서드명에서 볼 수 있듯 공급항목을 공급에 넣어줌
     * 내부에서 사용되는 메서드이기에 private 접근 제한자를 둠
     *
     * @param supply
     * @param supplyContentInfos
     */
    private void addSupplyContentsToSupply(Supply supply, List<SupplyAddForm.SupplyContentInfo> supplyContentInfos) {
        supplyContentInfos.stream()
                .map(info -> {
                    Ingredient ingredient = ingredientRepository.findByName(info.getIngredientName());
                    addAmountToIngredient(ingredient, info.getAmount());
                    return info.toEntity(ingredient,supply);
                }).forEach(supplyContent -> supply.getSupplyContentList().add(supplyContent));
    }

    private void addAmountToIngredient(Ingredient ingredient, int amount) {
        ingredient.plusAmount(amount);
    }

    private Integer getSupplyTotalPrice(List<SupplyAddForm.SupplyContentInfo> supplyContentInfos) {
        return supplyContentInfos.stream()
                .mapToInt(SupplyAddForm.SupplyContentInfo::getPrice).sum();
    }


    public List<Supply> getSupplyList() {
        return supplyRepository.findAll();
    }

    public List<SupplyContent> getSupplyContentList(Long seq) {
        return supplyContentRepository.findSupplyContentsBySupplySeq(seq);
    }

}
