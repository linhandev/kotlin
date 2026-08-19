/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: enum in companion is accessed via ClassName.Companion.Enum.ENTRY
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        enum class Color { RED, GREEN }
    }
}

fun test() = Box.Companion.Color.RED

fun box(): String {
    if (test() !== Box.Companion.Color.RED) return "NOK"
    if (test() === Box.Companion.Color.GREEN) return "NOK"
    return "OK"
}
