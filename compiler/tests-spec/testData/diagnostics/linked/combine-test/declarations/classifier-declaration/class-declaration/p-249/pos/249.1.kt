// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 249 -> sentence 249
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 249 -> sentence 249
 *                declarations, function-declaration -> paragraph 249 -> sentence 249
 *                inheritance, inheriting -> paragraph 249 -> sentence 249
 * NUMBER: 1
 * DESCRIPTION: precise types when an interface member type parameter is independent of the interface type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Mapper<T> {
    fun <R> map(f: (T) -> R): R
}

class IntMapper : Mapper<Int> {
    override fun <R> map(f: (Int) -> R): R = f(1)
}

fun case1() {
    val m = IntMapper()
    m checkType { check<IntMapper>() }
    checkSubtype<Mapper<Int>>(m)
    m.map { it.toString() } checkType { check<String>() }
    m.map { it + 1 } checkType { check<Int>() }
    val asMapper: Mapper<Int> = m
    asMapper.map { it * 3 } checkType { check<Int>() }
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

fun case2() {
    val t = StringTransformer()
    checkSubtype<Transformer<String>>(t)
    t.value() checkType { check<String>() }
    t.transform { it.length } checkType { check<Int>() }
    t.transform { it.uppercase() } checkType { check<String>() }
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

fun case3() {
    val p = MixPairMapper()
    checkSubtype<PairMapper<String, Int>>(p)
    p.left() checkType { check<String>() }
    p.right() checkType { check<Int>() }
    p.combine { a, b -> a + b } checkType { check<String>() }
}
