package com.dnlab.coffeeshop.supllier.common;

import com.dnlab.coffeeshop.product.domain.Ingredient;
import com.dnlab.coffeeshop.supllier.domain.Supply;
import com.dnlab.coffeeshop.supllier.domain.SupplyContent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class SupplyAddForm {

    public String supplierName;

    public List<SupplyContentInfo> supplyContentList = new ArrayList<>();

    public LocalDateTime dueDate;


    /**
     * 공급할 재료의 이름, 양, 단위, 가격
     */
    @Getter
    @Setter
    public static class SupplyContentInfo {
        private String ingredientName;
        private Integer amount;
        private Integer price;

        public SupplyContent addSupplyContent(Ingredient ingredient, Supply supply) {
            return SupplyContent.builder()
                    .supply(supply)
                    .ingredient(ingredient)
                    .amount(amount)
                    .price(price)
                    .build();
        }
    }


}
