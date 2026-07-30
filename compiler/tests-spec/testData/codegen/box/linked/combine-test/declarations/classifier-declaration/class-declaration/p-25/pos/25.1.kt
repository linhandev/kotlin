// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 25 -> sentence 25
 *                declarations, classifier-declaration, interface-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: class implements generic interface with concrete type argument
 */

// TESTCASE NUMBER: 1
interface Sink<T> {
    fun accept(x: T)
}

class IntSink : Sink<Int> {
    var last: Int = -1
    override fun accept(x: Int) {
        last = x
    }
}

fun test(): Int {
    val s = IntSink()
    s.accept(1)
    return s.last
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
