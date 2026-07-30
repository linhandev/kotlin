// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 51 -> sentence 51
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 51 -> sentence 51
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: invoke on sealed subclass after cast
 */

// TESTCASE NUMBER: 1
sealed class Command {
    class Execute : Command() {
        operator fun invoke(): String = "exec"
    }
}

fun test(cmd: Command): String = (cmd as Command.Execute)()

fun box(): String {
    if (test(Command.Execute()) != "exec") return "NOK"
    return "OK"
}
