// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: five-step lambda statement processing — step 4 top-down recursive analysis
 * HELPERS: checkType
 */

fun outer1432(f: () -> Int): Int = f()

fun inner1432(f: () -> Int): Int = f()

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Int>(outer1432 {
        inner1432 { 42 }
    })
}
