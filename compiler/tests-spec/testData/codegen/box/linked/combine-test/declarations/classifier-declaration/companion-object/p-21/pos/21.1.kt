/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: companion object factory create returns instance with name
 */

// TESTCASE NUMBER: 1
class Box(val name: String) {
    companion object {
        fun create(name: String) = Box(name)
    }
}

fun test() = Box.create("test").name

fun box(): String {
    if (test() != "test") return "NOK"
    return "OK"
}
