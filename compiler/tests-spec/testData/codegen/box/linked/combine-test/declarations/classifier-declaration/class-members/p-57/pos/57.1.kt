// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 57 -> sentence 57
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 57 -> sentence 57
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 57 -> sentence 57
 * NUMBER: 1
 * DESCRIPTION: lambda body uses invoke call convention
 */

// TESTCASE NUMBER: 1

class Callable {
    operator fun invoke(): String = "called"
}

fun process(block: () -> String): String = block()

fun test(): String = process { Callable()() }

fun box(): String {
    if (test() != "called") return "NOK"
    return "OK"
}
