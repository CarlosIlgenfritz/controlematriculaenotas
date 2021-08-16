package com.ucaininc.controlematriculaenotas.aplicacao.curso;

import com.ucaininc.controlematriculaenotas.aplicacao.dtos.AlunoDto;
import com.ucaininc.controlematriculaenotas.aplicacao.dtos.CursoDto;
import com.ucaininc.controlematriculaenotas.aplicacao.dtos.DisciplinaDto;
import com.ucaininc.controlematriculaenotas.comun.dominio.RespostaManipuladorDto;
import com.ucaininc.controlematriculaenotas.dominio.Aluno;
import com.ucaininc.controlematriculaenotas.dominio.Curso;
import com.ucaininc.controlematriculaenotas.dominio.Disciplina;
import com.ucaininc.controlematriculaenotas.dominio.repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(rollbackOn = {SQLException.class})
public class SalvarCurso {

    private CursoRepositorio cursoRepositorio;

    @Autowired
    public SalvarCurso(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    public RespostaManipuladorDto salvar(CursoDto cursoDto) {
        List<Aluno> alunos = criarAlunos(cursoDto.alunos);
        Curso curso = new Curso(cursoDto.nome, alunos);

        try {
            cursoRepositorio.save(curso);
            return new RespostaManipuladorDto(HttpStatus.OK, "Curso salvo com sucesso!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return new RespostaManipuladorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível salvar o curso.");
        }
    }

    private List<Aluno> criarAlunos(List<AlunoDto> alunos) {

        List<Aluno> alunoList = new ArrayList<>();
        alunos.forEach(alunoDto -> {
            List<Disciplina> listaDeDisciplinas = criarListaDeDisciplinas(alunoDto.disciplinas);

            Aluno aluno = new Aluno(alunoDto.nome, listaDeDisciplinas);
            alunoList.add(aluno);
        });
        return alunoList;
    }

    private List<Disciplina> criarListaDeDisciplinas(List<DisciplinaDto> discplinas) {
        List<Disciplina> disciplinasList = new ArrayList<>();
        discplinas.forEach(disciplinaDentroDoFor -> {
            Disciplina disciplina = new Disciplina(disciplinaDentroDoFor.nome, disciplinaDentroDoFor.nota);

            disciplinasList.add(disciplina);
        });
        return disciplinasList;
    }
}
