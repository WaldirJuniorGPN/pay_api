package com.paySimplificado.pay_api.service;

import com.paySimplificado.pay_api.model.UsuarioComum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioComumService {

    public void ralizaTranferencia(UsuarioComum destino, BigDecimal valor) {
        var saldoAtual = destino.getCarteira();
        destino.setCarteira(saldoAtual.add(valor));
    }
}
