// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: protected property accessible via subclass method
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    protected val x: Int = 42
}

class Derived : Base() {
    fun readX() = x
}

fun case_1() {
    checkSubtype<Int>(Derived().readX())
}
