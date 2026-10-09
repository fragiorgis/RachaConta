---
version: 1
slug: "app-src-main-res-layout-activity-dividir-conta-xml"
primary_target: "app/src/main/res/layout/activity_dividir_conta.xml"
related_targets: ["app/src/main/res/layout/activity_resultado.xml","app/src/main/res/layout-land/activity_resultado.xml","app/src/main/res/layout/component_parcela_pessoa.xml"]
---

# RachaConta — duas telas Android

Modo: Operate. Público: amigos universitários dividindo uma conta em um encontro. Ação: informar valor, dividir igualmente e confirmar manualmente quem pagou. Conteúdo: exemplo R$ 100,00 / 3, parcelas 33,33 + 33,33 + 33,34. Duas Activities, XML e ViewBinding; etapa 1 apenas. Referência aprovada: .impeccable/mocks/mesa-v5.png.

## Direction contract

THESIS: uma divisão clara com confirmação individual, em uma identidade brasileira de convivência.

OWN-WORLD: creme e amarelo manteiga, texto marrom, letreiro popular com copos americanos caricatos, botões arredondados e lista plana.

STORY: a pessoa informa conta e participantes, confere parcelas e marca os pagamentos; o contador acompanha sem movimentar dinheiro.

FIRST VIEWPORT: formulário com logo, título, valor, seletor de pessoas, nome opcional e ações inferiores. Resultado com voltar, nome, total, lista na ordem pessoa/estado → parcela → check à direita e botão de editar. A lista rola independentemente da ação de editar. Horizontal usa resumo à esquerda e lista à direita, preservando a ordem de leitura de cada bloco.

FORM: Mesa de amigos, primeira proposta exibida, refinada em mesa-v5; seed key 910c1d83; direção boteco-brasileiro determinada pelo usuário, com prioridade sobre o sorteio.

FINISH: unreviewed and undocumented is unfinished; this build ends with the finish review, the verdict, DESIGN.md, and every shipping raster carrying its provenance

O logo é asset raster próprio; todo conteúdo de interação é View nativa. Cores contrastantes, áreas de 48dp, fonte ampliável e estado salvo devem ser verificados no emulador. Nenhuma decisão de produto está em aberto.
