// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, late-initialized-properties -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lateinit mutable member properties defer initialization checks
 */

// TESTCASE NUMBER: 1
class Service {
    lateinit var resource: String

    fun initialize(value: String) {
        resource = value
    }
}

// TESTCASE NUMBER: 2
lateinit var globalResource: String

fun assignGlobal(value: String) {
    globalResource = value
}
