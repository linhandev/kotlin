// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: true.or evaluates right while true || skips right
 */

// TESTCASE NUMBER: 1
var log = ""
fun a(): Boolean { log += "a"; return true }
fun b(): Boolean { log += "b"; return false }

fun viaOr(): Boolean = a().or(b())
fun viaShort(): Boolean = a() || b()

fun box(): String {
    log = ""
    if (!viaOr()) return "NOK"
    if (log != "ab") return "NOK"
    log = ""
    if (!viaShort()) return "NOK"
    if (log != "a") return "NOK"
    return "OK"
}
