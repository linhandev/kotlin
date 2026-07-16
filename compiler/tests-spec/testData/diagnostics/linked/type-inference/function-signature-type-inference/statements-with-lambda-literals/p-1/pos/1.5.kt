// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: five-step lambda statement processing — step 5 bottom-up inference nested run example
 * HELPERS: checkType
 */

fun <T> foo1432(): T = null!!

fun <R> run1432(body: () -> R): R = body()

// TESTCASE NUMBER: 1
fun case_1() {
    val x = run1432 {
        run1432 {
            run1432 {
                foo1432<Int>()
            }
        }
    }
    checkSubtype<Int>(x)
}
