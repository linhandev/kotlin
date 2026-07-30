// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: while-condition && short-circuit vs evaluate-right without body entry
 */

// TESTCASE NUMBER: 1
var n = 0
var body = 0
fun cond(): Boolean {
    n++
    return false
}

fun skip() {
    while (false && cond()) {
        body++
    }
}

fun evalRight() {
    while (true && cond()) {
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
    evalRight()
    if (n != 1) return "NOK: eval side"
    if (body != 0) return "NOK: eval body"
    return "OK"
}
