// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: five-step lambda statement processing — step 2 overload resolution before lambda body
 * HELPERS: checkType
 */

fun pick1432(f: () -> Int): Int = f()

fun pick1432(f: () -> String): String = f()

// TESTCASE NUMBER: 1
fun case_1() {
    <!OVERLOAD_RESOLUTION_AMBIGUITY!>pick1432<!> { 42 }
}
