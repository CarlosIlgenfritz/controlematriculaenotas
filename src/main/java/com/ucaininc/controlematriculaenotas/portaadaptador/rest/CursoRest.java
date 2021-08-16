package com.ucaininc.controlematriculaenotas.portaadaptador.rest;

import com.ucaininc.controlematriculaenotas.aplicacao.curso.BuscarInformacoesCurso;
import com.ucaininc.controlematriculaenotas.aplicacao.curso.SalvarCurso;
import com.ucaininc.controlematriculaenotas.aplicacao.dtos.BuscarInformacoesCursoDto;
import com.ucaininc.controlematriculaenotas.aplicacao.dtos.CursoDto;
import com.ucaininc.controlematriculaenotas.comun.dominio.RespostaManipuladorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/curso")
public class CursoRest {

    @Autowired
    private SalvarCurso salvarCurso;

    @Autowired
    private BuscarInformacoesCurso buscarInformacoesCurso;

    @PostMapping
    public RespostaManipuladorDto salvarCurso(@RequestBody CursoDto cursoDto) {
        return salvarCurso.salvar(cursoDto);
    }

    @GetMapping
    public BuscarInformacoesCursoDto buscarInformacoesCurso(@PathParam("cursoId") Long cursoId){
        return buscarInformacoesCurso.buscarInformacoesCurso(cursoId);
    }
}
