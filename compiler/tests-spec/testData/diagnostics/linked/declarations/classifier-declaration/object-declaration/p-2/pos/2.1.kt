// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: object with supertypes and body members
 */

// TESTCASE NUMBER: 1
interface Service {
    fun run(): String
}

object DefaultService : Service {
    override fun run(): String = "ok"
}
