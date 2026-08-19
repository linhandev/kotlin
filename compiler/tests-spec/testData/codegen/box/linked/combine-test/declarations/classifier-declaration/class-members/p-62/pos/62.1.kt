// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 62 -> sentence 62
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 62 -> sentence 62
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 62 -> sentence 62
 * NUMBER: 1
 * DESCRIPTION: same receiver: extension property then invoke
 */

// TESTCASE NUMBER: 1

class Box

operator fun Box.invoke(): String = "box"

val Box.value: String
    get() = "value"

fun test(): String {
    val b = Box()
    return b.value + b()
}

fun box(): String {
    if (test() != "valuebox") return "NOK"
    return "OK"
}
