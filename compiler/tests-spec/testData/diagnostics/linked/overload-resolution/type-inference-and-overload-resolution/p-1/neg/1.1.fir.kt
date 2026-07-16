// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, type-inference-and-overload-resolution -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lambda type inference cannot disambiguate overloaded pick117N before overload resolution completes
 */

fun pick117N(f: (Int) -> Int): Int = 1
fun pick117N(f: (String) -> String): String = ""

// TESTCASE NUMBER: 1
fun case_1() = <!OVERLOAD_RESOLUTION_AMBIGUITY!>pick117N<!> { <!CANNOT_INFER_VALUE_PARAMETER_TYPE!>x<!> -> x }
