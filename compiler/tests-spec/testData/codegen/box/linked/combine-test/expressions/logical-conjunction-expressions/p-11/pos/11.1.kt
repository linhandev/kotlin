// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 *                expressions, conditional-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: if-condition && short-circuit vs evaluate-right with body entry
 */

// TESTCASE NUMBER: 1
var n = 0
var body = 0
fun side(): Boolean {
    n++
    return true
}

fun skip() {
    if (false && side()) {
        body++
    }
}

fun take() {
    if (true && side()) {
        body++
    }
}

fun box(): String {
    n = 0
    body = 0
    skip()
    if (n != 0) return "NOK: skip side"
    if (body != 0) return "NOK: skip body"
    n = 0
    body = 0
    take()
    if (n != 1) return "NOK: take side"
    if (body != 1) return "NOK: take body"
    return "OK"
}
