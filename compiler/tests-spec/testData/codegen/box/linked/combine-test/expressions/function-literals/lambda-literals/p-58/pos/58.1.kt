// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 58 -> sentence 58
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 58 -> sentence 58
 * NUMBER: 1
 * DESCRIPTION: labeled run lambda allows return@label from nested forEach
 */

// TESTCASE NUMBER: 1
fun test(): Int = run label@ {
    listOf(1).forEach { return@label 2 }
    0
}

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
