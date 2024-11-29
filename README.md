# Jogo Imobiliário 

## Descrição
Este é um jogo simples de implementado em Java, onde os jogadores podem comprar propriedades, pagar impostos, receber restituições, ganhar ou perder dinheiro com sorte, e até mesmo ir para a prisão.

---

## Estrutura do Projeto
O projeto é composto pelas seguintes classes:

- **Main**: Classe principal que inicializa e inicia o jogo.
- **Jogo**: Gerencia o fluxo do jogo, incluindo a configuração inicial, a execução das rodadas e a finalização do jogo.
- **Tabuleiro**: Representa o tabuleiro do jogo, que é uma lista circular de casas.
- **Jogador**: Representa um jogador no jogo, incluindo seu saldo, propriedades e posição atual no tabuleiro.
- **Casa**: Representa uma casa no tabuleiro, que pode ser de diferentes tipos (início, imóvel, imposto, restituição, sorte, prisão).
- **TipoEnum**: Enumeração que define os diferentes tipos de casas no tabuleiro.
- **Menu**: Exibe o menu de opções para o jogador durante o jogo.

---

## Como o Jogo Foi Construído

### **Main**
A classe `Main` é responsável por configurar e iniciar o jogo, onde cria instâncias das casas e adiciona jogadores ao jogo.

### **Jogo**
A classe `Jogo` gerencia o fluxo do jogo. Ela contém métodos para:
- Configurar o jogo.
- Adicionar jogadores e casas.
- Iniciar o jogo.
- Movimentar jogadores.
- Processar as ações nas casas.
- Finalizar o jogo.

Durante cada rodada, os jogadores jogam o dado, movem-se pelo tabuleiro e realizam ações com base na casa em que param.

### **Tabuleiro**
A classe `Tabuleiro` representa o tabuleiro do jogo como uma lista circular de casas. Ela contém métodos para:
- Adicionar casas.
- Imprimir o estado do tabuleiro.

### **Jogador**
A classe `Jogador` representa um jogador no jogo. Ela contém:
- Nome do jogador.
- Saldo.
- Posição atual no tabuleiro.
- Propriedades.
- Estado de prisão.

Inclui métodos para:
- Atualizar o saldo.
- Adicionar e remover propriedades.
- Exibir o status do jogador.
- Gerenciar o estado de prisão.

### **Casa**
A classe `Casa` representa uma casa no tabuleiro. Ela contém:
- Nome.
- Tipo.
- Valor.
- Proprietário.
- Referência à próxima casa na lista circular.

Inclui métodos para:
- Verificar se a casa está disponível para compra.
- Calcular o aluguel.

### **TipoEnume**
A enumeração `TipoEnume` define os diferentes tipos de casas no tabuleiro:
- `INICIO`
- `IMOVEL`
- `IMPOSTO`
- `RESTITUICAO`
- `SORTE`
- `REVES`
- `PRISAO`

### **Menu**
A classe `Menu` exibe o menu de opções para o jogador durante o jogo. As opções incluem:
- Jogar o dado.
- Comprar um imóvel.
- Exibir o status do jogador.

---

## Como Jogar

1. **Configuração Inicial**: O jogo é configurado na classe `Main`, onde são criadas as casas e adicionados os jogadores.
2. **Início do Jogo**: Todos os jogadores começam na casa "Início", onde é impresso para mostrar a disposição das casas.
3. **Rodadas**: Em cada rodada, cada jogador, por sua vez, pode escolher entre:
    - Jogar o dado.
    - Comprar um imóvel (se estiver disponível).
    - Exibir seu status.
4. **Jogar o Dado**:
    - O jogador joga o dado e se move pelo número de casas correspondente ao resultado.
5. **Comprar Imóvel**:
    - Se o jogador parar em uma casa de imóvel disponível para compra, ele pode optar por comprá-la, desde que tenha saldo suficiente.
6. **Exibir Status**:
    - O jogador pode exibir seu status atual.

### **Ações nas Casas**
- **INICIO**: O jogador recebe um salário.
- **IMOVEL**: O jogador pode comprar o imóvel ou pagar aluguel, caso já tenha um proprietário.
- **IMPOSTO**: O jogador paga um imposto.
- **RESTITUICAO**: O jogador recebe uma restituição.
- **SORTE**: O jogador ganha um bônus aleatório.
- **REVES**: O jogador perde um valor aleatório.
- **PRISAO**: O jogador é enviado para a prisão e perde três rodadas.

### 7. **Finalização do Jogo**
- Após o número de rodadas definidas no início do jogo, o fluxo é encerrado.
- O saldo de cada jogador é avaliado.
- O jogador com o maior saldo é declarado o **vencedor**.

---

## Possíveis Melhorias Futuras
- **Interface Gráfica (GUI)**: Implementar uma interface gráfica para tornar o jogo mais interativo.
- **Mais Tipos de Casas**:
    - Casas com eventos especiais.
    - Propriedades que podem ser desenvolvidas (ex.: construção de hotéis).
- **Sistema de Estatísticas**: Exibir relatórios e históricos das partidas, como dados de desempenho dos jogadores.

---
