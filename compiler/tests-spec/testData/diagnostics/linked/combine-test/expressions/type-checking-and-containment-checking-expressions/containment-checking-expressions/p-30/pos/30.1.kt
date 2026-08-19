// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 30 -> sentence 30
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: in operator evaluates rhs receiver before lhs element and infers Boolean
 * HELPERS: checkType
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

fun case1() {
    order = ""
    checkSubtype<Boolean>(1 in make())
    checkSubtype<Boolean>(order == "R")
}
