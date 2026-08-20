// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: getter calls other method
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    fun compute() = 42
    val x get() = compute()
}

fun case_1() {
    checkSubtype<Int>(Box().x)
}
