package com.ucaininc.controlematriculaenotas.dominio;

import com.ucaininc.controlematriculaenotas.comun.dominio.ExcecaoDeDominio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "DISCIPLINA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Double nota;

    public Disciplina(String nome, Double nota) {
        validarCamposObrigatorios(nome, nota);
        this.nome = nome;
        this.nota = nota;
    }

    private void validarCamposObrigatorios(String nome, Double nota) {
        ExcecaoDeDominio.quandoTextoVazioOuNulo(nome, "Não é possível informar um nome vazio para uma disciplina.");
        ExcecaoDeDominio.quandoNulo(nota, "Não é possível informar uma nota vazia.");
        ExcecaoDeDominio.quandoValorMenorQueUm(nota, "Não é possível informar uma nota menor que zero");
    }

}
