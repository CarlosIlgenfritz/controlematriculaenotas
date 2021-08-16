package com.ucaininc.controlematriculaenotas.comun.dominio;

import org.springframework.http.HttpStatus;

public class RespostaManipuladorDto {
    public HttpStatus httpStatus;
    public String mensagemDeResposta;

    public RespostaManipuladorDto(HttpStatus httpStatus, String mensagemDeResposta) {
        this.httpStatus = httpStatus;
        this.mensagemDeResposta = mensagemDeResposta;
    }
}
