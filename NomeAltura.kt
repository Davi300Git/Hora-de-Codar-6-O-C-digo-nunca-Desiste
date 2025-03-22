//Nome e Altura
//Elaborar um programa que armazene o nome e a altura de 15 pessoas, por meio do uso de
//registros. O programa deverá ser manipulado por um menu que execute as seguintes etapas:
//
//a) Cadastrar os 15 registros.
//b) Apresentar os registros (nome e altura) das pessoas menores ou iguais a 1.5m.
//c) Apresentar os registros (nome e altura) das pessoas que sejam maiores que 1.5m.
//d) Apresentar os registros (nome e altura) das pessoas que sejam maiores que 1.5m e menores que
//2.0m.
//e) Apresentar a média extraída de todas as alturas armazenadas.
//f) Sair do programa.
data class Pessoa(val nome: String, val altura: Double)

fun main() {
    val listaPessoas = mutableListOf<Pessoa>()
    var opcao: Int

    do {
        println("\n--- Menu ---")
        println("1. Cadastrar 15 pessoas")
        println("2. Mostrar pessoas com altura <= 1.5m")
        println("3. Mostrar pessoas com altura > 1.5m")
        println("4. Mostrar pessoas com altura > 1.5m e < 2.0m")
        println("5. Calcular média das alturas")
        println("6. Sair")
        print("Escolha uma opção: ")
        opcao = readLine()?.toIntOrNull() ?: 0

        when (opcao) {
            1 -> listaPessoas.addAll(cadastrarPessoas())
            2 -> mostrarPessoasPorAltura(listaPessoas, 0.0, 1.5)
            3 -> mostrarPessoasPorAltura(listaPessoas, 1.5, Double.MAX_VALUE)
            4 -> mostrarPessoasPorAltura(listaPessoas, 1.5, 2.0)
            5 -> calcularMediaAlturas(listaPessoas)
            6 -> println("Saindo do programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    } while (opcao != 6)
}

fun cadastrarPessoas(): List<Pessoa> {
    val pessoas = mutableListOf<Pessoa>()
    for (i in 1..15) {
        println("\nCadastro da pessoa $i:")
        print("Nome: ")
        val nome = readLine() ?: ""
        print("Altura (em metros): ")
        val altura = readLine()?.toDoubleOrNull() ?: 0.0
        pessoas.add(Pessoa(nome, altura))
    }
    println("Cadastro concluído!")
    return pessoas
}

fun mostrarPessoasPorAltura(pessoas: List<Pessoa>, alturaMin: Double, alturaMax: Double) {
    val filtradas = pessoas.filter { it.altura > alturaMin && it.altura <= alturaMax }
    if (filtradas.isEmpty()) {
        println("Nenhuma pessoa encontrada nessa faixa de altura.")
    } else {
        println("\nPessoas com altura entre $alturaMin e $alturaMax metros:")
        filtradas.forEach { println("Nome: ${it.nome}, Altura: ${it.altura}m") }
    }
}

fun calcularMediaAlturas(pessoas: List<Pessoa>) {
    if (pessoas.isEmpty()) {
        println("Nenhuma pessoa cadastrada.")
    } else {
        val media = pessoas.map { it.altura }.average()
        println("\nMédia das alturas: ${"%.2f".format(media)} metros")
    }
}