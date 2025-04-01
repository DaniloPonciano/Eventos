package com.evento.specs;

import com.evento.dtos.UsuarioDTO;
import com.evento.exceptions.BussinessExceptions;
import com.evento.models.Usuario;
import com.evento.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UsuarioSpec {

    private static final String MSG_USUARIO_NULO = "Usuário não encontrado: %s.";
    private static final String MSG_EMAIL = "Usuário já cadastrado com email: %s.";
    private static final String MSG_CPF = "Usuário já cadastrado com cpf: %s.";

    @Autowired
    UsuarioRepository usuarioRepository;

    public void verificarSeUsuarioENulo(Usuario usuario){
        if(isNull(usuario)){
            throw new BussinessExceptions(String.format(MSG_USUARIO_NULO,usuario.getNome()));
        }
    }

    public void verificarSeExisiteUsuarioComEmailDuplicado(Usuario usuario){

        if(nonNull(usuario)){
            throw new BussinessExceptions(String.format(MSG_EMAIL,usuario.getEmail()));
        }
    }

    public void verificarSeExisitirUsuarioComCpfDuplicado(Usuario usuario){

        if(nonNull(usuario)){
            throw new BussinessExceptions(String.format(MSG_CPF,usuario.getCpf()));
        }
    }

    public void verificarSeEmailUsuarioDifere(Usuario usuario, UsuarioDTO usuarioDTO){

        boolean alterouEmail = !(usuario.getEmail().equals(usuarioDTO.getEmail()));

        if (alterouEmail){
            boolean encontrouUsuarioComEmailInformado = nonNull(usuarioRepository.findByEmail(usuarioDTO.getEmail()));

            if (encontrouUsuarioComEmailInformado){
                throw new BussinessExceptions(String.format(MSG_EMAIL, usuarioDTO.getEmail()));
            }
        }
    }

    public void verificarSeCpfUsuarioDifere(Usuario usuario, UsuarioDTO usuarioDTO){

        boolean alterouCpf = !(usuario.getCpf().equals(usuarioDTO.getCpf()));

        if (alterouCpf){
            boolean encontrouUsuarioComCpfInformado = nonNull(usuarioRepository.findByCpf(usuarioDTO.getCpf()));

            if (encontrouUsuarioComCpfInformado){
                throw new BussinessExceptions(String.format(MSG_CPF,usuarioDTO.getCpf()));
            }
        }
    }


}
