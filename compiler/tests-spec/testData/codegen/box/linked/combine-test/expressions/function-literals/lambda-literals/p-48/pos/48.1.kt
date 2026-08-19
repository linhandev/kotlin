// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: non-local return from also lambda exits enclosing function
 */

// TESTCASE NUMBER: 1
fun test(x: Int): Int {
    x.also { if (it != 0) return 7 }
    return 0
}

fun box(): String {
    if (test(1) != 7) return "NOK"
    if (test(0) != 0) return "NOK"
    return "OK"
}
