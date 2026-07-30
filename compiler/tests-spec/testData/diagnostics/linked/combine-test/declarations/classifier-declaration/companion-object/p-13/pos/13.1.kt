// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: companion object as interface implementor has I and String types via class and interface access
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

class Box {
    companion object : I {
        override fun foo() = "impl"
    }
}

fun case_1() {
    val asI: I = Box
    checkSubtype<I>(asI)
    checkSubtype<String>(Box.foo())
    checkSubtype<String>(asI.foo())
}
