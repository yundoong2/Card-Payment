package myproject.cardpayment.repository;

import myproject.cardpayment.dto.paymentDTO;
import myproject.cardpayment.entity.paymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface paymentRepository extends JpaRepository<paymentEntity, Long> {
    Optional<paymentEntity> findById(String id);
}
