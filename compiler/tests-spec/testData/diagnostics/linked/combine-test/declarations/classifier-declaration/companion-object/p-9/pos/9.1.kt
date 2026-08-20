// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: companion object invoke(String) is called when no matching constructor exists
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        operator fun invoke(name: String) = "Hello $name"
    }
}

fun case_1() {
    checkSubtype<String>(Box("World"))
}
