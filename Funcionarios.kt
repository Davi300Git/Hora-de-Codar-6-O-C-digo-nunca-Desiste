//Funcionários
//Considerando os registros de 20 funcionários, contendo os campos: matrícula, nome e salário,
//desenvolver um programa que, por meio de um menu, execute as seguintes etapas:
//
//a) Cadastrar os 20 empregados e classificar os registros por número de matrícula.
//b) Pesquisar um determinado empregado pelo número de matrícula.
//c) Apresentar de forma ordenada (por matrícula) os registros dos empregados que recebem salários
//acima de R$1.000,00.
//d) Apresentar de forma ordenada (por matrícula) os registros dos empregados que recebem salários
//abaixo de R$1.000,00.
//e) Apresentar de forma ordenada (por matrícula) os registros dos empregados que recebem salários
//iguais a R$1.000,00.
//f) Sair do programa.
data class Funcionario(val matricula: Int, val nome: String, val salario: Double)

fun main() {
    val funcionarios = mutableListOf<Funcionario>()
    var opcao: Int

    do {
        print("""
            --- Sistema de Cadastro de Funcionários ---
            1. Cadastrar 20 funcionários
            2. Pesquisar funcionário por matrícula
            3. Apresentar funcionários com salário acima de R${'$'}1.000,00
            4. Apresentar funcionários com salário abaixo de R${'$'}1.000,00
            5. Apresentar funcionários com salário igual a R${'$'}1.000,00
            6. Sair
            Escolha uma opção: """.trimIndent())
        opcao = readLine()?.toIntOrNull() ?: 0

        when (opcao) {
            1 -> funcionarios.addAll(cadastrarFuncionarios())
            2 -> pesquisarPorMatricula(funcionarios)
            3 -> apresentarFuncionariosPorSalario(funcionarios, 1000.0, true)
            4 -> apresentarFuncionariosPorSalario(funcionarios, 1000.0, false)
            5 -> apresentarFuncionariosComSalarioExato(funcionarios, 1000.0)
            6 -> println("Saindo do programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    } while (opcao != 6)
}

fun cadastrarFuncionarios(): List<Funcionario> {
    val listaFuncionarios = mutableListOf<Funcionario>()
    for (i in 1..20) {
        println("\nCadastro do funcionário $i:")
        print("Matrícula: ")
        val matricula = readLine()?.toIntOrNull() ?: 0
        print("Nome: ")
        val nome = readLine() ?: ""
        print("Salário: R$")
        val salario = readLine()?.toDoubleOrNull() ?: 0.0
        listaFuncionarios.add(Funcionario(matricula, nome, salario))
    }
    listaFuncionarios.sortBy { it.matricula }
    println("Cadastro concluído!")
    return listaFuncionarios
}

fun pesquisarPorMatricula(funcionarios: List<Funcionario>) {
    print("\nDigite a matrícula do funcionário a ser pesquisado: ")
    val matriculaPesquisa = readLine()?.toIntOrNull() ?: 0
    val funcionarioEncontrado = funcionarios.find { it.matricula == matriculaPesquisa }

    if (funcionarioEncontrado != null) {
        println("""
            Funcionário encontrado:
            Matrícula: ${funcionarioEncontrado.matricula}
            Nome: ${funcionarioEncontrado.nome}
            Salário: R$${"%.2f".format(funcionarioEncontrado.salario)}""".trimIndent())
    } else {
        println("Funcionário não encontrado.")
    }
}

fun apresentarFuncionariosPorSalario(funcionarios: List<Funcionario>, valor: Double, acima: Boolean) {
    val filtrados = if (acima) {
        funcionarios.filter { it.salario > valor }
    } else {
        funcionarios.filter { it.salario < valor }
    }

    if (filtrados.isEmpty()) {
        println("\nNenhum funcionário encontrado.")
    } else {
        println("\n--- Funcionários com salário ${if (acima) "acima" else "abaixo"} de R$${"%.2f".format(valor)} ---")
        filtrados.forEach { funcionario ->
            println("""
                Matrícula: ${funcionario.matricula}
                Nome: ${funcionario.nome}
                Salário: R${'$'}${"%.2f".format(funcionario.salario)}
                ----------------------""".trimIndent())
        }
    }
}

fun apresentarFuncionariosComSalarioExato(funcionarios: List<Funcionario>, valor: Double) {
    val filtrados = funcionarios.filter { it.salario == valor }

    if (filtrados.isEmpty()) {
        println("\nNenhum funcionário encontrado.")
    } else {
        println("\n--- Funcionários com salário igual a R$${"%.2f".format(valor)} ---")
        filtrados.forEach { funcionario ->
            println("""
                Matrícula: ${funcionario.matricula}
                Nome: ${funcionario.nome}
                Salário: R${'$'}${"%.2f".format(funcionario.salario)}
                ----------------------""".trimIndent())
        }
    }
}