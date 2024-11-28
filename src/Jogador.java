import java.util.ArrayList;
import java.util.List;

class Jogador {
    String nome;
    double saldo;
    Casa posicaoAtual;
    List<Casa> propriedades;
    private int rodadasNaPrisao;

    public Jogador(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.propriedades = new ArrayList<>();
        this.rodadasNaPrisao = 0;
    }

    public boolean estaFalido() {
        return saldo <= 0;
    }

    public void atualizarSaldo(double valor) {
        saldo += valor;
        if (saldo < 0) {
            saldo = 0;
        }
    }

    public void adicionarPropriedade(Casa propriedade) {
        propriedades.add(propriedade);
    }

    public void removerPropriedade(Casa propriedade) {
        propriedades.remove(propriedade);
    }

    public void exibirStatus() {
        System.out.println("\n[STATUS] " + nome);
        System.out.println("- Saldo: R$" + saldo);
        System.out.println("- Propriedades: ");
        if (propriedades.isEmpty()) {
            System.out.println("  Nenhuma");
        } else {
            for (Casa prop : propriedades) {
                System.out.println("  - " + prop.nome);
            }
        }
    }

    public void enviarParaPrisao() {
        rodadasNaPrisao = 3;
    }

    public boolean estaNaPrisao() {
        return rodadasNaPrisao > 0;
    }

    public void decrementarRodadasNaPrisao() {
        if (rodadasNaPrisao > 0) {
            rodadasNaPrisao--;
        }
    }
}