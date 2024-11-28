import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Jogo {
    private Tabuleiro tabuleiro;
    private List<Jogador> jogadores;
    private double salario;
    private int maxRodadas;

    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.jogadores = new ArrayList<>();
    }

    public void configurarJogo(double saldoInicial, double salario, int maxRodadas) {
        this.salario = salario;
        this.maxRodadas = maxRodadas;
        for (Jogador jogador : jogadores) {
            jogador.saldo = saldoInicial;
        }
    }

    public void adicionarJogador(String nome) {
        jogadores.add(new Jogador(nome, 0));
    }

    public void adicionarCasa(Casa casa) {
        tabuleiro.adicionarCasa(casa);
    }

    public void iniciar() {
        if (jogadores.size() < 2 || tabuleiro.inicio == null) {
            System.out.println("Erro: Número insuficiente de jogadores ou casas no tabuleiro!");
            return;
        }

        for (Jogador jogador : jogadores) {
            jogador.posicaoAtual = tabuleiro.inicio;
        }

        System.out.println("\n=== Início do Jogo ===");
        tabuleiro.imprimirTabuleiro();

        Random dado = new Random();
        Scanner scanner = new Scanner(System.in);

        for (int rodada = 1; rodada <= maxRodadas; rodada++) {
            System.out.println("\n=== Rodada " + rodada + " ===");

            for (Jogador jogador : jogadores) {
                if (jogador.estaFalido()) {
                    System.out.println(jogador.nome + " está falido e será ignorado.");
                    continue;
                }

                if (jogador.estaNaPrisao()) {
                    System.out.println(jogador.nome + " está na prisão e perderá esta rodada.");
                    jogador.decrementarRodadasNaPrisao();
                    continue;
                }

                System.out.println("\nVez do jogador: " + jogador.nome);
                boolean turnoFinalizado = false;
                while (!turnoFinalizado) {
                    Menu.exibirMenu();
                    int escolha = scanner.nextInt();

                    switch (escolha) {
                        case 1:
                            int resultadoDado = dado.nextInt(6) + 1;
                            System.out.println("\n" + jogador.nome + " jogou o dado: " + resultadoDado);
                            movimentarJogador(jogador, resultadoDado);
                            processarCasa(jogador);
                            turnoFinalizado = true;
                            break;
                        case 2:
                            if (jogador.posicaoAtual.tipo == TipoEnum.IMOVEL && jogador.posicaoAtual.disponivelParaCompra()) {
                                if (jogador.saldo >= jogador.posicaoAtual.valor) {
                                    jogador.atualizarSaldo(-jogador.posicaoAtual.valor);
                                    jogador.posicaoAtual.proprietario = jogador;
                                    jogador.adicionarPropriedade(jogador.posicaoAtual);
                                    System.out.println("Comprou o imóvel: " + jogador.posicaoAtual.nome);
                                } else {
                                    System.out.println("Saldo insuficiente para comprar o imóvel!");
                                }
                            } else {
                                System.out.println("Não é possível comprar esta casa!");
                            }
                            break;
                        case 3:
                            jogador.exibirStatus();
                            break;
                        default:
                            System.out.println("Opção inválida! Tente novamente.");
                    }
                }
            }
        }

        finalizarJogo();
    }

    private void movimentarJogador(Jogador jogador, int passos) {
        for (int i = 0; i < passos; i++) {
            jogador.posicaoAtual = jogador.posicaoAtual.proxima;
        }
        System.out.println(jogador.nome + " parou na casa: " + jogador.posicaoAtual.nome);
        exibirInformacoesCasa(jogador.posicaoAtual);
    }

    private void exibirInformacoesCasa(Casa casa) {
        System.out.println("Informações da casa:");
        System.out.println("- Nome: " + casa.nome);
        System.out.println("- Tipo: " + casa.tipo);
        System.out.println("- Valor: R$" + casa.valor);
        if (casa.proprietario != null) {
            System.out.println("- Proprietário: " + casa.proprietario.nome);
        } else if (casa.tipo == TipoEnum.IMOVEL) {
            System.out.println("- Disponível para compra");
        }
    }

    private void processarCasa(Jogador jogador) {
        Casa casa = jogador.posicaoAtual;
        switch (casa.tipo) {
            case INICIO:
                jogador.atualizarSaldo(salario);
                System.out.println("Passou pelo início! Recebeu R$" + salario);
                break;
            case IMOVEL:
                if (casa.disponivelParaCompra()) {
                    System.out.println("Você pode comprar este imóvel por R$" + casa.valor);
                } else if (casa.proprietario != jogador) {
                    double aluguel = casa.calcularAluguel();
                    jogador.atualizarSaldo(-aluguel);
                    casa.proprietario.atualizarSaldo(aluguel);
                    System.out.println("Pagou aluguel de R$" + aluguel + " para " + casa.proprietario.nome);
                }
                break;
            case IMPOSTO:
                double imposto = jogador.saldo * 0.05;
                jogador.atualizarSaldo(-imposto);
                System.out.println("Pagou imposto de R$" + imposto);
                break;
            case RESTITUICAO:
                double recompensa = salario * 0.1;
                jogador.atualizarSaldo(recompensa);
                System.out.println("Recebeu restituição de R$" + recompensa);
                break;
            case SORTE:
                double bonus = new Random().nextInt(200) + 50; // Bônus entre 50 e 250
                jogador.atualizarSaldo(bonus);
                System.out.println("Sorte! Ganhou R$" + bonus);
                break;
            case REVES:
                double perda = new Random().nextInt(200) + 50; // Perda entre 50 e 250
                jogador.atualizarSaldo(-perda);
                System.out.println("Revés! Perdeu R$" + perda);
                break;
            case PRISAO:
                jogador.enviarParaPrisao();
                System.out.println(jogador.nome + " foi enviado para a prisão!");
                break;
        }
    }

    private void finalizarJogo() {
        Jogador vencedor = jogadores.stream()
                .filter(j -> !j.estaFalido())
                .max((j1, j2) -> Double.compare(j1.saldo, j2.saldo))
                .orElse(null);

        System.out.println("\n=== Fim do Jogo ===");
        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor.nome + " com saldo de R$" + vencedor.saldo);
        } else {
            System.out.println("Todos os jogadores faliram!");
        }
    }
}