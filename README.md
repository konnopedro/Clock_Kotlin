⏰ Tun Tun Clock
Desenvolvimento de Aplicativos Móveis — Trabalho 1

Disciplina: Desenvolvimento de Aplicativos Móveis

Integrantes:
Daniel Luiz — Relógio
Daniel Rocha — Cronômetro
Pedro Konno — Timer

1. Sobre o aplicativo

O Tun Tun Clock é um aplicativo de controle de tempo desenvolvido em Kotlin utilizando Jetpack Compose.

A proposta é reunir três funções principais em um único aplicativo:

Relógio;
Cronômetro;
Timer.

O aplicativo foi pensado para pessoas que precisam consultar o horário e controlar períodos de tempo durante atividades como estudos, trabalho, exercícios e tarefas do dia a dia.

A identidade visual utiliza um fundo escuro, cards e tons de roxo, buscando uma interface simples, organizada e fácil de utilizar.

2. Telas desenvolvidas
🕐 Relógio

A tela apresenta:

Nome do aplicativo;
Saudação;
Data;
Horário atual;
Localização;
Próximo alarme;
Botão para adicionar alarme;
Fuso horário;
Barra inferior de navegação.

O horário é atualizado automaticamente.

⏱️ Cronômetro

A tela apresenta:

Título e status;
Contador;
Área circular para exibição do tempo;
Botão para iniciar/pausar;
Botão para zerar;
Card de última volta.

O cronômetro utiliza estado para atualizar o tempo em tela.

⏳ Timer

A tela apresenta:

Título;
Duração selecionada;
Atalhos de 5, 10 e 15 minutos;
Campo para informar os minutos;
Botão para iniciar/pausar;
Mensagem informativa.

O Timer utiliza remember e mutableStateOf para controlar o tempo e possui Toast para informar quando é iniciado ou pausado.

3. Fluxo de navegação

O fluxo principal planejado para o aplicativo é:

              ┌───────────────┐
              │    RELÓGIO    │
              └───────┬───────┘
                      │
            ┌─────────┴─────────┐
            │                   │
            ▼                   ▼
     ┌─────────────┐     ┌─────────────┐
     │ CRONÔMETRO  │     │    TIMER    │
     └─────────────┘     └─────────────┘

          Barra inferior de navegação


A navegação visual entre as três funções é feita pela barra inferior.

A troca de telas foi mantida simples porque a navegação real entre telas ainda não foi trabalhada na disciplina.

4. Canvas
Qual problema o aplicativo resolve?

O aplicativo facilita o controle do tempo ao reunir relógio, cronômetro e timer em um único lugar.


Para quem é?

O público imaginado são estudantes, trabalhadores e qualquer pessoa que precise controlar períodos de tempo durante sua rotina.


Por que alguém abriria hoje e amanhã?

O usuário pode abrir o aplicativo sempre que precisar consultar o horário ou controlar uma atividade. Como essa necessidade pode acontecer diariamente, o aplicativo pode fazer parte da rotina.


Qual é a principal função?

A principal função é permitir que o usuário controle o tempo de maneira rápida e simples.

Como poderia gerar valor ou dinheiro?

Em um produto real, poderia existir uma versão gratuita com recursos básicos e uma versão premium com recursos adicionais, como personalização, sons, temas e estatísticas. Também poderia ser utilizada publicidade na versão gratuita.


Decisões de design

Como o objetivo é facilitar o controle do tempo, a tela inicial apresenta diretamente o horário atual. As funções de cronômetro e timer ficam disponíveis na barra inferior para acesso rápido.


5. Tecnologias e conceitos utilizados

O projeto foi desenvolvido utilizando:

Kotlin;
Android Studio;
Jetpack Compose;
Material 3.

Durante a implementação foram utilizados os conceitos solicitados no trabalho:

Row;
Column;
Box;
Button;
Text;
OutlinedTextField;
Card;
Scaffold;
remember;
mutableStateOf;
Toast;
padding;
Arrangement;
Alignment;
Lambdas através de onClick e onValueChange.

As três telas possuem funções Composable próprias para organizar os componentes.


6. Mockups e planejamento

Antes da programação, o grupo realizou o planejamento das telas.

10 telas no papel

<img width="2664" height="3904" alt="1000220197" src="https://github.com/user-attachments/assets/3460e971-7570-48b5-84aa-bfc45efc5ad1" />


5 telas no mockup digital

<img width="1600" height="702" alt="telas canvas" src="https://github.com/user-attachments/assets/6a7b8560-4818-429b-b141-7061fde99750" />

Diagrama de fluxo

3. Fluxo de navegação

O usuário pode alternar entre as três funções principais do aplicativo utilizando a barra inferior de navegação.

flowchart TD
    A["RELÓGIO<br/>Tela principal<br/><br/>Horário atual<br/>Próximo alarme<br/>Fuso horário"]
    
    B["CRONÔMETRO<br/><br/>Contador<br/>Iniciar / Pausar<br/>Zerar<br/>Última volta"]
    
    C["TIMER<br/><br/>Contagem regressiva<br/>5 / 10 / 15 minutos<br/>Campo de minutos<br/>Iniciar / Pausar"]

    A -->|"Barra inferior<br/>Crono"| B
    B -->|"Barra inferior<br/>Timer"| C
    C -->|"Barra inferior<br/>Relógio"| A

    A -->|"Barra inferior<br/>Timer"| C
    B -->|"Barra inferior<br/>Relógio"| A
    C -->|"Barra inferior<br/>Crono"| B

7. Acompanhamento / Progresso

O desenvolvimento começou com o planejamento das telas e posteriormente passou para a implementação no Android Studio.

Uma das principais decisões foi criar três funções relacionadas ao controle de tempo, mantendo uma identidade visual semelhante entre elas.

Durante a programação, tivemos como dificuldade principal organizar os componentes e trabalhar com estados para atualizar o cronômetro e o timer em tempo real. A solução foi dividir as telas em funções Composable menores e utilizar remember e mutableStateOf.

Também foram necessários alguns ajustes entre o mockup e a implementação para adaptar o projeto aos conteúdos de Jetpack Compose trabalhados até o momento.

Registros do desenvolvimento

<img width="899" height="1599" alt="unnamed" src="https://github.com/user-attachments/assets/20a47268-5828-4411-a5f1-45c6abab7a8e" />
<img width="3000" height="4000" alt="1000123155" src="https://github.com/user-attachments/assets/6f51c876-77e7-4b5e-a2a0-21c57cfbaff6" />
<img width="3000" height="4000" alt="1000123153" src="https://github.com/user-attachments/assets/a37cf34b-ec21-447e-9ac3-4df6f3e53a1c" />
<img width="3000" height="4000" alt="1000123154" src="https://github.com/user-attachments/assets/71a51309-5a94-4e25-bd53-a0dfa39ed4df" />



8. Repositório

GitHub: https://github.com/konnopedro/Clock_Kotlin

9. Conclusão

O projeto Tun Tun Clock foi desenvolvido como aplicação prática dos primeiros conceitos de Jetpack Compose apresentados na disciplina.

As três telas programadas possuem diferentes componentes e organizações de layout, utilizando estados, botões, cards, campos de entrada, Row, Column, Box e outros recursos trabalhados em aula.

As funcionalidades mais avançadas, como navegação real e persistência de dados, poderão ser implementadas posteriormente conforme esses conteúdos forem apresentados na disciplina.
