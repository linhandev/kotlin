// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 33 -> sentence 33
 *                type-inference, introduction-1 -> paragraph 33 -> sentence 33
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: explicit type argument conflict with value argument type is rejected
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun test() {
    val n: Int = 1
    id<String>(<!TYPE_MISMATCH!>n<!>)
}
