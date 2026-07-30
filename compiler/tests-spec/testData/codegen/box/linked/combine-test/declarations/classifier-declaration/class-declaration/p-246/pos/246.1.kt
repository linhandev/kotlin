// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 246 -> sentence 246
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 246 -> sentence 246
 *                declarations, function-declaration -> paragraph 246 -> sentence 246
 *                inheritance, inheriting -> paragraph 246 -> sentence 246
 * NUMBER: 1
 * DESCRIPTION: a generic interface default member may use the type parameter via other abstract members; contrasts with interface-declaration p-17 default returning T? and p-231 producer without defaults
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
    fun isNull(): Boolean = get() == null
}

class StringBox : Box<String> {
    override fun get(): String = "a"
}

class NullableBox : Box<String?> {
    override fun get(): String? = null
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    fun value(): T
    fun label(): String = "v=" + value().toString()
}

class IntHolder : Holder<Int> {
    override fun value(): Int = 7
}

// TESTCASE NUMBER: 3
interface PairBox<A, B> {
    fun left(): A
    fun right(): B
    fun joined(): String = left().toString() + ":" + right().toString()
}

class MixPair : PairBox<String, Int> {
    override fun left(): String = "x"
    override fun right(): Int = 2
}

fun box(): String {
    if (StringBox().get() != "a") return "NOK: string-get"
    if (StringBox().isNull()) return "NOK: string-not-null"
    if (NullableBox().get() != null) return "NOK: nullable-get"
    if (!NullableBox().isNull()) return "NOK: nullable-is-null"
    val asBox: Box<String> = StringBox()
    if (asBox.isNull()) return "NOK: via-box"

    if (IntHolder().value() != 7) return "NOK: int-value"
    if (IntHolder().label() != "v=7") return "NOK: int-label"
    val asHolder: Holder<Int> = IntHolder()
    if (asHolder.label() != "v=7") return "NOK: via-holder"

    if (MixPair().joined() != "x:2") return "NOK: joined"
    val asPair: PairBox<String, Int> = MixPair()
    if (asPair.joined() != "x:2") return "NOK: via-pair"
    return "OK"
}
