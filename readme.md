# Princípios SOLID

Classe: src/main/java/tech/ada/banco/service/saque/SaquePFImpl.java     
Linha: 14   
Princípios: **Responsabilidade única**  
Classe trata somente da ação de saque.

Classe: src/main/java/tech/ada/banco/service/investimento/InvestirPFImpl.java   
Linha: 16   
Princípios: **Responsabilidade única**  
Classe trata somente da ação de saque.

Classe: src/main/java/tech/ada/banco/service/ContaCorrentePFService.java    
Linha: 30   
Princípios: **Aberto-Fechado**, **Segregação de interfaces**    
Classe possibilita a inclusão de novas implementações sem precisar alterar o código já existente.

Classe: src/main/java/tech/ada/banco/service/ContaInvestimentoPFService.java    
Linha: 31   
Princípios: **Aberto-Fechado**, **Segregação de interfaces**    
Classe possibilita a inclusão de novas implementações sem precisar alterar o código já existente.

# Padrões de projeto (Design Patterns)

## Builder
Classe: src/main/java/tech/ada/banco/login/JwtService.java  
Linha: 68

Classe: src/main/java/tech/ada/banco/designPartner/ClientePFBuilder.java 
Linha: 71

Padrão que possibilita a inclusão de atributos um a um para construir o objeto. 

## Bridge
Classe: src/main/java/tech/ada/banco/designPartner/ExemploBridge.java 
Linha: 104   
Padrão que possibilita o uso de comportamentos independente da implamentação escolhida.  

## Singleton
Classe: src/main/java/tech/ada/banco/designPartner/ExemploSigleton.java
Linha: 7   
Padrão que garante que exista apenas um objeto do tipo, provendo um acesso global a instância.

## Prototype
Classe: src/main/java/tech/ada/banco/designPartner/ExemploPrototype.java
Linha: 13
Padrão de projeto criacional que permite copiar objetos existentes sem fazer seu código ficar dependente de suas classes.

