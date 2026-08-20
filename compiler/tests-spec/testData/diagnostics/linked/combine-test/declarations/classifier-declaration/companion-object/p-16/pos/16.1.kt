// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: companion object property can use lazy delegate
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val x: Int by lazy { 42 }
    }
}

fun case_1() {
    checkSubtype<Int>(Box.x)
}
