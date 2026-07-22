// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, constant-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: const val with string template interpolating mutable variable reports CONST_VAL_WITH_NON_CONST_INITIALIZER
 */

private var v = 1

// TESTCASE NUMBER: 1
const val s = <!CONST_VAL_WITH_NON_CONST_INITIALIZER!>"x${v}"<!>
