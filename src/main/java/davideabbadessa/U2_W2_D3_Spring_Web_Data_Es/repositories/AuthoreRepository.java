package davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.repositories;

import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.Autore;
import davideabbadessa.U2_W2_D3_Spring_Web_Data_Es.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface  AuthoreRepository extends JpaRepository<Autore, UUID> {
}
