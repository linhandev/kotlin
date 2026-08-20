// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: companion object function return type is inferred as String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        fun getValue() = "value"
    }
}

fun case_1() {
    checkSubtype<String>(Box.getValue())
}
