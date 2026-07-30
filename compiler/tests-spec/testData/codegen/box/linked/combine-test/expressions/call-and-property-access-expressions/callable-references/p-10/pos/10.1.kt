// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 10 -> sentence 10
 *                declarations, function-declaration, extension-function-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: unbound extension function reference String::tag infers function type (String) -> String with receiver as first parameter, verifying runtime semantics
 */

fun String.tag(): String = "[$this]"

val f: (String) -> String = String::tag

// TESTCASE NUMBER: 1
fun test(): String = f("a")

fun box(): String {
    if (test() != "[a]") return "NOK"
    if (f("hello") != "[hello]") return "NOK"
    if (f("") != "[]") return "NOK"
    return "OK"
}
