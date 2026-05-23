package br.com.webschool.repository;


import br.com.webschool.entity.Usuario;
import br.com.webschool.entity.Usuario.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailUsuario(String emailUsuario);

    List<Usuario> findByNomeUsuarioContainingIgnoreCase(String nomeUsuario);

    List<Usuario> findByRoleStatus(RoleStatus roleStatus);

    List<Usuario> findByAtivo(Boolean ativo);

    boolean existsByEmailUsuario(String emailUsuario);

    Optional<Usuario> findByResetPasswordToken(String resetPasswordToken);
}