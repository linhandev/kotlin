// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: statements, loop-statements -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: continue skips current iteration
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in 1..5) { if (x % 2 == 0) continue; s += x }; return s }

fun box(): String {
    if (test() != 9) return "NOK"
    return "OK"
}
