// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: lhs() in rhs() evaluates rhs() before lhs() giving order RL
 */

// TESTCASE NUMBER: 1

var order = ""

fun lhs(): String {
    order += "L"
    return "x"
}

class Holder {
    operator fun contains(v: String): Boolean = true
}

fun rhs(): Holder {
    order += "R"
    return Holder()
}

fun box(): String {
    order = ""
    if (!(lhs() in rhs())) return "NOK"
    if (order != "RL") return "NOK:$order"
    return "OK"
}
