package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.exception.ValidacaoException;
import com.paySimplificado.pay_api.model.Usuario;
import com.paySimplificado.pay_api.model.UsuarioComum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioService {

    private void saca(UsuarioComum origem, BigDecimal valor) {
        if (valor.compareTo(origem.getCarteira()) > 0) {
            throw new ValidacaoException("Valor inválido. O saldo é insuficiente para concluir a operação");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor inválido. Nãao é possível insetir um número negativo para efetuar essa operação");
        }
        var valorAtual = origem.getCarteira();
        origem.setCarteira(valorAtual.subtract(valor));
    }

    private void deposita(Usuario destino, BigDecimal valor) {
        var valorAtual = destino.getCarteira();
        destino.setCarteira(valorAtual.add(valor));
    }

    public void transfere(UsuarioComum origem, Usuario destino, BigDecimal valor) {
        saca(origem, valor);
        deposita(destino, valor);
    }

}
