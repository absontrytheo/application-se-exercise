package com.dlizarra.starter.purchase;

import com.dlizarra.starter.purchase.PurchaseDto;

import java.util.List;

public interface PurchaseService {
    PurchaseDto createPurchase(PurchaseDto purchase);

    void updatePurchase(PurchaseDto purchase);

    void deletePurchase(Integer id);

    PurchaseDto getPurchase(Integer id);

    List<PurchaseDto> getPurchases();

}
