package entities;

import interfaces.IConta;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }


    @Override
    public void sacar(double valor) {
        saldo -= valor;

    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor,double taxa, Conta contaDestino) {
        this.sacar(valor+taxa);
        contaDestino.depositar(valor);
    }

    protected void imprimirExtratoReal() {
        System.out.printf("Titular: %s%n",this.cliente);
        System.out.printf("Agencia: %d ",this.agencia);
        System.out.printf("Conta: %d%n",this.numero);
        System.out.printf("Saldo: %.2f%n",this.saldo);
    }

    protected void imprimirExtratoDep(double valor_deposito) {
        System.out.printf("Titular: %s%n",this.cliente);
        System.out.printf("Agencia: %d ",this.agencia);
        System.out.printf("Conta: %d%n",this.numero);
        System.out.printf("Valor Depositado: R$ %.2f%nNovo Saldo: R$ %.2f%n ",valor_deposito, this.saldo);
    }

    protected void imprimirExtratoSac(double valor_sacado) {
        System.out.printf("Titular: %s%n",this.cliente);
        System.out.printf("Agencia: %d ",this.agencia);
        System.out.printf("Conta: %d%n",this.numero);
        System.out.printf("Valor Sacado: R$ %.2f%nNovo Saldo: R$ %.2f%n ",valor_sacado, this.saldo);
    }

    protected void imprimirExtratoTra(double valor_transferido, String tipo_conta, String tipo_transferencia) {
        System.out.printf("Titular: %s%n",this.cliente);
        System.out.printf("Agencia: %d ",this.agencia);
        System.out.printf("Conta: %d - %s%n",this.numero, tipo_conta);
        System.out.printf("Tipo de transferência: %s%n", tipo_transferencia);
        System.out.printf("Valor Transferido: R$ %.2f%n",valor_transferido);
    }
}
