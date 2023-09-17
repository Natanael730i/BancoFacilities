import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ContaCorrente extends Conta{

    Scanner ler = new Scanner(System.in);
    Double saldo = 0.0;

    public ContaCorrente(){
        super();

    }
    public ContaCorrente(Integer numeroConta, Cliente cliente) {
        super(numeroConta, cliente);
    }

    public ContaCorrente criarConta(){
        Random random = new Random();
        int numeroConta = random.nextInt(90000) + 10000;
        System.out.println("Digite o seu Nome por favor: ");
        String nome = ler.nextLine();
        System.out.println("Digite o seu CPF por favor? (Digite com os pontos e o traço por favor)");
        String cpf = ler.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        ContaCorrente conta = new ContaCorrente(numeroConta, cliente);

        System.out.println("O número da sua conta é: " + conta.numeroConta);

        return conta;
    }

    public ContaCorrente depositar(List<ContaCorrente> contas, Integer numeroConta){
        ContaCorrente contaCorrente = new ContaCorrente();
        System.out.println("Digite o valor para depositar por favor: ");
        double valorDeposito = ler.nextDouble();
        do {
            if (valorDeposito > 0){
                for (ContaCorrente conta : contas){
                    if (numeroConta.equals(conta.numeroConta)) {
                        conta.saldo += valorDeposito;
                        System.out.printf("""
                                Olá %s,
                                Seu depósito de %.2f, foi efetuado com sucesso!\n\n""", conta.cliente.nome, valorDeposito);
                        ExecutarOperacao.temConta(numeroConta);
                    }
                }
            }else {
                System.out.println("Valor para depósito inválido, digite outro Valor por favor:");
                valorDeposito = ler.nextDouble();
            }
        }while (valorDeposito < 0);


        return contaCorrente;
    }

    public void retirar(List<ContaCorrente> contas, int numeroConta){
        for (ContaCorrente conta : contas){
            if(conta.numeroConta.equals(numeroConta)){
                if (conta.saldo>0){
                    System.out.println("Digite o valor que precisa sacar: ");
                    double valorSacar = ler.nextDouble();
                        if (valorSacar <= conta.saldo){
                            conta.saldo -= valorSacar;
                            System.out.printf("""
                                          Valor de R$ %.2f retirado de sua conta!
                                          """, valorSacar);
                            ExecutarOperacao.temConta(conta.numeroConta);
                        }else {
                            do {
                                System.out.printf("""
                                        O valor a sacar é maior do que o valor que tem na conta
                                        Escolha outro valor por favor!
                                        Ou caso deseje encerrar, Digite -1!
                                        """);
                                valorSacar = ler.nextDouble();
                                if (valorSacar == -1){
                                    ExecutarOperacao.temConta(conta.numeroConta);
                                }
                            }while(valorSacar >conta.saldo);
                            conta.saldo -= valorSacar;
                            System.out.printf("""
                                          Valor de R$ %.2f retirado de sua conta!
                                          """, valorSacar);
                        }
                }else {
                    System.out.printf("""
                                       Infelizmente o valor em sua conta é de R$ %.2f
                                       Estarei lhe enviando novamente para o menú!
                                       """, conta.saldo);
                    ExecutarOperacao.temConta(conta.numeroConta);
                }
            }
        }
    }

    public void consultarSaldo(List<ContaCorrente> contas,int numeroConta){
        for (ContaCorrente conta: contas){
            if (conta.numeroConta.equals(numeroConta)){
                System.out.printf("""
                        O saldo em sua conta é de %.2f,
                        %n""", conta.saldo);
                ExecutarOperacao.temConta(numeroConta);
            }
        }

    }

}
