// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: bound member reference captures the original receiver instance, verifying that reassigning the variable does not affect the captured reference
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val s = "hi"
    val f = s::length
    val s2 = "bye"
    return f()
}

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
