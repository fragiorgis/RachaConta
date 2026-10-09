# Arquitetura e decisões — RachaConta

## Fluxo

DividirContaActivity coleta e valida os dados. NavegacaoConta cria uma Intent explícita para ResultadoActivity contendo valorCentavos (Long), quantidadePessoas (Int) e nomeEncontro (String opcional).

ResultadoActivity reconstrói Conta, calcula a divisão e entrega as parcelas ao ParcelaPessoaAdapter. O adapter informa o id e o estado do check; a Activity atualiza ResultadoRateio usando copy e chama updateUI.

Voltar/Editar chama finish, retomando o formulário existente na pilha. O aplicativo não cria outra instância do formulário.

## Regra do dinheiro

Os cálculos usam inteiros Long em centavos. Para total T e pessoas N:

- base = T / N;
- resto = T % N;
- resto pessoas recebem base + 1; as demais, base;
- os centavos extras ficam nas últimas pessoas, com id estável.

A soma sempre fecha T e duas parcelas diferem em no máximo um centavo. Contas muito pequenas podem gerar parcelas de zero centavos. Decimal brasileiro é convertido sem Double; o teclado com ponto decimal também é aceito.

## Imutabilidade e estado

Conta e ParcelaPessoa possuem propriedades val. ResultadoRateio.marcarPagamento produz uma nova lista e copia apenas a parcela alterada. O estado pertence à Activity; não há conta global.

onSaveInstanceState salva entradas no formulário e ids pagos no resultado. O Android também restaura o estado da lista. Ao dividir novamente, nasce outro resultado, com todos pendentes. Não há persistência entre encerramentos do processo.

## Lista e eventos

Cada linha é component_parcela_pessoa.xml inflado com ViewBinding. Pessoa/estado ficam à esquerda, valor no meio e checkbox no extremo direito. Tocando a linha, performClick encaminha a ação ao checkbox.

Antes de preencher o check durante onBindViewHolder, seu listener é removido. Depois de definir o estado correto, ele é reinstalado com o id daquela parcela. Isso evita que a reciclagem da lista marque outra pessoa.

## Plataforma

AppCompat, Material Components e RecyclerView mantêm os controles familiares do Android. XMLs distintos organizam o resultado em vertical ou horizontal. Insets de barras, recorte e teclado são aplicados no root; o formulário é rolável. Fonte local em sp, campos com labels e áreas de toque de pelo menos 48dp.

ViewBinding foi escolhido para relacionar ids XML ao Kotlin com tipos gerados. Compose, ViewModel, Repository, banco de dados e rede não são necessários à etapa 1.

## Limites

Valor de R$ 0,01 a R$ 99.999,99, 1 a 50 pessoas e nome opcional até 40 caracteres após trim. Nome vazio vira null. Erros aparecem junto ao campo. Checks registram confirmação manual e não comprovam uma transação bancária.
