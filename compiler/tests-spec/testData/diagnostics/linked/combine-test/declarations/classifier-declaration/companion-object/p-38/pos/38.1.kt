// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: with(companion) can call companion members
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x = 42
        fun readX() = x
    }
}

fun case_1() {
    checkSubtype<Int>(with(Box) { readX() })
}
