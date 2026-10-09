# RachaConta — planejamento atual

Esta revisão substitui as decisões conflitantes do planejamento inicial. O usuário solicitou amarelo claro, UI simples, botões mais arredondados, retirada do serviço de 10% e check de acerto por pessoa. O design da versão 5 foi aprovado em 09/10/2026. O app Android foi implementado e validado em 09/10/2026. Este documento preserva as decisões de escopo; a implementação está descrita em ARQUITETURA.md e as evidências em VALIDACAO.md.

## Produto e fluxo

1. Informar valor final da conta, quantidade de pessoas (1 a 50) e nome opcional do encontro.
2. Tocar em Dividir para abrir a segunda Activity por Intent explícita.
3. Conferir total e uma linha por pessoa com sua parcela.
4. Marcar ou desmarcar o check de cada pessoa; a linha passa entre Pago e Pendente e o contador atualiza imediatamente.
5. Editar conta retorna ao formulário preservando entradas.

O check é um registro manual local de que a parte foi acertada. A marcação não altera parcelas e não efetua pagamentos. Toda nova divisão começa pendente; o mock mostra uma pessoa paga apenas para exemplificar o estado visual. Alterar entradas e dividir novamente inicia uma nova lista pendente. Durante a exibição do resultado, rotação/recriação preserva os checks por estado salvo. Sem API, banco de dados, login ou histórico persistente.

## Telas

- Formulário: RachaConta, Dividir conta, valor, controle menos/mais de pessoas, nome opcional, Dividir.
- Resultado: voltar, nome do encontro ou Conta da turma quando ausente, total, Por pessoa, lista individual com pessoa/estado à esquerda, valor e checkbox de confirmação à direita, contador de pagos e Editar conta.
- Lista rolável para grupos grandes. Marcar deve funcionar ao tocar na linha ou no check, com semântica acessível de checkbox e sem alternância dupla.
- Erros próximos aos campos; teclado decimal no valor; campos não cobertos pelo teclado. Botões com área mínima de toque de 48dp, preferencialmente altura de 56dp.

Sem opção de serviço, percentual, ilustração de mesa ou frases explicando os centavos.

## Design aprovado

- Amarelo manteiga claro como destaque, creme como fundo, texto marrom neutro legível.
- Sem verde escuro, laranja ou azul como cor principal.
- Botões de ação com cantos bem arredondados; controles menos/mais circulares.
- Referência de boteco usada em cores e formas, sem reproduzir móveis ou publicidade. Logo com letreiro popular e dois copos americanos caricatos; detalhes de identidade descritos em ESTETICA_BUTECO.md.
- Interface limpa, uma lista alinhada com divisórias leves; Pago/Pendente também escritos para não depender apenas de cor.
- Amarelo claro em superfícies e controles, texto escuro sobre elas para preservar contraste. Tamanhos e contraste serão validados no XML funcional.

Referência aprovada: ../.impeccable/mocks/mesa-v5.png. Prompt exato: ../.impeccable/mocks/mesa-v5.png.json. Gerado com ImageGen; aprovação recebida em 09/10/2026.

## Kotlin e XML previstos

- DividirContaActivity e activity_dividir_conta.xml: entrada, validação, pessoas, Intent.
- ResultadoActivity e activity_resultado.xml: cálculo, linhas individuais, checks e contador.
- component_parcela_pessoa.xml: linha reutilizável inflada com ViewBinding, contendo checkbox, pessoa, valor e estado. Bindings atualizados por updateUI.
- Conta: data class imutável com valorCentavos: Long, quantidadePessoas: Int e nomeEncontro: String?.
- ParcelaPessoa: data class imutável com id: Int, valorCentavos: Long e pago: Boolean. Pessoa N deriva do id estável.
- ResultadoRateio: totalCentavos e lista de parcelas. Atualização de marcação por copy, gerando lista nova.
- CalculadoraRateio, ValidadorConta, FormatoDinheiro e NavegacaoConta mantêm as responsabilidades definidas no plano inicial, retirando argumentos de serviço.
- Intent envia apenas valor, quantidade e nome opcional. Checks são estado da tela de resultado.

## Cálculo e verificação

Usar Long em centavos. Para total T e N pessoas, base = T / N e resto = T % N. Distribuir exatamente resto parcelas de base + 1 e as demais de base; atribuição estável por pessoa. Exemplo: 10000 centavos / 3 = 3333, 3333, 3334. Valores pequenos podem produzir parcelas zero.

Validar valor positivo até R$ 99.999,99, pessoas entre 1 e 50, nome opcional até 40 caracteres. Nome em branco vira null. Preservar conversão decimal brasileira sem ponto flutuante para dinheiro.

Verificar soma exata, diferenças de no máximo um centavo, 1/3/50 pessoas, marcação reversível independente, contador, voltar/editar, rotação, teclado, rolagem e texto ampliado. Os participantes fictícios locais (Pessoa 1 até Pessoa N) representam os dados simulados. As duas telas, Intent, ViewBinding, modelos imutáveis e interação dos checks cumprem a etapa 1. Compose continua fora da entrega.
