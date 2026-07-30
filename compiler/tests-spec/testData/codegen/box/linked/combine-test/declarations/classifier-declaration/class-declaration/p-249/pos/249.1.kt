// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 249 -> sentence 249
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 249 -> sentence 249
 *                declarations, function-declaration -> paragraph 249 -> sentence 249
 *                inheritance, inheriting -> paragraph 249 -> sentence 249
 * NUMBER: 1
 * DESCRIPTION: an interface member type parameter R is independent of interface T; contrasts with p-31 class Box map and p-231 producer without member type params
 */

// TESTCASE NUMBER: 1
interface Mapper<T> {
    fun <R> map(f: (T) -> R): R
}

class IntMapper : Mapper<Int> {
    override fun <R> map(f: (Int) -> R): R = f(1)
}

// TESTCASE NUMBER: 2
interface Transformer<T> {
    fun value(): T
    fun <U> transform(f: (T) -> U): U
}

class StringTransformer : Transformer<String> {
    override fun value(): String = "ab"
    override fun <U> transform(f: (String) -> U): U = f(value())
}

// TESTCASE NUMBER: 3
interface PairMapper<A, B> {
    fun left(): A
    fun right(): B
    fun <R> combine(f: (A, B) -> R): R
}

class MixPairMapper : PairMapper<String, Int> {
    override fun left(): String = "x"
    override fun right(): Int = 2
    override fun <R> combine(f: (String, Int) -> R): R = f(left(), right())
}

fun box(): String {
    if (IntMapper().map { it.toString() } != "1") return "NOK: int-map-string"
    if (IntMapper().map { it + 1 } != 2) return "NOK: int-map-int"
    val asMapper: Mapper<Int> = IntMapper()
    if (asMapper.map { it * 3 } != 3) return "NOK: via-mapper"

    if (StringTransformer().transform { it.length } != 2) return "NOK: string-transform"
    val asTransformer: Transformer<String> = StringTransformer()
    if (asTransformer.transform { it.uppercase() } != "AB") return "NOK: via-transformer"

    if (MixPairMapper().combine { a, b -> a + b } != "x2") return "NOK: combine"
    val asPair: PairMapper<String, Int> = MixPairMapper()
    if (asPair.combine { a, b -> "$a:$b" } != "x:2") return "NOK: via-pair"
    return "OK"
}
