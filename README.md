# RachaConta

Aplicativo Android para dividir uma conta igualmente entre participantes e registrar a confirmação de pagamento de cada pessoa. Desenvolvido em Kotlin com layouts XML para a avaliação parcial da disciplina Programação Mobile I.

## Execução

1. Abra a pasta do projeto no Android Studio.
2. Instale o Android SDK 37 pelo SDK Manager e configure o JDK 25 para o Gradle.
3. Aguarde a sincronização e execute o módulo `app` em um emulador com Android 13 (API 33) ou superior.

## Bibliotecas

- AndroidX Core, Activity e AppCompat: suporte às telas e aos recursos do Android.
- Material Components: campos de entrada, botões e caixas de seleção.
- RecyclerView: exibição da lista de parcelas e confirmações de pagamento.
- JUnit e AndroidX Test/Espresso: testes unitários e de interface.
- Foojay Toolchain Resolver: obtenção do JDK necessário à compilação.

O aplicativo utiliza a fonte Nunito, cuja licença está em `licenses/Nunito-OFL.txt`.
