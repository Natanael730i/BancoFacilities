import java.util.ArrayList;
import java.util.Scanner;

public class ExecutarOperacao {
    static ArrayList<ContaCorrente> contas = new ArrayList<>();
    static Scanner ler = new Scanner(System.in);
    static ContaCorrente contaCorrente = new ContaCorrente();
    public static void main(String[] args) {
        menu();

    }

    public static void menu(){
        int navegacaoMenu;

        System.out.println("""
                Bem Vindo! O que precisa fazer?
                Digite 1 para criar uma conta!
                Digite 2 para caso já tenha uma conta!
                """);
        navegacaoMenu = ler.nextInt();

        while(!(navegacaoMenu == 1 || navegacaoMenu == 2)){
            System.out.println("""
                    Você digitou algo inválido, digite novamente por favor!
                    Digite 1 para criar uma conta!
                    Digite 2 para caso já tenha uma conta!
                    """);
            navegacaoMenu = ler.nextInt();
        }

// ------------------------------------------------------------------------------------------------------------------- //

        if(navegacaoMenu == 1){
            naoTemConta();
        } else {
            System.out.println("Digite o número de sua conta por favor: ");
            int numeroConta = ler.nextInt();
            while (numeroConta < 10000 || numeroConta > 99999){
                System.out.println("""
                    Número de conta Inválido,
                    digite novamente por favor:
                    """);
                numeroConta = ler.nextInt();
            }
            temConta(numeroConta);
        }
    }


    /*

    Aqui tem outro método, que neste ponto, vou fazer as operações, caso o cliente não tenha Conta ainda;

    */
    public static void naoTemConta(){
        ContaCorrente conta;
        ContaCorrente contaCorrente = new ContaCorrente();
        conta = contaCorrente.criarConta();
        contas.add(conta);
        System.out.println("""
                           Caso deseje depositar dinheiro na conta digite 1!
                           Ou caso deseje encerrar o processo Digite 2!
                           """);
        int deposita = ler.nextInt();
        if (deposita == 1){
            contaCorrente.depositar(contas, conta.numeroConta);
        }
        else {
            menu();
        }
    }

    /*
    *
    * Aqui tem outro método que será utilizado para chamar as operações, caso o cliente já tenha conta
    *
    * */

    public static void temConta (int numeroConta) {
        for (Conta contasSalvas: contas) {
            if (contasSalvas.numeroConta == numeroConta){
                System.out.printf("""
                        Olá %S
                        O que deseja fazer?
                        Digite 1 para depositar em sua conta
                        Digite 2 se Deseja Retirar algum valor
                        Ou Digite 3 para verificar o saldo
                        """, contasSalvas.cliente.nome);
                int temConta = ler.nextInt();

                switch (temConta) {
                    case 1 -> contaCorrente.depositar(contas, numeroConta);
                    case 2 -> contaCorrente.retirar(contas, numeroConta);
                    case 3 -> contaCorrente.consultarSaldo(contas, numeroConta);
                    default -> {
                        System.out.println("Valor digitado inválido!");
                        temConta(numeroConta);
                    }
                }


            }
        }


    }
}



