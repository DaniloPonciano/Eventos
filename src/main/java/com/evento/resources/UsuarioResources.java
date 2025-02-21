package com.evento.resources;

import com.evento.dtos.UsuarioDTO;

import com.evento.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResources {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping()
    public String buscarUsuarios(){
        return "Buscando usuários";
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioServices.cadastrarUsuario(usuarioDTO));
    }



}
