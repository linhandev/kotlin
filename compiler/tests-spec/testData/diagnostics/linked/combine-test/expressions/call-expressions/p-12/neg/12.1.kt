// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 12 -> sentence 12
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: default parameter value cannot reference a parameter declared later in the parameter list
 */

// TESTCASE NUMBER: 1
fun f(a: Int = <!UNINITIALIZED_PARAMETER!>b<!>, b: Int = 1): Int = a + b
