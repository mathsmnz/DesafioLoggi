fun initPackages(): ArrayList<Pacote> {
    val input: ArrayList<String> = arrayListOf(
        "288355555123888",
        "335333555584333",
        "223343555124001",
        "002111555874555",
        "111188555654777",
        "111333555123333",
        "432055555123888",
        "079333555584333",
        "155333555124001",
        "333188555584333",
        "555288555123001",
        "111388555123555",
        "288000555367333",
        "066311555874001",
        "110333555123555",
        "333488555584333",
        "455448555123001",
        "022388555123555",
        "432044555845333",
        "034311555874001"
    )
    var retVal: ArrayList<Pacote> = ArrayList()
    for (s in input) {
        retVal.add(Pacote(s))
    }
    return retVal
}

fun initVendors(pacotes:ArrayList<Pacote>): HashSet<String> {
    var retVal: HashSet<String> = HashSet()
    for (p in pacotes){
        p.codVendedor?.let { retVal.add(it) }
    }
    retVal.sortedBy { it.toInt() }
    return retVal
}

fun filterPackages(
    origem: String,
    destino: String,
    vendedor: String,
    produto: String,
    packages: ArrayList<Pacote>
): ArrayList<Pacote> {
    var retVal: ArrayList<Pacote> = ArrayList()
    var tmp: ArrayList<Pacote> = ArrayList()
    retVal.addAll(packages)

    if (origem != "") {
        when (origem) {
            "centro" -> for (p in retVal) {
                if ((p.codOrigem?.toInt() ?: 0) !in 201..299) {
                    tmp.add(p)
                }
            }
            "nordeste" ->
                for (p in retVal) {
                    if ((p.codOrigem?.toInt() ?: 0) !in 300..399) {
                        tmp.add(p)
                    }
                }
            "norte" ->
                for (p in retVal) {
                    if ((p.codOrigem?.toInt() ?: 0) !in 400..499) {
                        tmp.add(p)
                    }
                }
            "sudeste" ->
                for (p in retVal) {
                    if ((p.codOrigem?.toInt() ?: 0) !in 1..99) {
                        tmp.add(p)
                    }
                }
            "sul" ->
                for (p in retVal) {
                    if ((p.codOrigem?.toInt() ?: 0) !in 100..199) {
                        tmp.add(p)
                    }
                }
        }
    }
    retVal.removeAll(tmp.toSet())
    tmp.clear()
    if (destino != "") {
        when (destino) {
            "centro" -> for (p in retVal) {
                if ((p.codDestino?.toInt() ?: 0) !in 201..299) {
                    tmp.add(p)
                }
            }
            "nordeste" ->
                for (p in retVal) {
                    if ((p.codDestino?.toInt() ?: 0) !in 300..399) {
                        tmp.add(p)
                    }
                }
            "norte" ->
                for (p in retVal) {
                    if ((p.codDestino?.toInt() ?: 0) !in 400..499) {
                        tmp.add(p)
                    }
                }
            "sudeste" ->
                for (p in retVal) {
                    if ((p.codDestino?.toInt() ?: 0) !in 1..99) {
                        tmp.add(p)
                    }
                }
            "sul" ->
                for (p in retVal) {
                    if ((p.codDestino?.toInt() ?: 0) !in 100..199) {
                        tmp.add(p)
                    }
                }
        }
    }

    retVal.removeAll(tmp.toSet())
    tmp.clear()

    if (vendedor != "") {
        for (pacote in retVal) {
            if (pacote.codVendedor != vendedor) {
                tmp.add(pacote)
            }
        }
    }

    retVal.removeAll(tmp.toSet())
    tmp.clear()

    if (produto != "") {
        for (pacote in retVal) {
            if (pacote.codProduto != produto) {
                tmp.add(pacote)
            }
        }
    }

    retVal.removeAll(tmp.toSet())
    tmp.clear()

    return retVal
}