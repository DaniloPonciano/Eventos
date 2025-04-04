package com.evento.services;

import com.evento.dtos.CidadeDTO;
import com.evento.exceptions.BussinessExceptions;
import com.evento.models.Cidade;
import com.evento.repositories.CidadeRepository;
import com.evento.specs.CidadeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeSpec cidadeSpec;

    public CidadeDTO buscarCidadePorNome(String nome){
        Cidade cidade = cidadeRepository.findByNome(nome).orElseThrow(() ->
                new BussinessExceptions("Cidade não encontrada"));

        return converterCidadeParaCidadeDTO(cidade);
    }

    public CidadeDTO buscarCidadePorId(Long id){
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(() ->
                new BussinessExceptions("Cidade não encontrada"));
        return converterCidadeParaCidadeDTO(cidade);
    }

    public CidadeDTO converterCidadeParaCidadeDTO(Cidade cidade){
        return new CidadeDTO(cidade.getId(), cidade.getEstado(), cidade.getNome());
    }

    public Cidade converterCidadeDTOParaCidade(CidadeDTO cidadeDTO){
        return new Cidade(cidadeDTO.getId(), cidadeDTO.getEstado(), cidadeDTO.getNome());
    }

    public CidadeDTO cadastrarCidade(CidadeDTO cidadeDTO){


        Cidade cidade = converterCidadeDTOParaCidade(cidadeDTO);
        cidade = cidadeSpec.nomeCidadeFormatado(cidade);
        cidade = cidadeRepository.save(cidade);
        return converterCidadeParaCidadeDTO(cidade);
    }

    public void deletarCidade(Long id) {
        cidadeRepository.deleteById(id);
    }

    public CidadeDTO atualizarCidade(CidadeDTO cidadeDTO){
        if (Objects.isNull(cidadeDTO.getId())){
            throw new BussinessExceptions("Id não pode ser nulo");
        }
        Cidade cidade = cidadeRepository.findById(cidadeDTO.getId()).orElseThrow(() ->
                new BussinessExceptions("Cidade não encontrada"));

        cidade = converterCidadeDTOParaCidade(cidadeDTO);
        cidadeRepository.save(cidade);
        return converterCidadeParaCidadeDTO(cidade);
    }

}
