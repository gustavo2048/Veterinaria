package com.ohMyDog.OhMyDog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ohMyDog.OhMyDog.DTO.UsuarioDTO;
import com.ohMyDog.OhMyDog.Entity.Usuario;
import com.ohMyDog.OhMyDog.ServiceIMPL.usuarioServiceIMPL;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("usuario")
public class UsuarioControlador {

	@Autowired
	private usuarioServiceIMPL usuarioService;
	
	
	@PostMapping
	@RequestMapping(value="crearUsuario", method = RequestMethod.POST )
	public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuario){
		Usuario usuarioCreado = this.usuarioService.crearUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
	}
	
	@GetMapping
	@RequestMapping(value="consultarUsuarios", method = RequestMethod.GET )
	public ResponseEntity<?> consultarUsuarios(){
		List<Usuario> listadoUsuarios = this.usuarioService.consultarUsuarios();
		return ResponseEntity.ok(listadoUsuarios);
	}
	
	@PostMapping
	@RequestMapping(value="modoficarUsuario", method = RequestMethod.POST )
	public ResponseEntity<?> modificarUsuario(@RequestBody Usuario usuario){
		 Usuario usuario2 = this.usuarioService.modificarUsuario(usuario);
		return ResponseEntity.ok(usuario2);
	}

	@PostMapping
	@RequestMapping(value="Login", method = RequestMethod.POST )
	public ResponseEntity<?> Login(@RequestBody UsuarioDTO user){
		Usuario u = this.usuarioService.buscarUsuarioXEmail(user.getEmail());
		System.out.print(u);
		if(u == null) {
			u= new Usuario();
			u.setId(-1); // usuario no existe, le mandamos el id -1;
			return ResponseEntity.ok(u);}
		else {
			if(u.getPassword().equals(user.getPassword()))
				return ResponseEntity.ok(u);
			else {
				u.setId(-2); // si el usuario existe pero la contra es invalida
				return ResponseEntity.ok(u);
				}
		}
	
	}
	

}