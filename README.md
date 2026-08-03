Aqui está a tradução:

RPG Java

Um sistema de batalha por turnos em Java puro, com progressão de personagem, mecânicas de combate e um sistema de menu interativo.

Funcionalidades
Sistema de batalha por turnos com mecânica de esquiva
3 classes de personagem para escolher (Guerreiro, Mago, Arqueiro)
3 níveis de dificuldade (Fácil, Normal, Difícil)
Sistema de upgrade de personagem
Loja para comprar poções de cura e força
Gerenciamento de inventário
Menu interativo (Batalha, Loja, Inventário)
Sistema de save para preservar seu progresso
Tecnologias
Java 21
FileWriter + BufferedWriter para o sistema de save
Java puro (sem bibliotecas externas)
Estrutura do Projeto
src/
└── main/
    └── java/
        ├── controller/
        │   ├── GameController.java
        │   └── InventoryController.java
        ├── domain/
        │   ├── entity/
        │   │   ├── Entity.java
        │   │   ├── Player.java
        │   │   └── Enemy.java
        │   ├── enums/
        │   │   ├── CharacterClass.java
        │   │   ├── Difficulty.java
        │   │   └── ConsoleColor.java
        │   └── item/
        │       ├── Potion.java
        │       ├── HealPotion.java
        │       └── StrengthPotion.java
        ├── game/
        │   ├── BattleSystem.java
        │   └── Shop.java
        └── utils/
            ├── GameSaver.java
            └── InputValidation.java
Como Jogar

Execute a aplicação e siga o menu interativo:

Criação de Personagem

Digite seu nome:
Escolha sua classe:
  1. Guerreiro (Força: 10, Defesa: 8)
  2. Mago      (Força: 8,  Defesa: 10)
  3. Arqueiro  (Força: 7,  Defesa: 8)

Escolha a dificuldade:
  1. Fácil
  2. Normal
  3. Difícil

Menu Principal

=== Menu Principal ===
1. Batalha
2. Loja
3. Inventário
4. Salvar
5. Sair

Sistema de Batalha

Combate por turnos contra inimigos
Mecânica de esquiva para evitar ataques recebidos
Derrote inimigos para ganhar ouro e avançar nas fases

Loja

Compre Poções de Cura para restaurar vida
Compre Poções de Força para aumentar o ataque
Gerencie seu inventário estrategicamente

Progressão

Melhore os atributos do seu personagem após as batalhas
Enfrente inimigos mais fortes conforme avança nas fases
A dificuldade afeta o dano e o bônus de vida dos inimigos

Sistema de Save

O progresso é salvo em um arquivo .txt usando FileWriter e BufferedWriter, armazenando os atributos do jogador, fase, classe, dificuldade e inventário.

saves/
└── save.txt
