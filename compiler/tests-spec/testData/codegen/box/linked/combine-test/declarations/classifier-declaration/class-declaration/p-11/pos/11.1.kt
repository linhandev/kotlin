// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 11 -> sentence 11
 *                declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: in variance allows InBox of Number to be used as InBox of Int
 */

// TESTCASE NUMBER: 1
class InBox<in T> {
    private var last: Any? = null
    fun accept(x: T) {
        last = x
    }
    fun lastValue(): Any? = last
}

fun test(): Any? {
    val b: InBox<Int> = InBox<Number>()
    b.accept(1)
    return b.lastValue()
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
