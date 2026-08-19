// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 30 -> sentence 30
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: in operator evaluates rhs receiver before lhs element at runtime
 */

// TESTCASE NUMBER: 1
class Box {
    operator fun contains(x: Int): Boolean = true
}

var order = ""

fun make(): Box {
    order += "R"
    return Box()
}

fun test(): Boolean {
    order = ""
    val r = 1 in make()
    return order == "R" && r
}

fun box(): String {
    if (!test()) return "NOK: rhs receiver must be evaluated before lhs element"
    order = ""
    if (!(2 in Box())) return "NOK: contains should return true"
    if (order != "") return "NOK: lhs-only evaluation must not mutate order"
    return "OK"
}
