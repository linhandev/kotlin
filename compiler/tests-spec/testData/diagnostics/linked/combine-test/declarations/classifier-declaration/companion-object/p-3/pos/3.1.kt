// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: companion object function can be called via ClassName.fun()
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun foo() = "hello"
    }
}

fun case_1() {
    checkSubtype<String>(Box.foo())
}
