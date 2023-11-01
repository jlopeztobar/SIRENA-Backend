package co.edu.unicauca.SIRENABackend.security.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.SIRENABackend.security.models.TokenModel;

public interface ITokenRepository extends JpaRepository<TokenModel, Integer> {

    @Query("SELECT token FROM TokenModel token JOIN token.user user " +
            "WHERE user.id = :prmUserId AND (token.expired = false OR token.revoked = false)")
    List<TokenModel> findAllValidTokensByUser(@Param("prmUserId") Integer prmUserId);

    Optional<TokenModel> findByToken(String prmToken);

}
