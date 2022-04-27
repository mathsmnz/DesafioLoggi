fun main() {
    var a = -1
    val packages: ArrayList<Pacote> = initPackages()
    val vendors: HashSet<String> = initVendors(packages)
    val products: ArrayList<String> = arrayListOf("001", "111", "333", "555", "888")
    val regions: ArrayList<String> = arrayListOf("sudeste", "sul", "centro", "nordeste", "norte")
    while (a != 0) {
        println("|| ========================== ||")
        println("|| => Questao [1-4] =========== ||")
        println("|| => Questao [2] =========== ||")
        println("|| => Questao [3] =========== ||")
        println("|| => Questao [5] =========== ||")
        println("|| => Questao [6] =========== ||")
        println("|| => Questao [7] =========== ||")
        println("|| => Questao [8-9] ========= ||")
        println("|| => Questao [10] ========== ||")
        println("|| => [0] Sair ============== ||")
        println("|| ========================== ||")
        print("|| => Insira sua escolha: ")
        a = try {
            readLine()?.toInt() ?: 11
        } catch (e: NumberFormatException) {
            11
        }
        when (a) {
            0 -> println("|| => Saindo")
            1 -> {
                for (r in regions){
                    filterByRegion(packages, r)
                }
            }
            2 -> {
                for (p in packages) {
                    println("|| = Pacote ${packages.lastIndexOf(p)}:\n$p")
                }
            }
            3 -> {
                val pkgResult: ArrayList<Pacote> = filterPackages("sul", "", "", "888", packages)
                for (p in pkgResult) {
                    println("|| = Pacote ${pkgResult.lastIndexOf(p)}:\n$p")
                }
            }
            5 -> {
                for (v in vendors) {
                    filterByVendor(packages, v)
                }
            }
            6 -> {
                for(r in regions){
                    println("|| =|> $r")
                    for(p in products){
                        filterByProduct(filterPackages("", r, "", "", packages), p)
                    }
                }

            }
            7 -> {
                var pkgResult: ArrayList<Pacote> = filterPackages("centro", "", "", "", packages)
                pkgResult.addAll(filterPackages("", "centro", "", "", packages))
                pkgResult.addAll(filterPackages("", "norte", "", "", packages))
                for (p in pkgResult) {
                    println("|| = Pacote ${pkgResult.lastIndexOf(p)}:\n$p")
                }
            }
            8 -> {
                var pkgResult: ArrayList<Pacote> = filterPackages("centro", "", "", "", packages)
                pkgResult.addAll(filterPackages("", "centro", "", "", packages))
                val tmp: ArrayList<Pacote> = filterPackages("", "norte", "", "", packages)
                tmp.sortBy{ it.codProduto?.toInt() }
                pkgResult.addAll(tmp)
                for (p in pkgResult) {
                    println("|| = Pacote ${pkgResult.lastIndexOf(p)}:\n$p")
                }
            }
            10 -> {
                var count: Int = 0
                for (p in packages) {
                    if (p.status == false) {
                        println("|| = Pacote ${count}:\n$p")
                        count++
                    }

                }
                println("|| ==> $count - Pacotes inválidos")
            }
            else -> println("|| => Entrada inválida")
        }
    }
}

private fun filterByRegion(packages: ArrayList<Pacote>, str: String) {
    val pkgResult: ArrayList<Pacote> = filterPackages("", str, "", "", packages)
    println("|| ====|> $str")
    if (pkgResult.isEmpty()) {
        println("|| => Sem pacotes")
    } else {
        for (p in pkgResult) {
            println("|| = Pacote ${pkgResult.lastIndexOf(p)}:\n$p")
        }
        println("|| ==> ${pkgResult.size} - Pacotes para região $str")
    }
}

private fun filterByProduct(packages: ArrayList<Pacote>, str: String) {
    val pkgResult: ArrayList<Pacote> = filterPackages("", "", "", str, packages)
    println("|| ====|> $str")
    if (pkgResult.isEmpty()) {
        println("|| => Sem pacotes")
    } else {
        for (p in pkgResult) {
            println("|| = Pacote ${pkgResult.lastIndexOf(p)}:\n$p")
        }
        println("|| ==> ${pkgResult.size} - Pacotes de produto: $str")
    }
}

private fun filterByVendor(packages: ArrayList<Pacote>, str: String) {
    val pkgResult: ArrayList<Pacote> = filterPackages("", "", str, "", packages)
    println("|| ====|> $str")
    if (pkgResult.isEmpty()) {
        println("|| => Sem pacotes")
    } else {
        for (p in pkgResult) {
            println("|| = Pacote ${pkgResult.lastIndexOf(p)}:\n$p")
        }
        println("|| ==> ${pkgResult.size} - Pacotes de vendedor: $str")
    }
}
