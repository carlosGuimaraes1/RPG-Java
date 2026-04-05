# RPG Java

A turn-based RPG battle system built in pure Java, featuring character progression, combat mechanics, and an interactive menu system.

## Features

- Turn-based battle system with dodge mechanics
- 3 character classes to choose from (Warrior, Mage, Archer)
- 3 difficulty levels (Easy, Normal, Hard)
- Character upgrade system
- Shop to buy healing and strength potions
- Inventory management
- Interactive menu (Battle, Shop, Inventory)
- Save system to preserve your progress

## Technologies

- Java 21
- FileWriter + BufferedWriter for save system
- Pure Java (no external libraries)

## Project Structure

```
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
```

## How to Play

Run the application and follow the interactive menu:

### Character Setup
```
Enter your name:
Choose your class:
  1. Warrior (Strength: 10, Defense: 8)
  2. Mage    (Strength: 8,  Defense: 10)
  3. Archer  (Strength: 7,  Defense: 8)

Choose difficulty:
  1. Easy
  2. Normal
  3. Hard
```

### Main Menu
```
=== Main Menu ===
1. Battle
2. Shop
3. Inventory
4. Save
5. Exit
```

### Battle System
- Turn-based combat against enemies
- Dodge mechanic to avoid incoming attacks
- Defeat enemies to earn gold and progress through stages

### Shop
- Buy Heal Potions to restore life
- Buy Strength Potions to boost attack
- Manage your inventory strategically

### Progression
- Upgrade your character stats after battles
- Face stronger enemies as you advance stages
- Difficulty affects enemy damage and life bonuses

## Save System

Progress is saved to a `.txt` file using `FileWriter` and `BufferedWriter`, storing player stats, stage, class, difficulty, and inventory.

```
saves/
└── save.txt
```
