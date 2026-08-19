// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 41 -> sentence 41
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 41 -> sentence 41
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: constructor call with explicit type argument fails when value arguments do not match
 */

// TESTCASE NUMBER: 1
fun test() = listOf<String>(<!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>, <!CONSTANT_EXPECTED_TYPE_MISMATCH!>2<!>)
