// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: bitwise and evaluates both sides
 */

// TESTCASE NUMBER: 1
var n = 0
fun f(): Int { n++; return 1 }
fun test(): Int = 0 and f()
fun check(): Int = n

fun box(): String {
    n = 0
    if (test() != 0) return "NOK"
    if (check() != 1) return "NOK"
    n = 0
    val skipped = false && run { n++; true }
    if (skipped || n != 0) return "NOK"
    return "OK"
}
