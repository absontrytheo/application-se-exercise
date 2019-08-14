package com.dlizarra.starter.purchase;

import com.dlizarra.starter.role.Role;
import com.dlizarra.starter.role.RoleName;
import com.dlizarra.starter.support.orika.OrikaBeanMapper;
import com.dlizarra.starter.purchase.*;

import com.dlizarra.starter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private OrikaBeanMapper mapper;

    @Transactional
    @Override
    public PurchaseDto createPurchase(final PurchaseDto purchaseDto) {
        final Purchase purchase = mapper.map(purchaseDto, Purchase.class);
        final Purchase resultingPurchase = purchaseRepository.save(purchase);
        return mapper.map(resultingPurchase, PurchaseDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseDto> getPurchases() {
        final List<Purchase> purchases = purchaseRepository.findAll(new Sort("id"));
        final List<PurchaseDto> purchasesDto = new ArrayList<PurchaseDto>();
        purchases.forEach(x -> purchasesDto.add(mapper.map(x, PurchaseDto.class)));

        return purchasesDto;
    }

    @Transactional(readOnly = true)
    @Override
    public PurchaseDto getPurchase(final Integer id) {
        return mapper.map(find(id), PurchaseDto.class);
    }

    @Transactional
    @Override
    public void updatePurchase(final PurchaseDto purchase) {
        Purchase purchaseDataBaseObject = find(purchase.getId());
        purchaseDataBaseObject.setBuyerName(purchase.getBuyerName());
        purchaseDataBaseObject.setProductName(purchase.getProductName());
        purchaseDataBaseObject.setPriceInCents(purchase.getPriceInCents());
        purchaseDataBaseObject.setBuyDate(purchase.getBuyDate());
        purchaseDataBaseObject.setModificationTime(LocalDateTime.now());
    }

    @Transactional
    @Override
    public void deletePurchase(final Integer id) {
        purchaseRepository.delete(id);
    }

    @Transactional(readOnly = true)
    private Purchase find(final Integer id) {
        final Optional<Purchase> purchaseOpt = purchaseRepository.findOne(id);
        return purchaseOpt.orElseThrow(() -> new PurchaseNotFoundException(id));
    }

}
