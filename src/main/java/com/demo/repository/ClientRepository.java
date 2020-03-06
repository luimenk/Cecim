package com.demo.repository;

import com.demo.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findByClientId(Long idClient);

    /*@Query(value = "select * from app_user inner join user_role on user_role.user_id=app_user.user_id " +
            "inner JOIN app_role on app_role.role_id=user_role.role_id where app_role.role_name='Super RH'",
            nativeQuery = true)


            SELECT LPAD((consecutivo),4,'0') FROM foliodocumento WHERE idFolio = 2;
UPDATE foliodocumento SET consecutivo = consecutivo + 1 WHERE idFolio = 2;


    List<Client> folio;*/
}
