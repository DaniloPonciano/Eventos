package com.evento.specs;

import com.evento.dtos.ProdutoraDTO;
import com.evento.dtos.UsuarioDTO;
import com.evento.exceptions.BussinessExceptions;
import com.evento.models.Produtora;
import com.evento.models.Usuario;
import com.evento.repositories.ProdutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ProdutoraSpec {

    private static final String MSG_PRODUTORA_NULO = "Produtora não encontrada: %s.";
    private static final String MSG_NOME = "Produtora %s. já cadastrada.";
    private static final String MSG_CPF_CNPJ = "Produtora com o cpf/cnpj%s. já cadastrada.";

    @Autowired
    ProdutoraRepository produtoraRepository;

    public void verificarSeProdutoraENula(Produtora produtora){
        if(isNull(produtora)){
            throw new BussinessExceptions(String.format(MSG_PRODUTORA_NULO, produtora.getNome()));
        }
    }

    public void verificarSeExisiteProdutoraComNomeDuplicado(Produtora produtora){

        if(nonNull(produtora)){
            throw new BussinessExceptions(String.format(MSG_NOME,produtora.getNome()));
        }
    }

    public void verificarSeExisitirProdutoraComCpfCnpjDuplicado(Produtora produtora){

        if(nonNull(produtora)){
            throw new BussinessExceptions(String.format(MSG_CPF_CNPJ,produtora.getCpfCnpj()));
        }
    }

    public void verificarSeNomeProdutoraDifere(Produtora produtora, ProdutoraDTO produtoraDTO){

        boolean alterouNome = !(produtora.getNome().equals(produtoraDTO.getNome()));

        if (alterouNome){
            boolean encontrouProdutoraComNomeInformado = nonNull(produtoraRepository.findByNome(produtoraDTO.getNome()));

            if (encontrouProdutoraComNomeInformado){
                throw new BussinessExceptions(String.format(MSG_NOME, produtoraDTO.getNome()));
            }
        }
    }
}
