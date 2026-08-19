// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 16 -> sentence 16
 *                overload-resolution, resolving-callable-references -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: overloaded function reference ::f without expected type is ambiguous, verifying compile-time failure
 */

fun f(x: Int): Int = x
fun f(x: String): String = x

// TESTCASE NUMBER: 1
fun case1() = ::<!OVERLOAD_RESOLUTION_AMBIGUITY!>f<!>
