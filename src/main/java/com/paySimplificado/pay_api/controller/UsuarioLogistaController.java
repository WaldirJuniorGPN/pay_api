package com.paySimplificado.pay_api.controller;

import com.paySimplificado.pay_api.dto.request.DadosCadastroUsuarioLogista;
import com.paySimplificado.pay_api.dto.response.DadosDetalhamentoUsuarioLogista;
import com.paySimplificado.pay_api.model.UsuarioLogista;
import com.paySimplificado.pay_api.repository.UsuarioLogistaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario-logista")
public class UsuarioLogistaController {

    @Autowired
    private UsuarioLogistaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuarioLogista dados, UriComponentsBuilder uriBuilder) {
        var usuarioLogista = new UsuarioLogista(dados);
        repository.save(usuarioLogista);
        var uri = uriBuilder.path("/usuario-logista/{id}").buildAndExpand(usuarioLogista.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuarioLogista(usuarioLogista));
    }
}
