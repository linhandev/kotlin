// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: block body, expression body, abstract function without body, and interface default implementation compile successfully
 */

// TESTCASE NUMBER: 1
fun blockBody(): Int {
    return 1
}

// TESTCASE NUMBER: 2
fun expressionBody(): Int = 2

// TESTCASE NUMBER: 3
abstract class Base {
    abstract fun noBody()
}

interface I {
    fun withDefault() = 3
}
