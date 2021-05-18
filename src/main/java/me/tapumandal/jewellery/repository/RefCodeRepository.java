package me.tapumandal.jewellery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import me.tapumandal.jewellery.domain.ref_code.RefCodeReward;

import java.util.List;

/**
 * Created by TapuMandal on 3/3/2021.
 * For any query ask online.tapu@gmail.com
 */

public interface RefCodeRepository extends JpaRepository<RefCodeReward, Integer> {

    @Query("SELECT id FROM RefCodeReward R WHERE R.code = :refCode")
    List<RefCodeReward> getByCode(String refCode);
}
