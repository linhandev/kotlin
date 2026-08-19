// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 258 -> sentence 258
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 258 -> sentence 258
 *                declarations, property-declaration -> paragraph 258 -> sentence 258
 *                inheritance, inheriting -> paragraph 258 -> sentence 258
 * NUMBER: 1
 * DESCRIPTION: a primary-constructor override val can bind a generic interface property; contrasts with p-163/p-89 non-generic override-val ctors, p-247 Holder used for star-projection reads, and p-255 nullable property overrides in body
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    val v: T
}

class IntBox(override val v: Int) : Box<Int>

// TESTCASE NUMBER: 2
interface Holder<T> {
    val current: T
}

class StringHolder(override val current: String) : Holder<String>

// TESTCASE NUMBER: 3
interface PairLike<A, B> {
    val left: A
    val right: B
}

class MixPair(override val left: String, override val right: Int) : PairLike<String, Int>

fun box(): String {
    if (IntBox(2).v != 2) return "NOK: int-box"
    val asBox: Box<Int> = IntBox(2)
    if (asBox.v != 2) return "NOK: via-box"

    if (StringHolder("ok").current != "ok") return "NOK: string-holder"
    val asHolder: Holder<String> = StringHolder("ok")
    if (asHolder.current != "ok") return "NOK: via-holder"

    if (MixPair("x", 7).left != "x") return "NOK: mix-left"
    if (MixPair("x", 7).right != 7) return "NOK: mix-right"
    val asPair: PairLike<String, Int> = MixPair("x", 7)
    if (asPair.left != "x" || asPair.right != 7) return "NOK: via-pair"
    return "OK"
}
