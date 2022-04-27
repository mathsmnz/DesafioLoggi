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
                                println("|| => Entrada invalida, não é possível entregar este produto nesta região")
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
                        println("|| => Entrada invalida, região inválida")
                    }
                }
            }
        }
    }

    private fun getRegiao(regiao: String): String {
        return if (regiao.toInt() in 1..99) {
            "região Sudeste"
        } else if (regiao.toInt() in 100..199) {
            "região Sul"
        } else if (regiao.toInt() in 200..299) {
            "região Centro-oeste"
        } else if (regiao.toInt() in 300..399) {
            "região Nordeste"
        } else if (regiao.toInt() in 400..499) {
            "região Norte"
        } else {
            ""
        }
    }

    private fun getProduto(): String{
        return when (codProduto?.toInt()) {
            1 -> "Jóias"
            111 -> "Livros"
            333 -> "Eletrônicos"
            555 -> "Bebidas"
            888 -> "Brinquedos"
            else -> "Não encontrado"
        }
    }

    override fun toString(): String {
        return if (status) {
            "       Código: $cod\n" +
                    "       Status: Código Válido\n" +
                    "       Região de origem: Cidade $codOrigem, ${codOrigem?.let { getRegiao(it) }}\n" +
                    "       Região de destino: Cidade $codDestino, ${codDestino?.let { getRegiao(it) }}\n" +
                    "       Código Loggi: $codLoggi\n" +
                    "       Código do vendedor do produto: $codVendedor\n" +
                    "       Tipo do produto: ${getProduto()}\n"
        } else {
            "       Código: $cod\n" +
                    "       Status: Inválido\n"
        }
    }
}