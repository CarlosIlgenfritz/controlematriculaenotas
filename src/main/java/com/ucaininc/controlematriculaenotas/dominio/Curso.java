package com.ucaininc.controlematriculaenotas.dominio;

import com.ucaininc.controlematriculaenotas.comun.dominio.ExcecaoDeDominio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "CURSO")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso")
    private List<Aluno> alunos;

    public Curso(String nome, List<Aluno> alunos) {
        validarCamposObrigatorios(nome, alunos);
        this.nome = nome;
        this.alunos = alunos;
    }

    private void validarCamposObrigatorios(String nome, List<Aluno> alunos) {
        ExcecaoDeDominio.quandoTextoVazioOuNulo(nome, "Não é possível informar um nome vazio para um curso.");
        ExcecaoDeDominio.quandoListaNulaOuVazia(alunos, "É necessário informar alunos para este curso.");

    }

}
