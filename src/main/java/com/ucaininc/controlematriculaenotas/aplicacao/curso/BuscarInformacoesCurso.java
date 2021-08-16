package com.ucaininc.controlematriculaenotas.aplicacao.curso;

import com.ucaininc.controlematriculaenotas.aplicacao.dtos.AlunoDto;
import com.ucaininc.controlematriculaenotas.aplicacao.dtos.DisciplinaDto;
import com.ucaininc.controlematriculaenotas.aplicacao.dtos.BuscarInformacoesCursoDto;
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
import java.util.Optional;

@Component
@Transactional(rollbackOn = {SQLException.class})
public class BuscarInformacoesCurso {

    private CursoRepositorio cursoRepositorio;

    @Autowired
    public BuscarInformacoesCurso(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    public BuscarInformacoesCursoDto buscarInformacoesCurso(Long CursoId){
        Optional<Curso> cursosOptional = cursoRepositorio.findById(CursoId);

        if(cursosOptional.isEmpty()){
            return new BuscarInformacoesCursoDto(HttpStatus.INTERNAL_SERVER_ERROR, "Curso não encontrado com o identificador informado.");
        }
        Curso curso = cursosOptional.get();
        List<AlunoDto> alunoDto = criarListaDeAlunoDto(curso.getAlunos());
        List<Aluno> alunos = curso.getAlunos();
        Integer quantidadeDeAlunosMatriculados = alunos.size();

        BuscarInformacoesCursoDto manipuladorInformacoesCursoDto = new BuscarInformacoesCursoDto(HttpStatus.OK, "Resultados encontrados");
        manipuladorInformacoesCursoDto.quantidadeDeAlunosMatriculadosNoCurso = quantidadeDeAlunosMatriculados;
        manipuladorInformacoesCursoDto.alunoDtoList = alunoDto;

        return manipuladorInformacoesCursoDto;
    }

    private List<AlunoDto> criarListaDeAlunoDto(List<Aluno> alunos) {
        List<AlunoDto> alunoDtoList = new ArrayList<>();
        alunos.forEach( aluno -> {
            AlunoDto alunoDto = new AlunoDto();

            alunoDto.nome = aluno.getNome();
            alunoDto.disciplinas = criarListaDeDisciplinasDto(aluno.getDisciplinas());
            alunoDto.foiAprovado =   calcularNotaDoAluno(aluno.getDisciplinas());

            alunoDtoList.add(alunoDto);
        });
        return alunoDtoList;
    }

    private List<DisciplinaDto> criarListaDeDisciplinasDto(List<Disciplina> disciplinas) {
        List<DisciplinaDto> disciplinaDtos = new ArrayList<>();

        disciplinas.forEach( disciplinaDentroDoFor -> {
            DisciplinaDto disciplinaDto = new DisciplinaDto();
            disciplinaDto.nome = disciplinaDentroDoFor.getNome();
            disciplinaDto.nota = disciplinaDentroDoFor.getNota();

            disciplinaDtos.add(disciplinaDto);
        });
        return disciplinaDtos;
    }

    private boolean calcularNotaDoAluno(List<Disciplina> disciplinas) {
        Double notaCalculada = 0.0;
        for (Disciplina disciplina : disciplinas) {
            notaCalculada += disciplina.getNota();
        }
        double notaFinal = notaCalculada / disciplinas.size();
        return notaFinal >= 7.0;
    }
}
