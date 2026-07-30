// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 46 -> sentence 46
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 46 -> sentence 46
 *                declarations, function-declaration -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: extension operator invoke enables call convention on class without member invoke
 */

// TESTCASE NUMBER: 1
class Box

operator fun Box.invoke(): String = "invoked"

fun test(): String = Box()()

fun box(): String {
    if (test() != "invoked") return "NOK"
    return "OK"
}
