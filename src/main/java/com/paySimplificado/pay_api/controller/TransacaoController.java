package com.paySimplificado.pay_api.controller;

import com.paySimplificado.pay_api.dto.reponse.DadosDetalhamentoTransacao;
import com.paySimplificado.pay_api.dto.reponse.DadosListagemTransacao;
import com.paySimplificado.pay_api.dto.request.DadosCadastroTransacao;
import com.paySimplificado.pay_api.model.Transacao;
import com.paySimplificado.pay_api.repository.TransacaoRepository;
import com.paySimplificado.pay_api.service.RecuperaUsuario;
import com.paySimplificado.pay_api.service.TransacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private RecuperaUsuario recuperaUsuario;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTransacao dados, UriComponentsBuilder uriComponentsBuilder){
        this.recuperaUsuario.recuperarUsuarios(dados.idUsuarioOrigem(), dados.idUsuarioDestino());
        var transacao = new Transacao(this.recuperaUsuario.getUsuarioOrigem(), this.recuperaUsuario.getUsuarioDestino(), dados.valorDaTransacao());
        this.transacaoService.efetuarTransacao(transacao);
        this.repository.save(transacao);
        var uri = uriComponentsBuilder.path("/transacao/{id}").buildAndExpand(transacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTransacao(transacao));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTransacao>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTransacao::new);
        return ResponseEntity.ok(page);
    }
}
