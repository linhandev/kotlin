// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 244 -> sentence 244
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 244 -> sentence 244
 *                inheritance, inheriting -> paragraph 244 -> sentence 244
 * NUMBER: 1
 * DESCRIPTION: a class may implement a multi-parameter generic interface by fixing each type argument independently; contrasts with p-231 two-parameter producers and p-242 single shared type-parameter inheritance
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

// TESTCASE NUMBER: 2
interface Entry<A, B> {
    fun left(): A
    fun right(): B
}

class BoolLongEntry : Entry<Boolean, Long> {
    override fun left(): Boolean = true
    override fun right(): Long = 9L
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

fun box(): String {
    if (StringIntPair().key != "k") return "NOK: pair-key"
    if (StringIntPair().value != 1) return "NOK: pair-value"
    val asPair: PairLike<String, Int> = StringIntPair()
    if (asPair.key != "k" || asPair.value != 1) return "NOK: via-pair"

    if (!BoolLongEntry().left()) return "NOK: entry-left"
    if (BoolLongEntry().right() != 9L) return "NOK: entry-right"
    val asEntry: Entry<Boolean, Long> = BoolLongEntry()
    if (!asEntry.left() || asEntry.right() != 9L) return "NOK: via-entry"

    if (MixTriple().first() != "x") return "NOK: triple-first"
    if (MixTriple().second() != 2) return "NOK: triple-second"
    if (MixTriple().third()) return "NOK: triple-third"
    val asTriple: TripleLike<String, Int, Boolean> = MixTriple()
    if (asTriple.first() != "x" || asTriple.second() != 2 || asTriple.third()) return "NOK: via-triple"
    return "OK"
}
