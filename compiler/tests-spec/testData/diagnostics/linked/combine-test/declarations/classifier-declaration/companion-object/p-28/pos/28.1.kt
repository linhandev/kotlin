// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: companion object String property can use lazy delegate
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val lazyValue: String by lazy { "lazy" }
    }
}

fun case_1() {
    checkSubtype<String>(Box.lazyValue)
}
