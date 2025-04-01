package com.evento.resources;

import com.evento.dtos.EnderecoDTO;
import com.evento.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorCep(@PathVariable String cep){
        return ResponseEntity.ok(enderecoService.buscarEnderecoPorCep(cep));
    }

    @PostMapping()
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO){
        EnderecoDTO endereco = enderecoService.cadastrarEndereco(enderecoDTO);
        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable EnderecoDTO enderecoDTO){
        enderecoService.deletarEndereco(enderecoDTO.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO){
        return ResponseEntity.ok(enderecoService.atualizarEndereco(enderecoDTO));
    }

}
