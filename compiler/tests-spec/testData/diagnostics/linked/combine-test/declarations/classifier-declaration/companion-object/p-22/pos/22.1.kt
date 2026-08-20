// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: companion object generic create returns the argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun <T> create(value: T) = value
    }
}

fun case_1() {
    checkSubtype<String>(Box.create("hello"))
}
