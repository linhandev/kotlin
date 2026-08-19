// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 12 -> sentence 12
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: bound property reference this::v inside a class captures the current receiver instance and returns the property value, verifying runtime semantics
 */

class C(val v: Int) {
    fun getF(): () -> Int = this::v
}

// TESTCASE NUMBER: 1
fun test(): Int = C(3).getF()()

fun box(): String {
    if (test() != 3) return "NOK"
    if (C(42).getF()() != 42) return "NOK"
    if (C(-1).getF()() != -1) return "NOK"
    return "OK"
}
