// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: is over enum entry in when is prohibited; use enum constant comparison
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(e: E): Int = when (e) {
    is <!IS_ENUM_ENTRY!>E.<!ENUM_ENTRY_AS_TYPE!>A<!><!> -> 1
    is <!IS_ENUM_ENTRY!>E.<!ENUM_ENTRY_AS_TYPE!>B<!><!> -> 2
}
