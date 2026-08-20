// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 51 -> sentence 51
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 51 -> sentence 51
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: sealed subclass invoke after cast infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
sealed class Command {
    class Execute : Command() {
        operator fun invoke(): String = "exec"
    }
}

fun case1(cmd: Command) {
    checkSubtype<String>((cmd as Command.Execute)())
}
