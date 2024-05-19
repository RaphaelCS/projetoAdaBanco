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
Classe usuario possibilita a inclusão de atributos um a um para construir o objeto. 

