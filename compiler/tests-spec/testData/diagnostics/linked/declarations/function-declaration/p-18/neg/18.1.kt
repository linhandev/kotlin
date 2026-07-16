// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: member extension declared in a class is not visible outside its scope
 */

// TESTCASE NUMBER: 1
class Box {
    fun String.wrap(): String = "[$this]"
}

fun outsideScope(value: String) {
    value.<!UNRESOLVED_REFERENCE!>wrap<!>()
}

// TESTCASE NUMBER: 2
class CallableHost {
    fun Int.ext(): Int = this

    fun createReference() {
        Int::<!EXTENSION_IN_CLASS_REFERENCE_NOT_ALLOWED!>ext<!>
    }
}

fun referenceFromOutside() {
    CallableHost::<!UNRESOLVED_REFERENCE!>ext<!>
}
