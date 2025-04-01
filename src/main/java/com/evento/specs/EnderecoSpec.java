package com.evento.specs;

import com.evento.exceptions.BussinessExceptions;
import com.evento.models.Endereco;
import com.evento.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class EnderecoSpec {

    private static final String MSG_CEP = "Endereço não encontrado: %s.";

    @Autowired
    EnderecoRepository enderecoRepository;

    public void verificarSeCepEnderecoExiste(Endereco endereco){
        if(isNull(endereco)){
            throw new BussinessExceptions(String.format(MSG_CEP,endereco.getCep()));
        }
    }


}
