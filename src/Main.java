public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();

        jogo.adicionarCasa(new Casa("Início", TipoEnum.INICIO, 0));
        for (int i = 1; i <= 10; i++) {
            jogo.adicionarCasa(new Casa("Imóvel " + i, TipoEnum.IMOVEL, 150 + i * 10));
        }
        jogo.adicionarCasa(new Casa("Imposto", TipoEnum.IMPOSTO, 0));
        jogo.adicionarCasa(new Casa("Restituição", TipoEnum.RESTITUICAO, 0));
        jogo.adicionarCasa(new Casa("Sorte", TipoEnum.SORTE, 0));
        jogo.adicionarCasa(new Casa("Revés", TipoEnum.REVES, 0));
        jogo.adicionarCasa(new Casa("Prisão", TipoEnum.PRISAO, 0));

        jogo.adicionarJogador("Jogador 1");
        jogo.adicionarJogador("Jogador 2");

        jogo.configurarJogo(1000, 200, 50);
        jogo.iniciar();
    }
}