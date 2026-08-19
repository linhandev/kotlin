// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 6 -> sentence 6
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: positional argument after named argument is rejected
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int, c: Int): Int = a + b + c

fun test(): Int = f(c = 1, <!MIXING_NAMED_AND_POSITIONED_ARGUMENTS!>2<!>, <!MIXING_NAMED_AND_POSITIONED_ARGUMENTS!>3<!>)
