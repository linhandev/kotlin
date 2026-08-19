// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: simple identifier interpolation resolves to local variable access type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val name = "world"
    checkSubtype<String>("hello, $name")
}
