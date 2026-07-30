// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 231 -> sentence 231
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 231 -> sentence 231
 *                inheritance, inheriting -> paragraph 231 -> sentence 231
 * NUMBER: 1
 * DESCRIPTION: a class declaration may implement a single-parameter generic interface by fixing T and overriding a producer member; contrasts with p-72 open-class Box, interface-declaration p-17 default empty(), and next-point consumer Sink type arguments
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun get(): T
}

class IntBox : Box<Int> {
    override fun get(): Int = 1
}

// TESTCASE NUMBER: 2
interface Holder<T> {
    fun value(): T
}

class StringHolder : Holder<String> {
    override fun value(): String = "ok"
}

// TESTCASE NUMBER: 3
interface PairBox<A, B> {
    fun left(): A
    fun right(): B
}

class StringIntPair : PairBox<String, Int> {
    override fun left(): String = "x"
    override fun right(): Int = 7
}

fun box(): String {
    if (IntBox().get() != 1) return "NOK: int-box"
    val asBox: Box<Int> = IntBox()
    if (asBox.get() != 1) return "NOK: via-box"
    if ((object : Box<Boolean> { override fun get(): Boolean = true }).get() != true) return "NOK: bool-anon"

    if (StringHolder().value() != "ok") return "NOK: string-holder"
    val asHolder: Holder<String> = StringHolder()
    if (asHolder.value() != "ok") return "NOK: via-holder"

    if (StringIntPair().left() != "x") return "NOK: pair-left"
    if (StringIntPair().right() != 7) return "NOK: pair-right"
    val asPair: PairBox<String, Int> = StringIntPair()
    if (asPair.left() != "x" || asPair.right() != 7) return "NOK: via-pair"
    return "OK"
}
