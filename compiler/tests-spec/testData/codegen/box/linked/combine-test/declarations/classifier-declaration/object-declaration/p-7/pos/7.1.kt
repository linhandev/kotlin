// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: object declaration identity differs from anonymous object expression
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

object One : I {
    override fun f(): Int = 1
}

fun test(): Boolean {
    val a: Any = One
    val b: Any = object : I {
        override fun f(): Int = 1
    }
    return a !== b
}

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
