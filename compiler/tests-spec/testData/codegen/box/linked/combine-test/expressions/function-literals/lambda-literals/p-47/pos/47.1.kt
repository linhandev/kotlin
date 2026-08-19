// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: non-local return from let lambda on nullable receiver
 */

// TESTCASE NUMBER: 1
fun test(x: Int?): Int {
    x?.let { if (it < 0) return -1 }
    return 0
}

fun box(): String {
    if (test(-5) != -1) return "NOK"
    if (test(5) != 0) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}
