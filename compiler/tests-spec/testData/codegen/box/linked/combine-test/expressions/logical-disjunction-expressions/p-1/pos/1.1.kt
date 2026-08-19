// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: chained || short-circuits after true
 */

// TESTCASE NUMBER: 1
var log = ""
fun a(): Boolean { log += "a"; return false }
fun b(): Boolean { log += "b"; return true }
fun c(): Boolean { log += "c"; return true }
fun test(): Boolean = a() || b() || c()
fun check(): String = log

fun box(): String {
    log = ""
    if (!test()) return "NOK"
    if (check() != "ab") return "NOK"
    return "OK"
}
