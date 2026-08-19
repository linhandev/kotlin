// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: do-while runs body once; condition && may short-circuit or evaluate right
 */

// TESTCASE NUMBER: 1
var n = 0
var body = 0
fun cond(): Boolean {
    n++
    return false
}

fun shortCond() {
    do {
        body++
    } while (false && cond())
}

fun evalCond() {
    do {
        body++
    } while (body < 2 && cond())
}

fun box(): String {
    n = 0
    body = 0
    shortCond()
    if (body != 1) return "NOK: short body"
    if (n != 0) return "NOK: short side"
    n = 0
    body = 0
    evalCond()
    if (body != 1) return "NOK: eval body"
    if (n != 1) return "NOK: eval side"
    return "OK"
}
