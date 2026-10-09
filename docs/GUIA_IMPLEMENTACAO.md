# RachaConta — guia de implementação do design aprovado

O usuário aprovou a versão 5 em 09/10/2026. Referência visual: ../.impeccable/mocks/mesa-v5.png. A sequência abaixo foi executada; o projeto Android está implementado. Este guia registra o plano de execução. Consulte ARQUITETURA.md, ../DESIGN.md e VALIDACAO.md para o estado final.

## O que significa importar o design

A imagem aprovada é a referência de composição. Apenas o logo (letras e copos americanos) será preparado como asset separado. Campos, rótulos, valores, botões e checkboxes serão Views reais em XML, com textos em strings.xml, dimensões em dp/sp e cores centralizadas. Isso permite interação, teclado, leitores de tela, fontes maiores e rolagem.

Preservar a paleta amarelo claro/creme/marrom, logo com letreiro de buteco, ações arredondadas e leitura do resultado na ordem pessoa → valor → checkbox à direita. As adaptações para teclado, barras do sistema, telas menores e texto ampliado devem preservar essa hierarquia.

## Sequência de trabalho

| Fase | Trabalho | Evidência de conclusão |
| --- | --- | --- |
| 1. Base Android | Criar projeto independente na pasta atual; configurar módulo app, Gradle Wrapper, Kotlin e ViewBinding; declarar duas Activities | Projeto sincroniza e gera APK básico |
| 2. Recursos visuais | Preparar logo transparente a partir do aprovado; fonte arredondada local; paleta, estilos, dimensões, ícones e botões | Logo legível em tamanho de celular e componentes com a aparência aprovada |
| 3. Layouts XML | Construir as duas telas e a linha reutilizável com dados de exemplo | Preview e emulador mostram o fluxo fiel ao estudo, sem cortes |
| 4. Kotlin | Validar entrada, calcular parcelas, navegar por Intent, preencher lista, marcar pagamentos e preservar estado | Fluxo completo funciona offline com valores reais |
| 5. Verificação | Testes de cálculo/conversão, execução no emulador, rotação, voltar, teclado, texto ampliado e grupos grandes | APK executável e comportamento consistente |
| 6. Entrega | README, screenshots, instruções de execução, dependências e novo repositório GitHub | Clone limpo compila e executa; link pronto para o Classroom |

## Base técnica e referência das aulas

O MediaTracker usa duas Activities, ViewBinding, Intent, XML reutilizável inflado e RecyclerView. Esses padrões serão reaproveitados, mantendo o código do RachaConta independente.

A configuração atual da referência contém plugin, flag e dependências de Compose. A nova base deve conter apenas o que a parcial usa: Views/XML, ViewBinding, AppCompat, Material Components, RecyclerView e demais dependências Android necessárias. Não copiar o app inteiro para depois manter código alheio.

Foram encontrados Android Studio com JBR, SDK android-37.0, emulador Medium_Phone e Gradle 9.5/9.6 em cache. A referência declara AGP 9.3.1, Gradle 9.5.0 e JDK do daemon 25. Esses dados são uma base local de comparação; a compatibilidade do novo projeto será confirmada pelo primeiro build. A compatibilidade foi confirmada por compilação, lint, testes locais e testes instrumentados do RachaConta.

Não versionar local.properties, caches, arquivos de máquina ou builds. Gradle Wrapper e configurações portáveis devem acompanhar o repositório.

## Mapeamento do visual para recursos Android

| Elemento aprovado | Recurso/implementação prevista |
| --- | --- |
| Logo com letreiro e copos | Asset transparente separado em res/drawable, exibido em ImageView com proporção preservada |
| Amarelo claro, creme e marrom | res/values/colors.xml e themes.xml |
| Tipografia arredondada | Fonte local em res/font e estilos; confirmar licença e legibilidade |
| Espaçamento e áreas de toque | res/values/dimens.xml; botões de aproximadamente 56dp e toque mínimo de 48dp |
| Valor e nome do encontro | Inputs nativos com labels, tipo de teclado e mensagens de validação |
| Menos e mais | Botões circulares com descrição acessível e estados de limite |
| Dividir e Editar conta | MaterialButton arredondado, texto e feedback de toque |
| Pessoas, valores e checks | RecyclerView com component_parcela_pessoa.xml e ViewBinding |
| Pago/Pendente | Texto abaixo do nome; check no extremo direito |
| Voltar e ícones de controle | Drawables vetoriais coerentes, separados do logo |

Cores e proporções finais devem ser amostradas da referência aprovada. As medidas do mock não são pixels fixos para copiar indiscriminadamente: a implementação deve suportar barras do Android, teclado e fonte ampliada.

## Organização de código

- ui/DividirContaActivity.kt: formulário, exemplo, pessoas, validação e abertura do resultado.
- ui/ResultadoActivity.kt: recebe os dados, apresenta parcelas, pagamentos e retorno para edição.
- ui/ParcelaPessoaAdapter.kt: linha reutilizável, binding e evento de confirmação por id estável.
- model/Conta.kt: valorCentavos, quantidadePessoas e nomeEncontro opcional; propriedades val.
- model/ParcelaPessoa.kt: id, valorCentavos e pago; mudança usando copy.
- model/ResultadoRateio.kt: total e lista de parcelas.
- domain/CalculadoraRateio.kt: regra de distribuição dos centavos, sem Views.
- domain/ValidadorConta.kt: limites e entradas válidas.
- util/FormatoDinheiro.kt: conversão/formatacão brasileira sem usar Double no cálculo.
- navigation/NavegacaoConta.kt: chaves e passagem dos dados da Intent.
- data/ContasExemplo.kt: exemplo local para atender ao requisito de mocks.

Estado permanece nas Activities e em savedInstanceState nesta etapa; não há necessidade de ViewModel, Repository ou banco de dados. Sem variáveis globais para conta ou pagamento.

## Comportamento a implementar

- Formulário inicia vazio, com 2 pessoas; nome vazio vira null.
- Usar exemplo preenche R$ 100,00, 3 pessoas e Pizza de sexta.
- Dividir valida as entradas e abre ResultadoActivity por Intent explícita.
- O resultado calcula 33,33 + 33,33 + 33,34 para o exemplo e começa com todas as pessoas pendentes.
- O primeiro pago mostrado no mock apenas ilustra o estado confirmado.
- Marcar/desmarcar atualiza a parcela imutável, o texto de estado e o contador.
- Reciclagem da lista não pode transferir checks para outra pessoa; listeners devem ser vinculados ao id correto e não disparar durante o preenchimento visual.
- Voltar/Editar retorna à Activity anterior preservando entradas.
- Uma nova divisão começa pendente; a rotação do resultado conserva os checks atuais.
- Grupos de até 50 pessoas usam lista rolável, mantendo acesso à ação Editar conta.
- Sem serviço de 10%, mesa literal ou frases decorativas.

## Validação e entrega

Os testes relevantes cobrem soma exata, diferença máxima de um centavo, dinheiro decimal brasileiro, limites e marcas independentes. Verificação manual contempla as duas telas, Intent, formulário inválido, nome ausente, teclado, 1/3/50 pessoas, pagamentos, retorno, rotação e acessibilidade.

A entrega terá objetivo do app, ambiente e comandos de execução, breve justificativa das bibliotecas, prints das duas telas e relação com os requisitos da parcial. O repositório só está pronto para entregar após validação de build e execução a partir de um clone limpo.
