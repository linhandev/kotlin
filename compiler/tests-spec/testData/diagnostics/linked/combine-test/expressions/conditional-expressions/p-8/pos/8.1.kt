// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 *                type-inference, smart-casts -> paragraph 8 -> sentence 8
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast and member function call in branch type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hello"
    checkSubtype<String>(if (x is String) x.uppercase() else "")
}
