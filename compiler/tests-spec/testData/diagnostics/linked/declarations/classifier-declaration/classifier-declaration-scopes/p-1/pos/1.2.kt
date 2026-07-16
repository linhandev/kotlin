// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: primary constructor parameter scope visible in initialization
 */

// TESTCASE NUMBER: 1
class C(val n: Int) {
    val doubled = n * 2
}

// TESTCASE NUMBER: 2
class D(val name: String) {
    init {
        val greeting = "hello, $name"
    }
}
