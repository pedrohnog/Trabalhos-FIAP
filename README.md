# Trabalhos à serem realizados durante o MBA de desenvolvimento Java, SOA e Internet das Coisas - Turma 29SCJ

## Integrantes
- Everton Mendes
- Pedro Nogueira
- Rafael Lima
- Victor Coimbra

### Trabalho 1
Projeto Final de Avaliação - Fundamentos da tecnologia Java e modelagem visual UML 2.0

Prof. Michel P. Fernandes

#### Opção 1 - Twitter Small Analytics

Atualmente usuários de redes sociais contribuem para entendimento de informações massivas que são postados e atualizados continuamente. Tais informações podem servir de subsídio para melhor conhecimento e refinamento de diversos fatos que contribui para tomada de decisões rápidas e eficazes.

Diante disto, o objetivo do projeto é construir uma aplicação Java que seja capaz de se integrar a API do Twitter para buscar as seguintes informações de uma determinada hashtag.

1. Quantidade por dia de tweets da última semana.
2. Quantidade por dia de retweets da última semana.
3. Quantidade por dia de favoritações da última semana.
4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome.
5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente.

Cada grupo deve escolher um hashtag de busca dos disponíveis:
- #java
- #jvm
- #javaone
- #openjdk
- #java7
- #java8
- #java9

Ao final, tendo todas os dados reunidos, deverá ser gerado um tweet referenciando o professor @michelpf até a data do término.

#### Opção 2 - Telegram Bot Digital Bank

Com o avanço da tecnologia móvel no mundo inteiro, explorando, dentre outras formas pelos comunicadores instantâneos aliado aos assistentes virtuais que tem se mostrado cada vez mais inteligentes e ajudando as pessoas a cumprirem suas tarefas do cotidiano estabelecendo uma plataforma de serviços de autoatendimento, com possibilidades também abertas as empresas.

Os bancos financeiros, por sua vez, estão nascendo ou migrando para a realidade digital, onde não é obrigatório ter agências físicas para obter seus principais serviços. Com o acesso massificado da internet, é possível realizar desde a abertura da conta e transações de qualquer natureza remotamente.

Por outro lado, os bots, abreviação de robots, tem sido utilizado nos principais comunicadores de mensagens, como o Telegram e Facebook. Sua facilidade e flexibilidade de integração permite utilizá-los como assistentes pessoais para resolução de diversas questões, tais como: obter segunda via de contas, solicitação de serviços e solicitação de informações. Os bots substituem o atendimento humano pelo virtual, mais efetivo e, podendo ser explorado para ser adaptado a cada tipo de cliente.

O objetivo desta atividade é criar um bot Telegram que simule um banco virtual, e seja possível implementar os seguintes comportamentos:
1. Tela de boas-vindas do banco
2. Criação de conta
3. Modificação de conta
4. Inclusão de dependentes (conta-conjunta)
5. Exibição dos dados do titular e dependentes
6. Depósito
7. Saque (custo do serviço R$ 2,50)
8. Solicitação de extrato (custo do serviço R$ 1,00)
9. Solicitação de empréstimo, cujo prazo máximo é de 3 anos e valor máximo é de 40 vezes o saldo da conta (custo do serviço R$ 15,00 além de juros de 5% a.m.)
10. Exibição de saldo devedor do empréstimo e prazo de pagamento
11. Exibição dos lançamentos detalhada, com somatória ao final
12. Exibição das retiradas, com somatória ao final
13. Exibição das tarifas de serviço, com somatória ao final dos serviços que já foram utilizados na conta
14. Tela de ajuda

Todas as operações devem ser armazenadas para futuras consultas em arquivo texto.

#### Considerações

As propostas apresentadas contêm o mínimo de funcionalidade que poderão ser aprimoradas ou acrescentadas desde que mantenham a entrega mínima requerida além do foco principal.

Não será permitida a criação de um projeto não relacionado com os temas sugeridos.

Utilizar a API [Twitter4J] para integração com o Twitter, no site da API possui ampla documentação.

No caso do Telegram, utilizar a API [Pengrad Telegram Bot] para integração com o Telegram. A documentação do Telegram pode ser encontrada em https://core.telegram.org/bots.

Eventuais dúvidas de utilização das APIs poderão ser discutidas com o professor.

#### Modelo e Entrega
O trabalho deverá ser entregue como um relatório técnico completo, utilizando linguagem e layout apropriado, contendo os itens a seguir obrigatoriamente:

- Capa contendo o nome e número de matrícula dos integrantes;
- Índice completo;
- Componentes, bibliotecas e frameworks utilizados.
- Explicação do uso de cada pacote (organização do sistema), classe e método;
- Diagrama de classes;
- Diagrama de sequência;
- Capturas de telas da aplicação comentando cada funcionalidade relevante passo-a-passo;
- Link do GitHub com o código-fonte enviado.

Deverá ser entregue um arquivo PDF do relatório técnico no Portal do Aluno. Os códigos fontes deverão ser carregados na conta do GitHub do grupo ou de algum membro do grupo. Lembrando que código fonte da aplicação é obrigatório o desenvolvimento no IDE Eclipse para que sejam testadas e verificadas todas as funcionalidades.

[Twitter4J]: <http://twitter4j.org/en/index.html>
[Pengrad Telegram Bot]: <https://github.com/pengrad/java-telegram-bot-api>
[Twitter4J]: <http://twitter4j.org/en/index.html>