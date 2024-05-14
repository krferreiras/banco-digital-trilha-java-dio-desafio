package interfaces;

import entities.Conta;

public interface IConta {
    void sacar(double valor);

    void depositar(double valor);

    void transferir(double valor,double taxa, Conta contaDestino);

    void imprimirExtrato(String tipo_conta);
    void imprimirExtratoDeposito(double valor_deposito);
    void imprimirExtratoSaque(double valor_sacado);
    void imprimirExtratoTransferencia(double valor_transferido, String tipo_conta, String tipo_transferencia);
}
