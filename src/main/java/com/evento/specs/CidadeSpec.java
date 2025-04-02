package com.evento.specs;

import com.evento.dtos.CidadeDTO;
import com.evento.exceptions.BussinessExceptions;
import com.evento.models.Cidade;
import com.evento.models.Usuario;
import com.evento.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.*;

@Service
public class CidadeSpec {

    private static final String MSG_CIDADE_NULA = "Cidade não encontrada: %s.";
    private static final String MSG_CIDADE_DUPLICADA = "Cidade já cadastrada com o nome: %s.";
    private static final String MSG_CIDADE_INVALIDA = "Cidade '%s.' digitada inválida";
    //Validação para o sistema aceitar apenas letras minúsculas ou maiúsculas, sem caracteres especiais
    private static final Pattern PATTERN_NOME_VALIDO = Pattern.compile("^[a-zA-Z ]+$");


    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade verificarSeCidadeENula(Cidade cidade){
        if (isNull(cidade)){
            throw new BussinessExceptions(String.format(MSG_CIDADE_NULA, cidade.getNome()));
        }
        return cidade;
    }

    public Cidade verificarSeExisteCidadeComNomeDuplicado(Cidade cidade){
        if (nonNull(cidade)){
            throw new BussinessExceptions(String.format(MSG_CIDADE_DUPLICADA, cidade.getNome()));
        }
        return cidade;
    }

    public Cidade verificarSeNomeCidadeEValido(Cidade cidade){
        if (nonNull(cidade) && !PATTERN_NOME_VALIDO.matcher(cidade.getNome()).matches()){
            throw new BussinessExceptions(String.format(MSG_CIDADE_INVALIDA, cidade.getNome()));
        }
        return cidade;
    }

    public Cidade nomeCidadeFormatado(Cidade cidade){
        if (isNull(cidade)){
            throw new BussinessExceptions(String.format(MSG_CIDADE_NULA, cidade.getNome()));
        }

        //Formata o nome da cidade para o padrão CamelCase EX: SÃOPAULO -> SaoPaulo
        String nomeFormatado = formatarNomeCidade(cidade.getNome());
        cidade.setNome(nomeFormatado); //Atualiza o nome para Camel Case

        //Verifica se o nome formatado contém caracteres especiais
        if(!PATTERN_NOME_VALIDO.matcher(nomeFormatado).matches()) {
            throw new BussinessExceptions(String.format(MSG_CIDADE_INVALIDA, nomeFormatado));
        }//Se o nome foi alterado para Camel Case mas caiu aqui, apresenta a mensagem de erro

        return cidade;
    }

    //outra funçao que formata o nome da cidade para o padrão CamelCase
    private String formatarNomeCidade(String nome){
        //Remove os espaços extras e divide as palavras corretamente
        String[] palavras = nome.trim().toLowerCase().split("\\s+");
        StringBuilder nomeFormatado = new StringBuilder();

        //Transforma as palavras fazendo com que a primeira letra seja maiúscula
        for (String palavra : palavras){
            if (!palavra.isEmpty()){
                nomeFormatado.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1)).append(" ");
            }
        }

        return nomeFormatado.toString().trim();//Retorna o nome da cidade já formatado
    }

}
