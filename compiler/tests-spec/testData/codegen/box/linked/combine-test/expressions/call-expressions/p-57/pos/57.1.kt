// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 57 -> sentence 57
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 57 -> sentence 57
 *                expressions, call-and-property-access-expressions, callable-references -> paragraph 57 -> sentence 57
 * NUMBER: 1
 * DESCRIPTION: callable reference as function return value is invokable, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun test(): (String) -> Int = String::length

fun box(): String {
    val f = test()
    if (f("abc") != 3) return "NOK"
    if (f("hello") != 5) return "NOK"
    if (f("") != 0) return "NOK"
    return "OK"
}
