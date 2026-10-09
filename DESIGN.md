---
name: RachaConta
description: Divisão clara de contas com o calor de um boteco brasileiro.
colors:
  cream: "#FFFDF6"
  yellow: "#FFE784"
  brown: "#3B2519"
  secondary-text: "#746052"
  outline: "#9D8C7B"
  divider: "#E8E0D6"
  field-surface: "#FFFEFA"
  error: "#A32929"
  night-cream: "#241E19"
  night-brown: "#FFF3DC"
  night-secondary-text: "#D6C7B8"
  night-outline: "#9B8978"
  night-divider: "#4E4135"
  night-field-surface: "#302820"
  night-error: "#FFB4AB"
typography:
  display:
    fontFamily: "Nunito"
    fontSize: "36sp"
    fontWeight: 800
  headline:
    fontFamily: "Nunito"
    fontSize: "28sp"
    fontWeight: 800
  title:
    fontFamily: "Nunito"
    fontSize: "22sp"
    fontWeight: 800
  body:
    fontFamily: "Nunito"
    fontSize: "16sp"
  label:
    fontFamily: "Nunito"
    fontSize: "16sp"
    fontWeight: 600
  secondary:
    fontFamily: "Nunito"
    fontSize: "14sp"
    fontWeight: 500
  person:
    fontFamily: "Nunito"
    fontSize: "18sp"
    fontWeight: 700
  action:
    fontFamily: "Nunito"
    fontSize: "20sp"
    fontWeight: 800
  input-amount:
    fontFamily: "Nunito"
    fontSize: "28sp"
    fontWeight: 700
  input-name:
    fontFamily: "Nunito"
    fontSize: "20sp"
    fontWeight: 600
  people-count:
    fontFamily: "Nunito"
    fontSize: "32sp"
    fontWeight: 800
rounded:
  input: "14dp"
  action: "32dp"
spacing:
  compact: "4dp"
  inline: "8dp"
  control-gap: "12dp"
  content: "16dp"
  footer-gap: "20dp"
  screen-padding: "24dp"
components:
  button-primary:
    backgroundColor: "{colors.yellow}"
    textColor: "{colors.brown}"
    typography: "{typography.action}"
    rounded: "{rounded.action}"
  button-text:
    textColor: "{colors.brown}"
    typography: "{typography.label}"
  stepper-button:
    backgroundColor: "{colors.yellow}"
    textColor: "{colors.brown}"
    rounded: "{rounded.action}"
    size: "64dp"
  input-amount:
    backgroundColor: "{colors.field-surface}"
    textColor: "{colors.brown}"
    typography: "{typography.input-amount}"
    rounded: "{rounded.input}"
  input-name:
    backgroundColor: "{colors.field-surface}"
    textColor: "{colors.brown}"
    typography: "{typography.input-name}"
    rounded: "{rounded.input}"
  payment-row:
    textColor: "{colors.brown}"
    typography: "{typography.person}"
    padding: "16dp 0dp"
  paid-check:
    backgroundColor: "{colors.yellow}"
    textColor: "{colors.brown}"
    size: "48dp"
---

# Design System: RachaConta

## Overview

**Creative North Star: "Mesa de amigos"**

O boteco brasileiro aparece no calor do creme, no amarelo manteiga e no letreiro caricato com copos americanos. A interface é acolhedora e direta: títulos robustos, campos claros, controles arredondados e valores fáceis de conferir. A convivência dá personalidade à marca; a operação continua simples.

O sistema usa uma lista plana e áreas de toque generosas, sem ornamentação entre a conta e a confirmação de pagamento. O logo é um asset raster próprio, não uma família tipográfica disponível para títulos. Verde escuro, laranja e mesa ilustrada foram rejeitados pelo usuário; o letreiro e os copos são a expressão aprovada da identidade.

**Key Characteristics:**

- Creme, amarelo manteiga e texto marrom; equivalentes quentes no modo escuro.
- Nunito variável com pesos explícitos para títulos, valores, labels e ações.
- Botões arredondados, campos com contorno e lista plana.
- Pagamento indicado pelo check e também por texto.

## Colors

