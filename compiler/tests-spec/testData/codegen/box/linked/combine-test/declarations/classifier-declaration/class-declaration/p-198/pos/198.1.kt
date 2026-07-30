// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 198 -> sentence 198
 * PRIMARY LINKS: inheritance, overriding -> paragraph 198 -> sentence 198
 *                declarations, declarations-with-type-parameters -> paragraph 198 -> sentence 198
 *                inheritance, inheriting -> paragraph 198 -> sentence 198
 * NUMBER: 1
 * DESCRIPTION: overriding a generic open member keeps the substituted signature; subclass values are observed through base-typed references
 */

// TESTCASE NUMBER: 1
open class Box<T> {
    open fun get(): T? = null
}

class IntBox : Box<Int>() {
    override fun get(): Int? = 1
}

// TESTCASE NUMBER: 2
open class Holder<T>(open val item: T)

class StringHolder(item: String) : Holder<String>(item) {
    override val item: String get() = super.item + "!"
}

// TESTCASE NUMBER: 3
open class PairBox<A, B> {
    open fun left(): A? = null
    open fun right(): B? = null
}

class StringIntBox : PairBox<String, Int>() {
    override fun left(): String? = "ok"
    override fun right(): Int? = 7
}

fun box(): String {
    if (IntBox().get() != 1) return "NOK: int-box"
    if (Box<Int>().get() != null) return "NOK: box-null"
    val asBox: Box<Int> = IntBox()
    if (asBox.get() != 1) return "NOK: box-ref"

    if (StringHolder("hi").item != "hi!") return "NOK: string-holder"
    if (Holder("hi").item != "hi") return "NOK: holder-base"
    val asHolder: Holder<String> = StringHolder("hi")
    if (asHolder.item != "hi!") return "NOK: holder-ref"

    val pair = StringIntBox()
    if (pair.left() != "ok") return "NOK: left"
    if (pair.right() != 7) return "NOK: right"
    val asPair: PairBox<String, Int> = pair
    if (asPair.left() != "ok") return "NOK: pair-left-ref"
    if (asPair.right() != 7) return "NOK: pair-right-ref"
    return "OK"
}
