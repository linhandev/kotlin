// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 21 -> sentence 21
 *                declarations, classifier-declaration, companion-object -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: companion object can implement an interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Factory {
    fun create(): Int
}

class Host {
    companion object : Factory {
        override fun create(): Int = 42
    }
}

fun case_1() {
    checkSubtype<Int>(Host.create())
}
