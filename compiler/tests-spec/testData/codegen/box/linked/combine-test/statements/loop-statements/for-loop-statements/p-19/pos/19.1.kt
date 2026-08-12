// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: statements, loop-statements -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: labeled break@ exits outer for loop
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; loop@ for (i in 1..3) { for (j in 1..3) { if (j == 2) break@loop; s += j } }; return s }

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
