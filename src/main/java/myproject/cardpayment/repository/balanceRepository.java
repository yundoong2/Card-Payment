package myproject.cardpayment.repository;

import myproject.cardpayment.entity.balanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface balanceRepository extends JpaRepository<balanceEntity, Long> {

}
