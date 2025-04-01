package com.evento.services;

import com.evento.dtos.CidadeDTO;
import com.evento.dtos.EnderecoDTO;
import com.evento.dtos.UsuarioDTO;
import com.evento.exceptions.BussinessExceptions;
import com.evento.models.Cidade;
import com.evento.models.Endereco;
import com.evento.models.Usuario;
import com.evento.repositories.EnderecoRepository;
import com.evento.specs.EnderecoSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoSpec enderecoSpec;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeService cidadeService;

    public EnderecoDTO converterEnderecoParaEnderecoDTO(Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setComplemento(endereco.getComplemento());

        enderecoDTO.setCidade(cidadeService.buscarCidadePorId(endereco.getCidade().getId()));
        return enderecoDTO;
    }

    public Endereco converterEnderecoDTOParaEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCidade(cidadeService.converterCidadeDTOParaCidade(enderecoDTO.getCidade()));
        return endereco;
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep){
        Endereco endereco = enderecoRepository.findByCep(cep).orElseThrow(() ->
                new BussinessExceptions("Endereco não encontrado"));
        return converterEnderecoParaEnderecoDTO(endereco);
    }

    public EnderecoDTO cadastrarEndereco(EnderecoDTO enderecoDTO){
        Endereco enderecoCep = enderecoRepository.findByCep(enderecoDTO.getCep()).orElseThrow(() ->
                new BussinessExceptions("Endereço não encontrado"));
        enderecoSpec.verificarSeCepEnderecoExiste(enderecoCep);

        Endereco endereco = converterEnderecoDTOParaEndereco(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return converterEnderecoParaEnderecoDTO(endereco);
    }

    public void deletarEndereco(Long id){
        enderecoRepository.deleteById(id);
    }

    public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO){
        if(isNull(enderecoDTO.getId())){
            throw new BussinessExceptions("Endereço não encontrado!");
        }
        enderecoRepository.findById(enderecoDTO.getId()).orElseThrow(() -> new BussinessExceptions(("Endereço não encontrado")));
        Endereco endereco = converterEnderecoDTOParaEndereco(enderecoDTO);

        enderecoRepository.save(endereco);
        return converterEnderecoParaEnderecoDTO(endereco);
    }

}
