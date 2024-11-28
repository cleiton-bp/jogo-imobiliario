class Casa {
    String nome;
    TipoEnum tipo;
    double valor;
    Jogador proprietario;
    Casa proxima;

    public Casa(String nome, TipoEnum tipo, double valor) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.proprietario = null;
        this.proxima = null;
    }

    public boolean disponivelParaCompra() {
        return tipo == TipoEnum.IMOVEL && proprietario == null;
    }

    public double calcularAluguel() {
        return valor * 0.1;
    }
}