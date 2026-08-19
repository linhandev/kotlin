// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 2 -> sentence 2
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: as? infers String? for mismatch, null literal, and nullable ref
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = 1
    checkSubtype<String?>(x as? String)
    checkSubtype<String?>(null <!CAST_NEVER_SUCCEEDS!>as?<!> String)
    val s: String? = null
    checkSubtype<String?>(s <!USELESS_CAST!>as? String<!>)
}
