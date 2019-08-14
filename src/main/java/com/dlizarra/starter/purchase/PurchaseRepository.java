package com.dlizarra.starter.purchase;

import java.util.Optional;

import com.dlizarra.starter.support.jpa.CustomJpaRepository;
import com.dlizarra.starter.purchase.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CustomJpaRepository<Purchase, Integer> {

}
