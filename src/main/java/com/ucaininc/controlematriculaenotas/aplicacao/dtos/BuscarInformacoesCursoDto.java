package com.ucaininc.controlematriculaenotas.aplicacao.dtos;

import com.ucaininc.controlematriculaenotas.comun.dominio.RespostaManipuladorDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BuscarInformacoesCursoDto extends RespostaManipuladorDto {

    public Integer quantidadeDeAlunosMatriculadosNoCurso;
    public List<AlunoDto> alunoDtoList;

    public BuscarInformacoesCursoDto(HttpStatus httpStatus, String mensagemDeResposta) {
        super(httpStatus, mensagemDeResposta);
    }
}
