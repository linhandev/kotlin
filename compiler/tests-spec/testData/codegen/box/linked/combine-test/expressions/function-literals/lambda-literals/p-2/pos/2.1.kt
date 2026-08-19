// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 2 -> sentence 2
 *                type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: lambda destructuring parameter types inferred from Pair argument
 */

// TESTCASE NUMBER: 1
fun test(): Int = (1 to "a").let { (i, s) -> i + s.length }

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
