// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 52 -> sentence 52
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 52 -> sentence 52
 * NUMBER: 1
 * DESCRIPTION: non-local return from repeat exits enclosing function
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    repeat(3) { if (it == 1) return 9 }
    return 0
}

fun box(): String {
    if (test() != 9) return "NOK"
    return "OK"
}
