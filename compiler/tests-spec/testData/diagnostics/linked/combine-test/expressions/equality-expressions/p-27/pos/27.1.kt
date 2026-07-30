// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: == null with custom equals infers Boolean with SENSELESS_COMPARISON
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C {
    override fun equals(other: Any?): Boolean = other != null
}

fun case1() {
    checkSubtype<Boolean>(<!SENSELESS_COMPARISON!>C() == null<!>)
}
