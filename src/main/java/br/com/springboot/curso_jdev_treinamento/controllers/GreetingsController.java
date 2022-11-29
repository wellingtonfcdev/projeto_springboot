package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /*IC/CD ou CDI - Injeção de dependencia*/
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
	@GetMapping(path = "/status")
	public String check() {
		return "online";
	}
	
	@GetMapping(value = "/mostrarnome/{name}")
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso Spring Boot API:" + name + "!";
	}
	
    
	@GetMapping(value = "/olamundo/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuarioRepository.save(usuario);
    	return "Olá mundo " + nome;
    }
	
	@GetMapping(value = "listatodos")
	public ResponseEntity<List<Usuario>> listaUsuario(){
		
		List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa a consulta no banco de dados*/
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*Retorna a lista em JSON*/
	}
	
	@PostMapping(value = "salvar") //Mapeia a URL
	@ResponseBody//Descrição da Resposta
	public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){/*Recebe os dados para salvar*/
		
		Usuario user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
   
	@DeleteMapping(value = "delete")/*mapeia a url*/
	@ResponseBody /*Descrição da resposta*/
	public ResponseEntity<String> delete(@RequestParam Long iduser){/*Recebe os dados para deletar*/
		
		usuarioRepository.deleteById(iduser);
		
		return new ResponseEntity<String>("Usuário deletado com sucesso!!", HttpStatus.OK);
		
	}
	
	@GetMapping(value = "buscaruserid")/*mapeia a url*/
	@ResponseBody /*Descrição da resposta*/
	public ResponseEntity<Usuario> buscaruserid(@RequestParam (name = "iduser") Long iduser){/*Recebe os dados para consultar*/
		
		Usuario usuario = usuarioRepository.findById(iduser).get();
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PutMapping(value = "atualizar")/*mapeia a url*/
	@ResponseBody /*Descrição da resposta*/
	public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){/*Dados para atualizar*/
		
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
		
	}
	
	/*Pesquisa por parte do texto*/
	
	@GetMapping(value = "buscarPorNome")/*mapeia a url*/
	@ResponseBody /**/
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){
		
		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}
	
	
	
}
