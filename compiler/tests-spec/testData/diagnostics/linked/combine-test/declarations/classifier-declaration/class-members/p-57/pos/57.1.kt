// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 57 -> sentence 57
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 57 -> sentence 57
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 57 -> sentence 57
 * NUMBER: 1
 * DESCRIPTION: lambda body invoke call convention infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Callable {
    operator fun invoke(): String = "called"
}

fun process(block: () -> String): String = block()

fun case1() {
    checkSubtype<String>(process { Callable()() })
}
