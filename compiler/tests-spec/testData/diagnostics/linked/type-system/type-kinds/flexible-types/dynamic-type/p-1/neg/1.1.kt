// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, dynamic-type -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: dynamic type is not supported as a type declaration on JVM
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: <!UNSUPPORTED!>dynamic<!> = 1
}


// TESTCASE NUMBER: 2
fun case_2() {
    class Local {
        val x: <!UNSUPPORTED!>dynamic<!> = 1
    }
}


// TESTCASE NUMBER: 3
fun case_3(p: <!UNSUPPORTED!>dynamic<!>) {}


// TESTCASE NUMBER: 4
fun case_4(): <!UNSUPPORTED!>dynamic<!> = 1


// TESTCASE NUMBER: 5
interface Box5<T> {
    val value: T
}

class Case5 : Box5<<!UNSUPPORTED!>dynamic<!>> {
    override val value: <!UNSUPPORTED!>dynamic<!> = 1
}
