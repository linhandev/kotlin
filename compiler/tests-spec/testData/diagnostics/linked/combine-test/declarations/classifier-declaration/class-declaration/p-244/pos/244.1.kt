// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 244 -> sentence 244
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 244 -> sentence 244
 *                inheritance, inheriting -> paragraph 244 -> sentence 244
 * NUMBER: 1
 * DESCRIPTION: precise types when a class implements a multi-parameter generic interface with independently fixed type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface PairLike<K, V> {
    val key: K
    val value: V
}

class StringIntPair : PairLike<String, Int> {
    override val key: String = "k"
    override val value: Int = 1
}

fun case1() {
    val p = StringIntPair()
    p checkType { check<StringIntPair>() }
    checkSubtype<PairLike<String, Int>>(p)
    p.key checkType { check<String>() }
    p.value checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface Entry<A, B> {
    fun left(): A
    fun right(): B
}

class BoolLongEntry : Entry<Boolean, Long> {
    override fun left(): Boolean = true
    override fun right(): Long = 9L
}

fun case2() {
    val e = BoolLongEntry()
    checkSubtype<Entry<Boolean, Long>>(e)
    e.left() checkType { check<Boolean>() }
    e.right() checkType { check<Long>() }
}

// TESTCASE NUMBER: 3
interface TripleLike<A, B, C> {
    fun first(): A
    fun second(): B
    fun third(): C
}

class MixTriple : TripleLike<String, Int, Boolean> {
    override fun first(): String = "x"
    override fun second(): Int = 2
    override fun third(): Boolean = false
}

fun case3() {
    val t = MixTriple()
    checkSubtype<TripleLike<String, Int, Boolean>>(t)
    t.first() checkType { check<String>() }
    t.second() checkType { check<Int>() }
    t.third() checkType { check<Boolean>() }
}
