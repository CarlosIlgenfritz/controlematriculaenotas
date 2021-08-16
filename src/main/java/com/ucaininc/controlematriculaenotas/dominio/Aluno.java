package com.ucaininc.controlematriculaenotas.dominio;

import com.ucaininc.controlematriculaenotas.comun.dominio.ExcecaoDeDominio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "ALUNO")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno")
    private List<Disciplina> disciplinas;

    public Aluno(String nome, List<Disciplina> disciplinas) {
        validarCamposObrigatorios(nome, disciplinas);
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    private void validarCamposObrigatorios(String nome, List<Disciplina> disciplinas) {
        ExcecaoDeDominio.quandoTextoVazioOuNulo(nome, "Não é possível informar um nome vazio para um aluno.");
        ExcecaoDeDominio.quandoListaNulaOuVazia(disciplinas, "Não é possível informar um aluno sem desciplinas.");
    }
}
