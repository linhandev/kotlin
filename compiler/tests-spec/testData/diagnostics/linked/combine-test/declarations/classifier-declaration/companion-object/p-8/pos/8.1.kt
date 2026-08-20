// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: companion object invoke() is called when ClassName() has private constructor
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box private constructor() {
    companion object {
        operator fun invoke() = "called"
    }
}

fun case_1() {
    checkSubtype<String>(Box())
}
