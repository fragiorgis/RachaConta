# RachaConta

<!-- impeccable:product-schema 1 -->

## Platform

android

## Stack

Kotlin, Android Views com layouts XML, ViewBinding e duas Activities conectadas por Intent explícita. Aplicativo da avaliação parcial de Programação Mobile 1. A etapa de Jetpack Compose está fora desta entrega.

## Users

Amigos e colegas, especialmente universitários, dividindo uma conta igualmente em restaurantes, botecos ou encontros. Precisam conferir quanto cada pessoa paga e marcar quem já acertou sua parte.

## Product Purpose

Informar o valor final da conta e a quantidade de pessoas, obter parcelas que somam exatamente o total e marcar manualmente cada pessoa como paga ou pendente.

## Operating Context

Uso em celular, em português brasileiro, com valores em reais. O fluxo funciona localmente. O professor avaliará o projeto por um repositório GitHub executável e pelo entendimento do aluno sobre as decisões técnicas.

## Capabilities and Constraints

- Duas telas: formulário de divisão e resultado.
- Valor final da conta e quantidade de pessoas. Sem opção de serviço ou percentual.
- Nome opcional do encontro.
- Validação de entrada com feedback próximo ao campo.
- Cálculo em centavos e distribuição de diferenças de no máximo um centavo entre parcelas.
- Uma linha por pessoa no resultado, com valor e check reversível para marcar sua parte como paga.
- Voltar para editar preserva os campos.
- Participantes fictícios gerados localmente (Pessoa 1 até Pessoa N); sem API ou banco de dados. Valor e quantidade são informados pelo usuário.
- Divisão por consumo individual, Pix, histórico e cadastro ficam para evolução futura.
- Design da versão 5 aprovado pelo usuário e implementado em duas telas XML com fluxo Kotlin. Evidências em docs/VALIDACAO.md e sistema visual em DESIGN.md.

## Brand Commitments

Nome de trabalho RachaConta. Linguagem direta, amigável e em português brasileiro. Amarelo claro como cor principal, fundo creme e texto neutro legível. Sem verde escuro, laranja ou mesa ilustrada. A referência de boteco orienta cores e formas acolhedoras; não deve ser reproduzida literalmente. Botões arredondados, fáceis de tocar, interface simples e sem frases decorativas. O usuário escolheu a proposta 1 como ponto de partida e solicitou estas alterações.

## Evidence on Hand

- `docs/PLANEJAMENTO_ATUAL.md`: escopo atual após simplificação do fluxo e inclusão do acerto por pessoa.
- `.impeccable/mocks/mesa-v5.png`: design aprovado, com logo de letreiro e copos americanos caricatos, e checks à direita.
- `docs/GUIA_IMPLEMENTACAO.md`: sequência de importação dos recursos, layouts, código, verificação e entrega.
- `docs/ESTETICA_BUTECO.md`: descrição da estética e sua aplicação no produto.

- `docs/PLANEJAMENTO.md`: plano do produto, classes, XML, cálculo e entrega.
- `../media-tracker`: projeto desenvolvido nas aulas e enunciado da avaliação.
- Exemplo de conta: R$ 100,00 entre três pessoas produz parcelas de R$ 33,33, R$ 33,33 e R$ 33,34.

## Product Principles

- A tarefa principal precisa ser compreensível na primeira utilização.
- Valores e composição da conta devem ser fáceis de conferir.
- A soma das parcelas deve fechar o total exato.
- As duas telas precisam usar controles familiares do Android.
- A identidade visual deve servir à clareza e ao uso rápido.

## Accessibility & Inclusion

Prever áreas de toque de pelo menos 48dp, labels visíveis, contraste legível, textos ampliados, estados indicados também por texto e navegação de retorno do Android. Moeda e plurais seguem português brasileiro.
