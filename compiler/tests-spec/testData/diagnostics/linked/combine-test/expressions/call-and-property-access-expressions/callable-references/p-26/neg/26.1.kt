// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 26 -> sentence 26
 *                type-system, introduction-1 -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: bound callable reference on nullable receiver String? cannot be created without safe call or smart cast, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
fun case1(s: String?): Int? {
    val f: () -> Int? = <!TYPE_MISMATCH!>s<!UNSAFE_CALL!>.<!>length<!>
    return f()
}
