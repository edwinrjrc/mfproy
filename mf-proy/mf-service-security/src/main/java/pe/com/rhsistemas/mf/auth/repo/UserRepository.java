package pe.com.rhsistemas.mf.auth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.rhsistemas.mf.auth.entity.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	
	List<Usuario> findByTxLogin(String txLogin);
}
