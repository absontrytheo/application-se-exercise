package com.dlizarra.starter.purchase;

import java.util.List;

import javax.validation.Valid;

import com.dlizarra.starter.purchase.PurchaseDto;
import com.dlizarra.starter.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.dlizarra.starter.role.RoleName;

@RestController
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/purchases" +
            "", method = RequestMethod.GET)
    public List<PurchaseDto> findAll() {
        return purchaseService.getPurchases();
    }

    @ResponseBody
    @RequestMapping(value = "/purchases", method = RequestMethod.POST)
    public PurchaseDto create(@RequestBody PurchaseDto purchase) {
        return purchaseService.createPurchase(purchase);
    }

    @RequestMapping(value = "/purchases/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        purchaseService.deletePurchase(id);
    }

}
