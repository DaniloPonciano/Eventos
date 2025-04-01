package com.evento.services;


import com.evento.dtos.ProdutoraDTO;
import com.evento.exceptions.BussinessExceptions;
import com.evento.models.Produtora;
import com.evento.repositories.ProdutoraRepository;
import com.evento.specs.ProdutoraSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProdutoraService {
    @Autowired
    private ProdutoraRepository produtoraRepository;

    @Autowired
    private ProdutoraSpec produtoraSpec;

    public Produtora converterProutoraDTOParaProdutora(ProdutoraDTO produtoraDTO){
        return new Produtora(produtoraDTO.getId() ,produtoraDTO.getNome(), produtoraDTO.getCpfCnpj());
    }

    public ProdutoraDTO converterProdutoraParaProdutoraDTO(Produtora produtora){
        return new ProdutoraDTO(produtora.getId(), produtora.getNome(), produtora.getCpfCnpj());
    }

    public ProdutoraDTO cadastrarProdutora(ProdutoraDTO produtoraDTO){
        Produtora produtora = converterProutoraDTOParaProdutora(produtoraDTO);
        produtora = produtoraRepository.save(produtora);
        return converterProdutoraParaProdutoraDTO(produtora);
    }

    public void deletarProdutora(Long id) {
        produtoraRepository.deleteById(id);
    }

    public ProdutoraDTO buscarProdutoraPorId(Long id){
        Produtora produtora = produtoraRepository.findById(id)
                .orElseThrow(() -> new BussinessExceptions("Produtora não encontrado"));
        return converterProdutoraParaProdutoraDTO(produtora);
    }

    public ProdutoraDTO atualizarProdutora(ProdutoraDTO produtoraDTO){

        Produtora produtora = new Produtora();

        if(Objects.isNull(produtoraDTO.getId()))
            throw new BussinessExceptions("Id não pode ser nulo");

        produtoraSpec.verificarSeProdutoraENula(produtora);
        produtoraSpec.verificarSeExisiteProdutoraComNomeDuplicado(produtora);
        produtoraSpec.verificarSeExisitirProdutoraComCpfCnpjDuplicado(produtora);
        produtoraSpec.verificarSeNomeProdutoraDifere(produtora, produtoraDTO);

        produtora = converterProutoraDTOParaProdutora(produtoraDTO);
        produtoraRepository.save(produtora);
        return converterProdutoraParaProdutoraDTO(produtora);
    }

}
