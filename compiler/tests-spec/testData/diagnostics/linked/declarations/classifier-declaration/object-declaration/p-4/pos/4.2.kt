// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: named object declaration at top level
 */

// TESTCASE NUMBER: 1
object Registry {
    fun register(name: String): String = name
}

fun call(): String = Registry.register("x")
