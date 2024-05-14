package application;

import entities.Cliente;
import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        char retornar = 'S';
        double LIMITE_MAXIMO = 1000.0;
        double LIMITE_MINIMO = 10.0;

        tela_inicial();
        int choice = sc.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("\"OPÇÃO INVÁLIDA! Escolha um operação válida\n: ");
            choice = sc.nextInt();
        }
        System.out.println();
        if (choice == 1) {
            System.out.println("BANCO FULANODE TAL");
            sc.nextLine();
            Cliente cliente = new Cliente();
            System.out.print("Informe o nome do cliente: ");
            cliente.setNome(sc.nextLine());
            Conta cc = new ContaCorrente(cliente);
            Conta cp = new ContaPoupanca(cliente);
            do {
                System.out.println("Qual operação deseja realizar: ");
                System.out.print("[1]-Deposito      [2]-Saque\n[3]-Transferência [4]-Extrato\n: ");
                int operacao = sc.nextInt();

                while (operacao < 1 || operacao > 4) {
                    System.out.print("OPÇÃO INVÁLIDA! Escolha um operação válida\n");
                    System.out.print("[1]-Deposito      [2]-Saque\n[3]-Transferência [4]-Extrato\n: ");
                    operacao = sc.nextInt();
                }
                switch (operacao) {
                    case 1: // DEPÓSITO
                        double valor_deposito;
                        System.out.println();
                        System.out.println("======DEPÓSITO======");
                        System.out.println("Em qual conta deseja depositar?");
                        System.out.print("[1]-Conta Corrente [2]-Conta Poupança\n: ");
                        int tipoConta = sc.nextInt();
                        while (tipoConta < 1 || tipoConta > 2) {
                            verificacao_de_escolhas("Conta Corrente", "Conta Poupança");
                            tipoConta = sc.nextInt();
                        }
                        if (tipoConta == 1) {
                            System.out.println();
                            System.out.println("======DEPÓSITO======");
                            System.out.println("======C. CORRENTE======");
                            System.out.println("Saldo: R$ " + cc.getSaldo());
                            System.out.print("Informe o valor de depósito: R$ ");
                            valor_deposito = sc.nextDouble();
                            if (valor_deposito < 0.0) {
                                System.out.print("Valor inválido! Informe um valor válido: R$ ");
                                sc.nextDouble();
                            } else {
                                cc.depositar(valor_deposito);
                            }
                            System.out.println();
                            cc.imprimirExtratoDeposito(valor_deposito);
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("======DEPÓSITO======");
                            System.out.println("======C. POUPANÇA======");
                            System.out.println("Saldo: R$ " + cp.getSaldo());
                            System.out.print("Informe o valor de depósito: R$ ");
                            valor_deposito = sc.nextDouble();
                            if (valor_deposito < 0.0) {
                                System.out.print("Valor inválido! Informe um valor válido: R$ ");
                                sc.nextDouble();
                            } else {
                                cp.depositar(valor_deposito);
                            }
                            System.out.println();
                            cp.imprimirExtratoDeposito(valor_deposito);
                            System.out.println();
                        }
                        System.out.print("Depósito realizado com sucesso!");
                        System.out.println();
                        break;
                    case 2: // SAQUE
                        double valor_saque;
                        System.out.println();
                        System.out.println("======SAQUE======");
                        System.out.println("De qual conta deseja sacar?");
                        System.out.print("[1]-Conta Corrente [2]-eConta Poupança\n: ");
                        tipoConta = sc.nextInt();
                        while (tipoConta < 1 || tipoConta > 2) {
                            verificacao_de_escolhas("Conta Corrente", "Conta Poupança");
                            tipoConta = sc.nextInt();
                        }
                        if (tipoConta == 1) {
                            System.out.println();
                            System.out.println("======C. CORRENTE======");
                            imprimir_tela_saque(cc.getSaldo());
                            valor_saque = sc.nextDouble();

                            while (valor_saque > 1000.0) {
                                avisos_de_valores_limites(valor_saque, cc.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                valor_saque = sc.nextDouble();
                            }
                            while (valor_saque < 10) {
                                avisos_de_valores_limites(valor_saque, cc.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                valor_saque = sc.nextDouble();
                            }
                            while (valor_saque > cc.getSaldo()) {
                                avisos_de_valores_limites(valor_saque, cc.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                valor_saque = sc.nextDouble();
                            }
                            cc.sacar(valor_saque);
                            System.out.println();
                            cc.imprimirExtratoSaque(valor_saque);
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("======C. POUPANÇA======");
                            imprimir_tela_saque(cp.getSaldo());
                            valor_saque = sc.nextDouble();
                            while (valor_saque > 1000.0) {
                                avisos_de_valores_limites(valor_saque, cp.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                valor_saque = sc.nextDouble();
                            }
                            while (valor_saque < 10) {
                                avisos_de_valores_limites(valor_saque, cp.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                valor_saque = sc.nextDouble();
                            }
                            while (valor_saque > cp.getSaldo()) {
                                avisos_de_valores_limites(valor_saque, cp.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                System.out.println("Saldo: R$ " + cp.getSaldo());
                                valor_saque = sc.nextDouble();
                            }
                            cp.sacar(valor_saque);
                            System.out.println();
                            cp.imprimirExtratoSaque(valor_saque);
                            System.out.println();
                        }
                        System.out.print("Deseja fazer outra operação(S/N)? ");
                        retornar = sc.next().toUpperCase().charAt(0);

                        while (retornar != 'S' && retornar != 'N') {
                            System.out.print("OPÇÃO INVÁLIDA! Digite [S]-SIM ou [N]-NÃO: ");
                            retornar = sc.next().toUpperCase().charAt(0);
                        }
                        break;
                    case 3: // TRANSFERENCIA
                        double valor_transferencia;
                        System.out.println();
                        System.out.println("======TRANSFERÊNCIA======");
                        System.out.println("Qual tipo de transferência deseja realizar?");
                        System.out.println("[1]-TED [2]-PIX\n: ");
                        int tipo_transferencia = sc.nextInt();
                        while (tipo_transferencia < 1 || tipo_transferencia > 2) {
                            verificacao_de_escolhas("TED", "PIX");
                            tipo_transferencia = sc.nextInt();
                        }
                        if (tipo_transferencia == 1) {
                            double TAXA_TED = 7.50;
                            System.out.println("Valor máximo para TED: R$ " + LIMITE_MAXIMO);
                            System.out.println("De qual conta deseja transferir?");
                            System.out.print("[1]-Conta Corrente [2]-Conta Poupança\n: ");
                            tipoConta = sc.nextInt();
                            while (tipoConta < 1 || tipoConta > 2) {
                                verificacao_de_escolhas("Conta Corrente", "Conta Poupança");
                                tipoConta = sc.nextInt();
                            }
                            System.out.print("Informe o valor do TED: R$ ");
                            valor_transferencia = sc.nextDouble();
                            if (tipoConta == 1) {
                                while (valor_transferencia > LIMITE_MAXIMO) {
                                    avisos_de_valores_limites(valor_transferencia, cc.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                    valor_transferencia = sc.nextDouble();
                                }while (valor_transferencia < LIMITE_MINIMO) {
                                    avisos_de_valores_limites(valor_transferencia, cc.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                    valor_transferencia = sc.nextDouble();
                                }
                                while (valor_transferencia > cc.getSaldo()) {
                                    avisos_de_valores_limites(valor_transferencia, cc.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                    valor_transferencia = sc.nextDouble();
                                }
                                cc.transferir(valor_transferencia, TAXA_TED, cp);
                                System.out.println("===================================");
                                System.out.println("TRANFERÊNCIA REALIZADA COM SUCESSO");
                                System.out.println("===================================");
                                cc.imprimirExtratoTransferencia(valor_transferencia, "Corrente", "TED");
                            } else {
                                System.out.print("Informe o valor do TED: R$ ");
                                valor_transferencia = sc.nextDouble();
                                while (valor_transferencia > LIMITE_MAXIMO) {
                                    avisos_de_valores_limites(valor_transferencia, cp.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                    valor_transferencia = sc.nextDouble();
                                }
                                while (valor_transferencia < LIMITE_MINIMO) {
                                    avisos_de_valores_limites(valor_transferencia, cp.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                    valor_transferencia = sc.nextDouble();
                                }
                                while (valor_transferencia > cp.getSaldo()) {
                                    avisos_de_valores_limites(valor_transferencia, cp.getSaldo(), LIMITE_MAXIMO, LIMITE_MINIMO);
                                    valor_transferencia = sc.nextDouble();
                                }
                                cp.transferir(valor_transferencia,TAXA_TED, cc);
                                System.out.println("===================================");
                                System.out.println("TRANFERÊNCIA REALIZADA COM SUCESSO");
                                System.out.println("===================================");
                                cc.imprimirExtratoTransferencia(valor_transferencia, "Poupança", "TED");
                            }
                        } else {
                            System.out.println("De qual conta deseja transferir?");
                            System.out.print("[1]-Conta Corrente [2]-Conta Poupança\n: ");
                            tipoConta = sc.nextInt();
                            while (tipoConta < 1 || tipoConta > 2) {
                                verificacao_de_escolhas("Conta Corrente", "Conta Poupança");
                                tipoConta = sc.nextInt();
                            }
                            if (tipoConta == 1) {
                                System.out.print("Informe o valor do PIX: R$ ");
                                valor_transferencia = sc.nextDouble();
                                while (valor_transferencia > cc.getSaldo()) {
                                    System.out.print("Saldo insuficiente para a realização da operação! Informe um valor válido: R$ ");
                                    System.out.println("Saldo: R$ " + cc.getSaldo());
                                    valor_transferencia = sc.nextDouble();
                                }
                                cc.transferir(valor_transferencia,0.0, cp);
                                System.out.println("===================================");
                                System.out.println("TRANFERÊNCIA REALIZADA COM SUCESSO");
                                System.out.println("===================================");
                                cc.imprimirExtratoTransferencia(valor_transferencia, "Corrente", "PIX");
                            } else {
                                System.out.print("Informe o valor do PIX: R$ ");
                                valor_transferencia = sc.nextDouble();
                                while (valor_transferencia > cp.getSaldo()) {
                                    System.out.print("Saldo insuficiente para a realização da operação! Informe um valor válido: R$ ");
                                    System.out.println("Saldo: R$ " + cp.getSaldo());
                                    valor_transferencia = sc.nextDouble();
                                }
                                cp.transferir(valor_transferencia,0.0, cc);
                                System.out.println("===================================");
                                System.out.println("TRANFERÊNCIA REALIZADA COM SUCESSO");
                                System.out.println("===================================");
                                cc.imprimirExtratoTransferencia(valor_transferencia, "Poupança", "PIX");
                            }
                        }
                        System.out.print("Deseja fazer outra operação(S/N)? ");
                        retornar = sc.next().toUpperCase().charAt(0);

                        while (retornar != 'S' && retornar != 'N') {
                            System.out.print("OPÇÃO INVÁLIDA! Digite [S]-SIM ou [N]-NÃO: ");
                            retornar = sc.next().toUpperCase().charAt(0);
                        }
                        break;
                    case 4: // EXTRATO
                        System.out.println();
                        System.out.println("======EXTRATOS======");
                        System.out.println("Qual conta deseja consultar o extrato?");
                        System.out.print("[1]-Conta Corrente [2]-Conta Poupança\n: ");
                        tipoConta = sc.nextInt();
                        while (tipoConta < 1 || tipoConta > 2) {
                            verificacao_de_escolhas("Conta Corrente", "Conta Poupança");
                            tipoConta = sc.nextInt();
                        }
                        if(tipoConta == 1){
                            cc.imprimirExtrato("Conta Corrente");
                        }else {
                            cp.imprimirExtrato("Conta Poupança");
                        }
                        System.out.print("Deseja fazer outra operação(S/N)? ");
                        retornar = sc.next().toUpperCase().charAt(0);

                        while (retornar != 'S' && retornar != 'N') {
                            System.out.print("OPÇÃO INVÁLIDA! Digite [S]-SIM ou [N]-NÃO: ");
                            retornar = sc.next().toUpperCase().charAt(0);
                        }
                        break;
                }
            } while (retornar != 'N');
            System.out.println("O entities.Banco Fulanode Tal agradece por utilizar nossos serviços!");
            System.out.println("ENCERRADO");

            sc.close();
        } else {
            System.out.println("O entities.Banco Fulanode Tal agradece por utilizar nossos serviços!");
            System.out.println("ENCERRADO");
        }
    }

    static void tela_inicial() {
        System.out.println("BANCO FULANODE TAL");
        System.out.print("[1]-Acessar entities.Conta [2]-Sair\n: ");
    }

    static void imprimir_tela_saque(double saldo_poupanca) {
        System.out.println("======SAQUE======");
        System.out.println("Saldo: R$ " + saldo_poupanca);
        System.out.println("Valor mínimo para saque: R$ 10.0");
        System.out.println("Valor máximo para saque: R$ 1000.0");
        System.out.print("Informe o valor de saque: R$ ");
    }

    static void verificacao_de_escolhas(String opcao1, String opcao2) {
        System.out.print("OPÇÃO INVÁLIDA! Escolha um operação válida\n");
        System.out.print("[1]-" + opcao1 + "[2]-" + opcao2 + "\n: ");
    }

    static void avisos_de_valores_limites(double valor_saque, double valor_saldo, double limite_maximo, double limite_minimo) {
        if (valor_saque > limite_maximo) {
            System.out.print("Valor informado maior que o limite máximo permitido! Informe um valor válido: R$ ");
        } else if (valor_saque < limite_minimo) {
            System.out.print("Valor informado menor que o limite mínimo permitido! Informe um valor válido: R$ ");
        } else if (valor_saque > valor_saldo) {
            System.out.print("Saldo insuficiente para a realização da operação! Informe um valor válido: R$ ");
            System.out.println("Saldo: R$ " + valor_saldo);
        }
    }

}