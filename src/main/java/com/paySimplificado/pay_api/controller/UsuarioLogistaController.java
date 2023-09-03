package com.paySimplificado.pay_api.controller;

import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioLogista;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioLogista;
import com.paySimplificado.pay_api.dto.response.DadosDetalhamentoUsuarioLogista;
import com.paySimplificado.pay_api.dto.response.DadosListagemUsuarioLogista;
import com.paySimplificado.pay_api.model.UsuarioLojista;
import com.paySimplificado.pay_api.repository.UsuarioLogistaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario-logista")
public class UsuarioLogistaController {

    @Autowired
    private UsuarioLogistaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuarioLogista dados, UriComponentsBuilder uriBuilder) {
        var usuarioLogista = new UsuarioLojista(dados);
        repository.save(usuarioLogista);
        var uri = uriBuilder.path("/usuario-logista/{id}").buildAndExpand(usuarioLogista.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuarioLogista(usuarioLogista));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarioLogista>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemUsuarioLogista::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuarioLogista dados) {
        var usuarioLogista = repository.getReferenceById(dados.id());
        usuarioLogista.atualizarDados(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuarioLogista(usuarioLogista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
