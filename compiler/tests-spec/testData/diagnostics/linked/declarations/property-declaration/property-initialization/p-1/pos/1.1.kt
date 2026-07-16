// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, property-initialization -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: member property initialized from constructor, lateinit assigned before read, and local val assigned before use are valid
 */

// TESTCASE NUMBER: 1
class Box(val seed: Int) {
    val value: Int = seed
}

// TESTCASE NUMBER: 2
class Service {
    lateinit var resource: String

    fun initialize() {
        resource = "ready"
    }

    fun readAfterInit(): String = resource
}

// TESTCASE NUMBER: 3
fun initializeLocal(): String {
    val message: String
    message = "ok"
    return message
}
