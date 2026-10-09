# Validação — RachaConta

Data: 09/10/2026. Escopo: etapa 1 da avaliação parcial, com Kotlin e Android Views/XML.

## Ambiente

- macOS, Android Studio com JBR/JDK 25.
- Android Gradle Plugin 9.3.1, Gradle Wrapper 9.5.0, compile/target SDK 37 e min SDK 33.
- Emulador Medium_Phone, Android 17/API 37.1, arm64, 1080 × 2400 px.
- Fonte do sistema em 100% e 130%, temas claro/escuro e orientação vertical/horizontal.

## Simplificação do formulário

A ação Usar exemplo foi removida, incluindo botão XML, listener, preenchimento automático e recurso textual. Os testes de fluxo agora preenchem os campos manualmente e confirmam a ação do teclado virtual Android. Os participantes fictícios locais permanecem como dados simulados; o escopo da etapa 1 continua completo.

## Verificações automatizadas

| Verificação | Resultado |
| --- | --- |
| Compilação APK debug | Aprovada |
| JUnit: cálculo, moeda, validação e imutabilidade | 11 testes, zero falhas |
| Espresso: fluxo completo no emulador | 7 testes, zero falhas |
| Android Lint debug | Sem erros impeditivos |

Os testes locais verificam a soma exata em centavos, diferenças de no máximo um centavo entre parcelas, 300 combinações de valores/grupos, limites, entradas enormes sem overflow, nome opcional e atualização imutável de pagamento.

Os testes instrumentados verificam entrada inválida, preenchimento manual, Intent com dados, divisão, marcar/desmarcar, voltar com campos preservados, recriação das duas telas, uma pessoa, nome ausente e 50 pessoas com rolagem/reciclagem das linhas. IDs estáveis preservam a marcação da pessoa correta ao rolar.

Comandos:

```sh
./gradlew clean assembleDebug testDebugUnitTest lintDebug
./gradlew connectedDebugAndroidTest
```

## Verificação visual e manual

| Estado | Evidência |
| --- | --- |
| Formulário simplificado | [formulário](screenshots/formulario-vazio.png) |
| Parcelas e confirmação individual à direita | [resultado](screenshots/resultado.png) |
| Texto ampliado em 130% | [resultado](screenshots/resultado-fonte-130.png) |
| Tema escuro com check marcado e legível | [resultado](screenshots/resultado-escuro.png), [formulário](screenshots/formulario-escuro-fonte-130.png) |
| Entrada manual e confirmação no teclado virtual | Teste instrumentado entradaManualNavegaConfirmaEVoltaMantendoDados |
| Rotação e ações acessíveis | [horizontal](screenshots/resultado-horizontal.png) |
| Grupo grande e pagamento da última pessoa | [lista](screenshots/resultado-50-pessoas.png), [pessoa 50](screenshots/resultado-50-pessoas-final.png) |

As imagens são capturas do app executável, não montagens do mock. O resultado de R$ 100,00 para três pessoas mostra R$ 33,33 + R$ 33,33 + R$ 33,34. A confirmação altera Pago/Pendente e o contador; não altera o valor. O botão Editar conta permanece acessível ao rolar a lista.

A revisão independente do design teve veredito **ship** após verificar fidelidade ao mock aprovado, texto ampliado, horizontal, tema escuro e proveniência dos recursos. Gates de DOM/HTML, servidor e detector web do Impeccable não se aplicam às Views Android; foram substituídos explicitamente por capturas nativas, inspeção dos XMLs e testes Espresso. Os prompts dos dois PNGs estão embutidos nos arquivos e registrados em ASSETS.json.

## Reprodução independente

Na validação inicial, o projeto foi clonado do repositório Git para uma pasta nova, sem local.properties, caches ou builds copiados. Com JAVA_HOME e ANDROID_HOME apontando para JDK/SDK instalados, o Gradle gerou o APK e executou testes locais, lint e os sete testes instrumentados. Nenhuma edição de código ou configuração versionada foi necessária. Após remover Usar exemplo, compilação, testes unitários, lint e os sete testes instrumentados foram executados novamente no emulador Android. O teste de regressão para valores enormes também integra a suíte atual.

## Limites observados

Validação executada em emulador; não foi realizada uma sessão com TalkBack nem teste em hardware físico. O aplicativo não persiste histórico após encerramento do processo: guarda apenas estado da tela durante recriação. Não há API, banco de dados, pagamento real ou etapa Compose nesta entrega.

O lint pode indicar versões mais recentes do Gradle/dependências e o uso de pesos aninhados no layout horizontal. A versão da base acompanha o projeto de aula, e o layout horizontal é pequeno e estático; nenhum aviso impede a compilação ou os fluxos verificados.
