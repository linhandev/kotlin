// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: object literal can be returned from function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Factory {
    fun create(): Int
}

fun make(): Factory = object : Factory {
    override fun create(): Int = 42
}

fun case_1(): Int = make().create()

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
