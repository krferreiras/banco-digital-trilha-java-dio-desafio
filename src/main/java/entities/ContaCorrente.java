package entities;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato(String tipo_conta) {
        System.out.println("===Extrato Conta "+tipo_conta.toUpperCase()+"===");
        imprimirExtratoReal();
    }

    @Override
    public void imprimirExtratoDeposito(double valor_depositado ) {
        System.out.println("===Extrato de Deposito===");
        imprimirExtratoDep(valor_depositado);
    }

    @Override
    public void imprimirExtratoSaque(double valor_sacado) {
        System.out.println("===Extrato de Saque===");
        imprimirExtratoSac(valor_sacado);
    }

    @Override
    public void imprimirExtratoTransferencia(double valor_transferido,  String tipo_conta, String tipo_transferencia) {
        System.out.println("===Extrato de Transferencia===");
        imprimirExtratoTra(valor_transferido, tipo_conta,tipo_transferencia);
    }
}
