// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object singleton with member property and object implementing interface compile successfully
 */

// TESTCASE NUMBER: 1
object Config {
    val version = "1.0"
}

// TESTCASE NUMBER: 2
interface I {
    fun value(): Int
}

object Holder : I {
    override fun value(): Int = 1
}