Uma paleta quente de creme e manteiga com tinta marrom mantém o vínculo com o letreiro popular. As cores normativas estão no frontmatter; as chaves preservam os nomes dos recursos Android, com hífens e prefixo `night-` para os valores alternativos.

### Primary

- **Amarelo manteiga (`yellow`)**: preenchimento das ações, botões do seletor e checks pagos. É idêntico nos dois modos.

### Neutral

- **Creme de fundo (`cream`)**: tela e superfície principal.
- **Marrom de letreiro (`brown`)**: texto principal, valores e ícones. O marrom diurno também permanece sobre os controles amarelos no modo escuro.
- **Marrom suave (`secondary-text`)**: texto auxiliar e status de pagamento.
- **Contorno quente (`outline`)**: contornos e checks pendentes.
- **Divisor creme (`divider`)**: linhas discretas entre pessoas.
- **Creme de campo (`field-surface`)**: superfície dos campos, ligeiramente distinta do fundo.
- **Equivalentes noturnos (`night-*`)**: fundo e campos tornam-se castanhos profundos; textos tornam-se creme; contornos e divisores continuam quentes. A substituição é feita pelos qualificadores `values-night`, não por uma segunda identidade.

A cor semântica **Erro (`error`, `night-error`)** serve à validação do Material TextInputLayout. Não é um segundo acento decorativo.

**The Amarelo de Ação Rule.** Reserve o amarelo para ações, controles de quantidade e confirmação de pagamento; use as superfícies neutras para conteúdo.

## Typography

**Display Font:** Nunito variável, recurso `@font/nunito_variable`.
**Body Font:** a mesma Nunito. Não há fonte de label ou mono separada.

**Character:** curvas amigáveis e pesos fortes dão clareza aos números e proximidade aos títulos. O arquivo declara a família Nunito, eixo `wght` de 200 a 1000; o texto da interface não herda a tipografia desenhada no logo.

### Hierarchy

- **Display**: total da conta (`display`, 36sp, peso 800).
- **Headline**: título da tela ou encontro (`headline`, 28sp, peso 800).
- **Title**: título da lista (`title`, 22sp, peso 800).
- **Body**: base do estilo de texto (`body`, 16sp); sem peso explícito no estilo base.
- **Label**: identificação de campos (`label`, 16sp, peso 600).
- **Secondary**: estado e apoio (`secondary`, 14sp, peso 500). O contador do rodapé vertical substitui o tamanho por 16sp.
- **Person**: nome da pessoa e parcela (`person`, 18sp, peso 700).
- **Action**: texto das ações amarelas (`action`, 20sp, peso 800).
- **Inputs**: valor (`input-amount`, 28sp, peso 700) e nome opcional (`input-name`, 20sp, peso 600).
- **People count**: número do seletor (`people-count`, 32sp, peso 800).

Não há line-height ou letter-spacing próprios definidos. O tema e os controles Material resolvem os valores herdados; `Text.RachaConta` desativa o padding adicional da fonte. Não fixe altura de texto em pixels.

**The Valores Conferíveis Rule.** Dê aos valores a mesma família dos labels, com tamanho e peso suficientes para distingui-los; preserve moeda, vírgula decimal e centavos.

## Layout

A base é Android Views com XML: `dp` mede geometria, `sp` mede texto. O frontmatter registra essas unidades nativas. As traduções HTML/CSS do sidecar usam pixels de prévia em uma relação ilustrativa de 1:1; não são medidas do runtime Android nem conversões de screenshots.

A margem horizontal recorrente é `screen-padding` (24dp). O ritmo usa 4dp, 8dp, 12dp, 16dp, 20dp e 24dp conforme proximidade do conteúdo. O layout usa a largura disponível, com insets de sistema aplicados pelo app.

O formulário é uma coluna em NestedScrollView com `fillViewport`; a região flexível antes das ações mantém a ação perto da base quando há espaço. Em tela curta, teclado ou texto ampliado, o conjunto pode rolar. O logo ocupa 300dp × 120dp com `fitCenter`.

