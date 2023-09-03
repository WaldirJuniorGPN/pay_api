package com.paySimplificado.pay_api.controller;

import com.paySimplificado.pay_api.dto.reponse.DadosDetalhamentoUsuarioLojista;
import com.paySimplificado.pay_api.dto.reponse.DadosListagemUsuarioLojista;
import com.paySimplificado.pay_api.dto.request.DadosAtualizacaoUsuarioLojista;
import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioLojista;
import com.paySimplificado.pay_api.model.UsuarioLojista;
import com.paySimplificado.pay_api.repository.UsuarioLojistaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario-lojista")
public class UsuarioLojistaController {

    @Autowired
    private UsuarioLojistaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuarioLojista dados, UriComponentsBuilder uriComponentsBuilder) {
        var usuarioLojista = new UsuarioLojista(dados);
        repository.save(usuarioLojista);
        var uri = uriComponentsBuilder.path("/usuario-lojista/{id}").buildAndExpand(usuarioLojista.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuarioLojista(usuarioLojista));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarioLojista>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemUsuarioLojista::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuarioLojista dados) {
        var usuarioLojista = repository.getReferenceById(dados.id());
        usuarioLojista.atualizarDados(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuarioLojista(usuarioLojista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
