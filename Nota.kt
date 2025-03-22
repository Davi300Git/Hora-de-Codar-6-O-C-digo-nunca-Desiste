//Qual a nota?
//Considerando a necessidade de um programa que armazene o nome e as notas bimestrais de 20 alunos do curso de Técnicas de Programação, defina a estrutura de registro apropriada, o diagrama de blocos e a codificação de um programa que, por meio do uso de um menu de opções, execute as seguintes etapas:
//
//a) Cadastrar os 20 registros (após o cadastro efetuar a classificação por nome).
//b) Pesquisar os 20 registros, de cada vez, pelo campo nome. Nesta pesquisa o programa deverá
//também apresentar a média do aluno e as mensagens: “Aprovado” caso sua média seja maior ou
//igual a 5, ou “Reprovado” para média abaixo de 5.
//c) Apresentar todos os registros, médias e a mensagem de aprovação ou reprovação.
//d) Sair do programa de cadastro.

data class Aluno(
    val nome: String,
    val notas: List<Double>,
    val media: Double = notas.average(),
    val status: String = if (notas.average() >= 5) "Aprovado" else "Reprovado"
)

fun main() {
    val alunos = mutableListOf<Aluno>()
    var opcao: Int

    do {
        print("""
            --- Sistema de Cadastro de Alunos ---
            1. Cadastrar 20 alunos
            2. Pesquisar aluno por nome
            3. Apresentar todos os alunos
            4. Sair
            Escolha uma opção: """.trimIndent())
        opcao = readLine()?.toIntOrNull() ?: 0

        when (opcao) {
            1 -> alunos.addAll(cadastrarAlunos())
            2 -> pesquisarAlunoPorNome(alunos)
            3 -> apresentarTodosAlunos(alunos)
            4 -> println("Saindo do programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    } while (opcao != 4)
}
fun cadastrarAlunos(): List<Aluno> {
    val listaAlunos = mutableListOf<Aluno>()
    for (i in 1..20) {
        println("\nCadastro do aluno $i:")
        print("Nome: ")
        val nome = readLine() ?: ""
        val notas = mutableListOf<Double>()
        for (j in 1..4) {
            print("Nota do $j º bimestre: ")
            val nota = readLine()?.toDoubleOrNull() ?: 0.0
            notas.add(nota)
        }
        listaAlunos.add(Aluno(nome, notas))
    }

    listaAlunos.sortBy { it.nome }
    println("Cadastro concluído!")
    return listaAlunos
}
fun pesquisarAlunoPorNome(alunos: List<Aluno>) {
    print("\nDigite o nome do aluno a ser pesquisado: ")
    val nomePesquisa = readLine() ?: ""
    val alunoEncontrado = alunos.find { it.nome.equals(nomePesquisa, ignoreCase = true) }

    if (alunoEncontrado != null) {
        println("""
            Aluno encontrado:
            Nome: ${alunoEncontrado.nome}
            Notas: ${alunoEncontrado.notas}
            Média: ${"%.2f".format(alunoEncontrado.media)}
            Status: ${alunoEncontrado.status}""".trimIndent())
    } else {
        println("Aluno não encontrado.")
    }
}
fun apresentarTodosAlunos(alunos: List<Aluno>) {
    if (alunos.isEmpty()) {
        println("\nNenhum aluno cadastrado.")
    } else {
        println("\n--- Lista de Alunos ---")
        alunos.forEach { aluno ->
            println("""
                Nome: ${aluno.nome}
                Notas: ${aluno.notas}
                Média: ${"%.2f".format(aluno.media)}
                Status: ${aluno.status}
                ----------------------
            """.trimIndent())
        }
    }
}