O resultado vertical tem resumo acima, RecyclerView com peso flexível e contador/ação de editar abaixo. A lista rola independentemente do rodapé. O recurso `layout-land` troca o resultado por duas colunas, com pesos 1 e 1,2: resumo rolável à esquerda e lista/ação à direita. A mudança depende da orientação Android, sem breakpoint CSS. O formulário mantém seu layout de coluna também na horizontal.

## Elevation & Depth

A profundidade percebida vem da diferença tonal entre fundo e campos, do contorno de input e dos divisores de pessoas. A lista não usa cards. Os botões desativam `stateListAnimator`, mas nenhuma elevação numérica própria está definida; valores herdados do Material não se tornam tokens do projeto. Não há vocabulário próprio de sombra nem motion customizado.

**The Plano e Legível Rule.** Separe conteúdo por espaço, contorno e divisores sutis; não dependa de sombras para comunicar a estrutura.

## Shapes

Os campos têm cantos suaves (`input`, 14dp). A ação principal usa cantos largos (`action`, 32dp), com altura mínima de 56dp e padding vertical de 14dp; pode crescer com o texto. O seletor reutiliza o raio da ação em botões de 64dp × 64dp. A lista permanece plana, com divisor de 1dp.

O suporte claro do logo tem raio de 20dp e cor diurna fixa, inclusive no tema escuro, para preservar a leitura do asset. É um tratamento específico desse raster, não um token de card para futuras superfícies.

## Components

### Buttons

A ação é ampla, amigável e firme: amarelo manteiga, tinta marrom diurna e texto `action`. `Widget.RachaConta.Action` fornece o raio, a altura mínima e o padding vertical. Dividir e Editar conta são usos do mesmo componente.

O botão de texto Usar exemplo usa Nunito de 16sp, peso 600, texto principal e mínimo de 48dp. Ripple, foco, estados pressionado e desabilitado seguem os controles Material; não há estado hover customizado no código.

### Inputs / Fields

Campos nativos Material com fundo de campo, contorno quente e raio `input`. Labels externos permanecem visíveis; `hintEnabled` é false. O valor tem prefixo R$, texto decimal em uma linha e mínimo de 64dp; o nome tem texto em uma linha e mínimo de 60dp. Validação e foco são gerenciados pelo TextInputLayout, com a cor de erro do tema. Não há uma paleta própria de estados de borda.

### Navigation

Voltar é um Material TextButton de 48dp × 48dp com ícone vetorial de 28dp, cor de texto principal e descrição acessível. É a navegação local presente; o sistema não contém abas ou barra de navegação própria.

### Seletor de pessoas

Dois botões circulares amarelos de 64dp enquadram o número central flexível. Os vetores mais e menos têm 28dp e marrom diurno. O número anuncia alterações via `accessibilityLiveRegion="polite"`.

### Linha de parcela e confirmação

Nome e estado formam a coluna flexível à esquerda, parcela vem em seguida, e check de 48dp fica à direita. A linha tem mínimo de 88dp e padding vertical de 16dp; margem de 8dp após o bloco textual e 12dp antes do check. O divisor tem 1dp. Pessoa e valor usam `person`; estado usa `secondary`.

`payment_tint` seleciona amarelo quando pago e contorno quando pendente; o ícone marcado permanece marrom diurno. O texto Pago/Pendente acompanha a mudança. O contador resume os pagamentos e anuncia mudanças sem alterar a ordem visual.

## Do's and Don'ts

### Do:

- Do manter a paleta quente, a Nunito e o logo aprovado ao estender o app.
- Do usar dp para geometria e sp para texto; respeitar a ampliação da fonte e o mínimo de toque de 48dp.
- Do apresentar estados de pagamento com texto e check, mantendo valores fáceis de conferir.
- Do reutilizar os estilos de ação, texto e campo definidos no tema antes de criar uma variante.

### Don't:

- Don't introduzir verde escuro, laranja ou mesa ilustrada na identidade.
- Don't substituir o letreiro raster por uma fonte de interface ou espalhar copos decorativos pelo conteúdo.
- Don't transformar a lista de pessoas em uma coleção de cards elevados.
- Don't usar somente cor para comunicar pagamento ou erro.
