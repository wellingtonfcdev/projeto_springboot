package br.com.springboot.curso_jdev_treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;

@ResponseBody
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
