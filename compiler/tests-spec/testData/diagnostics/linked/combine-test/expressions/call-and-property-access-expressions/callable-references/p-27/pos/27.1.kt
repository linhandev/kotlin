// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 27 -> sentence 27
 *                type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                type-inference, smart-casts -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: after smart cast via requireNotNull, bound member reference s::length infers type () -> Int on non-null String, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    requireNotNull(s)
    val f: () -> Int = s::length
    checkSubtype<() -> Int>(f)
}
