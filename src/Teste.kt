fun main() {
    var a = 0
    var packages: ArrayList<Pacote> = ArrayList()
    val validProducts: ArrayList<Int> = arrayListOf(
        1,
        111,
        333,
        555,
        888
    )
    while (a != 4) {
        println("|| ========================== ||")
        println("|| => [0] Entrar codigo ===== ||")
        println("|| => [1] Historico ========= ||")
        println("|| => [2] Buscar ============ ||")
        println("|| => [3] Usar preset loggi = ||")
        println("|| => [4] Sair ============== ||")
        println("|| ========================== ||")
        print("|| => Insira sua escolha: ")
        a = try {
            readLine()?.toInt() ?: 5
        } catch (e: NumberFormatException) {
            5
        }
        when (a) {
            0 -> {
                print("|| => Insira o codigo: ")
                val input: String = readLine() ?: ""
                println(input)
                packages.add(Pacote(input))
            }
            1 -> if (packages.isEmpty()) {
                println("|| => Historico vazio")
            } else {
                for (p in packages) {
                    println("|| = Pacote ${packages.lastIndexOf(p)}:\n$p")
                }
            }
            2 -> {
                var b = 0
                var filter: Array<String> = arrayOf("", "", "", "")
                while (b != 7) {
                    println("|| ==================================== ||")
                    println("|| => [0] Aplicar filtro por origem  == ||")
                    println("|| => [1] Aplicar filtro por destino == ||")
                    println("|| => [2] Aplicar filtro por vendedor = ||")
                    println("|| => [3] Aplicar filtro por produto == ||")
                    println("|| => [4] Filtrar por invalidos ======= ||")
                    println("|| => [5] Limpar filtros ============== ||")
                    println("|| => [6] Filtrar ===================== ||")
                    println("|| => [7] Sair =======================  ||")
                    println("|| ==================================== ||")
                    println("|| => Filtros aplicados -> ${filter.joinToString(" ")}")
                    print("|| => Insira o filtro que deseja aplicar: ")

                    b = try {
                        readLine()?.toInt() ?: 8
                    } catch (e: NumberFormatException) {
                        8
                    }

                    when (b) {
                        0 -> {
                            println("|| ===================== ||")
                            println("|| => [0] Centro-oeste = ||")
                            println("|| => [1] Nordeste ===== ||")
                            println("|| => [2] Norte ======== ||")
                            println("|| => [3] Sudeste ====== ||")
                            println("|| => [4] Sul =========  ||")
                            println("|| ===================== ||")
                            print("|| => Insira sua escolha:")
                            filter[0] = readLine() ?: ""
                            when (filter[0]) {
                                "0" -> filter[0] = "centro"
                                "1" -> filter[0] = "nordeste"
                                "2" -> filter[0] = "norte"
                                "3" -> filter[0] = "sudeste"
                                "4" -> filter[0] = "sul"
                                else -> {
                                    println("|| => Entrada inválida")
                                    filter[0] = ""
                                }
                            }
                        }
                        1 -> {
                            println("|| ===================== ||")
                            println("|| => [0] Centro-oeste = ||")
                            println("|| => [1] Nordeste ===== ||")
                            println("|| => [2] Norte ======== ||")
                            println("|| => [3] Sudeste ====== ||")
                            println("|| => [4] Sul =========  ||")
                            println("|| ===================== ||")
                            print("|| => Insira sua escolha:")
                            filter[1] = readLine() ?: ""
                            when (filter[1]) {
                                "0" -> filter[1] = "centro"
                                "1" -> filter[1] = "nordeste"
                                "2" -> filter[1] = "norte"
                                "3" -> filter[1] = "sudeste"
                                "4" -> filter[1] = "sul"
                                else -> {
                                    println("|| => Entrada inválida")
                                    filter[1] = ""
                                }
                            }
                        }
                        2 -> {
                            print("|| => Insira o codigo de vendedor: ")
                            filter[2] = readLine() ?: ""
                            try {
                                var tmp = filter[2].toInt()
                            } catch (e: NumberFormatException) {
                                println("|| => Entrada inválida")
                                filter[2] = ""
                            }
                            if (filter[2] != "" && filter[2].toInt() !in 0..999) {
                                filter[2] = ""
                            }
                        }
                        3 -> {
                            println("|| ====================== ||")
                            println("|| => [001] Joias  ====== ||")
                            println("|| => [111] Livros ====== ||")
                            println("|| => [333] Eletrônicos = ||")
                            println("|| => [555] Bebidas ===== ||")
                            println("|| => [888] Brinquedos =  ||")
                            println("|| ====================== ||")
                            print("|| => Insira sua escolha:")
                            filter[3] = readLine() ?: ""
                            try {
                                var tmp = filter[3].toInt()
                            } catch (e: NumberFormatException) {
                                println("|| => Entrada inválida")
                                filter[3] = ""
                            }

                            if (filter[3] != "" && !validProducts.contains(filter[3].toInt())) {
                                println("|| => Produto nao encontrado")
                                filter[3] = ""
                            }
                        }
                        4 -> {
                            for (p in packages) {
                                if (p.status == false) {
                                    println("|| = Pacote ${packages.lastIndexOf(p)}:\n$p")
                                }
                            }
                        }
                        5 -> {
                            filter = arrayOf("", "", "", "")
                        }
                        6 -> {
                            val filteredPackages = filterPackages(filter[0], filter[1], filter[2], filter[3], packages)
                            if (filteredPackages.isNotEmpty()) {
                                for (p in filteredPackages) {
                                    println("|| = Pacote ${packages.lastIndexOf(p)}:\n$p")
                                }
                                println("|| => ${filteredPackages.size} atendem os filtros")
                            } else {
                                println("|| => Nada foi encontrado")
                            }
                        }
                        else -> {
                            println("|| => Entrada invalida, por favor selecione a opção correta")
                        }
                    }
                }
            }
            3 -> if (packages.isEmpty()) {
                val value = initPackages()
                packages.addAll(value)
            }
            4 -> println("|| => Saindo")
            else -> println("|| => Entrada invalida, por favor selecione a opção correta")
        }
    }
}
