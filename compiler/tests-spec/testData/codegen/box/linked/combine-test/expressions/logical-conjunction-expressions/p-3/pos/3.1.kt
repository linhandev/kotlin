// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: chained && short-circuits after false
 */

// TESTCASE NUMBER: 1
var log = ""
fun a(): Boolean { log += "a"; return true }
fun b(): Boolean { log += "b"; return false }
fun c(): Boolean { log += "c"; return true }
fun test(): Boolean = a() && b() && c()
fun check(): String = log

fun box(): String {
    log = ""
    if (test()) return "NOK"
    if (check() != "ab") return "NOK"
    return "OK"
}
