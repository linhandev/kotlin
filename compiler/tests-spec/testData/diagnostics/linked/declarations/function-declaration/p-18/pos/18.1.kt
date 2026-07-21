// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: generic, nested-local, and String extension functions declared in class body compile and are callable
 */

// TESTCASE NUMBER: 1
class Container {
    fun <T> T.tag(): String = "tag:$this"

    fun useGeneric(value: Int): String = value.tag()
}

// TESTCASE NUMBER: 2
class Bar {
    fun Int.extensionThis(): String {
        fun nested(): String = this.toString()
        return "int:$this-${nested()}"
    }

    fun callMember(): String = "bar"

    fun callExtension(): String = 2.extensionThis()
}

// TESTCASE NUMBER: 3
class Wrapper {
    fun String.wrap(): String = "[$this]"

    fun withReceiver(receiver: String): String = receiver.wrap()
}
