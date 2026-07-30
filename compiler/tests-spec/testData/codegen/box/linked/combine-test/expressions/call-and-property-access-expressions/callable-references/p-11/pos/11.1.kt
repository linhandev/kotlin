// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 11 -> sentence 11
 *                declarations, function-declaration, extension-function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: unbound extension function reference String::twice infers function type (String) -> String with receiver as first parameter, verifying that the receiver type participates in the function type
 */

fun String.twice(): String = this + this

val f: (String) -> String = String::twice

// TESTCASE NUMBER: 1
fun test(): String = f("a")

fun box(): String {
    if (test() != "aa") return "NOK"
    if (f("hello") != "hellohello") return "NOK"
    if (f("") != "") return "NOK"
    return "OK"
}
