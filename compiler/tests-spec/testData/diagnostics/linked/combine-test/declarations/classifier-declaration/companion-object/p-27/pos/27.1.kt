// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: companion object higher-order function accepts trailing lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun process(value: Int, fn: (Int) -> Int) = fn(value)
    }
}

fun case_1() {
    checkSubtype<Int>(Box.process(5) { it * 2 })
}
