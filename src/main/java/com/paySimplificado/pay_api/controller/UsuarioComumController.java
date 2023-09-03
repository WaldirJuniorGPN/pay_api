package com.paySimplificado.pay_api.controller;

import com.paySimplificado.pay_api.dto.reponse.DadosDetalhamentoUsuarioComum;
import com.paySimplificado.pay_api.dto.reponse.DadosListagemUsuarioComum;
import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioComum;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioComum;
import com.paySimplificado.pay_api.model.UsuarioComum;
import com.paySimplificado.pay_api.repository.UsuarioComumRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario-comum")
public class UsuarioComumController {

    @Autowired
    private UsuarioComumRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuarioComum dados, UriComponentsBuilder uriComponentsBuilder) {
        var usuarioComum = new UsuarioComum(dados);
        repository.save(usuarioComum);
        var uri = uriComponentsBuilder.path("/usuario-comum/{id}").buildAndExpand(usuarioComum.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuarioComum(usuarioComum));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarioComum>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemUsuarioComum::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity autalizar(@RequestBody @Valid DadosAtualizacaoUsuarioComum dados) {
        var usuarioComum = repository.getReferenceById(dados.id());
        usuarioComum.atualizarDados(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuarioComum(usuarioComum));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
