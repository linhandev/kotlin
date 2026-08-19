// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: object declaration identity differs from anonymous object expression
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun f(): Int
}

object One : I {
    override fun f(): Int = 1
}

fun case_1() {
    val a: Any = One
    val b: Any = object : I {
        override fun f(): Int = 1
    }
    checkSubtype<Boolean>(a !== b)
}
