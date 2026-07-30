// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: is List star then as? List String reports UNCHECKED_CAST
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Any) {
    checkSubtype<List<String>?>(
        if (x is List<*>) x <!UNCHECKED_CAST!>as? List<String><!> else null
    )
}
