//Considerando a necessidade de desenvolver uma agenda que contenha nomes,
// endereços e telefones de 10 pessoas, defina a estrutura de registro apropriada,
// o diagrama de blocos e a codificação de um programa que por meio do uso de um menu
// de opções, execute as seguintes etapas:
//a) Cadastrar os 10 registros.
//b) Pesquisar um dos 10 registros de cada vez pelo campo nome (usar o método seqüencial).
//c) Classificar por ordem de nome os registros cadastrados.
//d) Apresentar todos os registros.
//e) Sair do programa de cadastro.
data class Contato(val nome: String, val telefone : String)
fun main() {
    val agenda = mutableListOf<Contato>()
    var opcao : Int

    do{
        println("""
            Agenda de Contatos
            1. Cadastrar 10 contatos 
            2. Pesquisar contato por nome
            3. Classificar contatos por nome
            4. Apresentar todos os contatos
            5. Sair
            Escolha a opção:
        """.trimIndent())
        opcao = readln().toIntOrNull() ?: 0
        when (opcao){
            1 -> cadastrarPessoas(agenda)
            2 -> pesquisarPorNome(agenda)
            3 -> classificarPorNome(agenda)
            4 -> apresentarContato(agenda)
            5 -> println("Sair do programa...")
            else -> println("Opção inválida. Tente novamente.")
        }
    }
        while (opcao !=5)
}
fun cadastrarPessoas(agenda: MutableList<Contato>){
    agenda.clear()
    for( i in 1..10){
        println("\nCadastro do contato $i:")
        print("Nome: ")
        val nome = readLine() ?: ""
        print("Telefone: ")
        val telefone = readLine() ?: ""
        agenda.add(Contato(nome, telefone))
    }
    println("Cadastro concluído!")
}
fun pesquisarPorNome(agenda: List<Contato>){
    println("\nDigite o nome a ser pesquisado: ")
    val nomeContato = readln()
    val contatoEncontrado = agenda.find{it.nome == nomeContato}

    if(contatoEncontrado != null){
        println("\nContato encontrado: ")
        println("Nome: ${contatoEncontrado.nome}")
        println("Telefone: ${contatoEncontrado.telefone}")
    }
    else{
        println("Contato não encontrado.")
    }
}
fun classificarPorNome(agenda:MutableList<Contato>){
    agenda.sortBy{it.nome}
    println("\nContato classificados por nome.")
}

fun apresentarContato(agenda: List<Contato>){
    if(agenda.isEmpty()){
        println("\nNenhum contato cadastrado.")
    }
    else{
        println("\n--- Lista de Contatos ---")
        agenda.forEach { contato ->
            println("Nome: ${contato.nome}")
            println("Telefone: ${contato.telefone}")
        }
    }
}