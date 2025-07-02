package com.statewideinsurance.claims_service.Repository;

import com.statewideinsurance.claims_service.Model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository  extends JpaRepository<Claim, Long> {

    List<Claim> findByUserId(Long userId);

}
