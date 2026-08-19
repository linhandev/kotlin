// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 61 -> sentence 61
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 61 -> sentence 61
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 61 -> sentence 61
 * NUMBER: 1
 * DESCRIPTION: companion object invoke called via class name when constructor is private
 */

// TESTCASE NUMBER: 1
class Factory private constructor() {
    companion object {
        operator fun invoke(): String = "from companion"
    }
}

fun test(): String = Factory()

fun box(): String {
    if (test() != "from companion") return "NOK"
    return "OK"
}
