import java.util.regex.Matcher
import java.util.regex.Pattern

class Pacote(var cod: String?) {
    var status: Boolean = false
    var codOrigem: String? = null
    var codDestino: String? = null
    var codLoggi: String? = null
    var codVendedor: String? = null
    var codProduto: String? = null

    init {
        val validProducts: ArrayList<Int> = arrayListOf(
            1,
            111,
            333,
            555,
            888
        )
        val invalidVendors: ArrayList<Int> = arrayListOf(
            367
        )
        val patternString = "([0-9]{3})"
        val pattern: Pattern = Pattern.compile(patternString)
        val matcher: Matcher? = cod?.let { pattern.matcher(it) }
        val stringList: ArrayList<String> = ArrayList()
        if (matcher != null) {
            while (matcher.find()) {
                stringList.add(matcher.group())
            }
        }else{
            this.status = false
        }
        if (stringList.size != 5) {
            this.status = false
            println("|| => Entrada invalida, tamanho invalido = ${stringList.size}")
        } else {
            var valid = 0
            for (s in stringList) {
                val value = s.toInt()
                if (value in 0..999) {
                    valid++
                }
            }
            if (valid != 5) {
                this.status = false
                println("|| => Entrada invalida, trinca faltando")
            } else {
                if (invalidVendors.contains(stringList[3].toInt())) {
                    this.status = false
                    println("|| => Entrada invalida, vendedor invalido")
                } else {
                    if ((stringList[0].toInt() in 1..499) && (stringList[1].toInt() in 1..499)) {
                        if (validProducts.contains(stringList[4].toInt())) {
                            if (stringList[4].toInt() == 1 && stringList[0].toInt() in 201..299) {
                                this.status = false
                                println("|| => Entrada invalida, n??o ?? poss??vel entregar este produto desta regi??o")
                            } else {
                                this.status = true
                                this.codOrigem = stringList[0]
                                this.codDestino = stringList[1]
                                this.codLoggi = stringList[2]
                                this.codVendedor = stringList[3]
                                this.codProduto = stringList[4]
                            }
                        } else {
                            this.status = false
                            println("|| => Entrada invalida, produto invalido")
                        }
                    } else {
                        this.status = false
                        println("|| => Entrada invalida, regi??o inv??lida")
                    }
                }
            }
        }
    }

    private fun getRegiao(regiao: String): String {
        return if (regiao.toInt() in 1..99) {
            "regi??o Sudeste"
        } else if (regiao.toInt() in 100..199) {
            "regi??o Sul"
        } else if (regiao.toInt() in 200..299) {
            "regi??o Centro-oeste"
        } else if (regiao.toInt() in 300..399) {
            "regi??o Nordeste"
        } else if (regiao.toInt() in 400..499) {
            "regi??o Norte"
        } else {
            ""
        }
    }

    private fun getProduto(): String{
        return when (codProduto?.toInt()) {
            1 -> "J??ias"
            111 -> "Livros"
            333 -> "Eletr??nicos"
            555 -> "Bebidas"
            888 -> "Brinquedos"
            else -> "N??o encontrado"
        }
    }

    override fun toString(): String {
        return if (status) {
            "       C??digo: $cod\n" +
                    "       Status: C??digo V??lido\n" +
                    "       Regi??o de origem: Cidade $codOrigem, ${codOrigem?.let { getRegiao(it) }}\n" +
                    "       Regi??o de destino: Cidade $codDestino, ${codDestino?.let { getRegiao(it) }}\n" +
                    "       C??digo Loggi: $codLoggi\n" +
                    "       C??digo do vendedor do produto: $codVendedor\n" +
                    "       Tipo do produto: ${getProduto()}\n"
        } else {
            "       C??digo: $cod\n" +
                    "       Status: Inv??lido\n"
        }
    }
}